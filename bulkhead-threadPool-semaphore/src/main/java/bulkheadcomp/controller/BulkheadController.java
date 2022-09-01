package bulkheadcomp.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import bulkheadcomp.service.BulkheadSemaphore;
import bulkheadcomp.service.BulkheadThredPool;
import bulkheadcomp.service.Service1;
import bulkheadcomp.service.Service2;
import bulkheadcomp.service.Service3;
import bulkheadcomp.service.Service4;

@RestController
public class BulkheadController {
	private static final String ERROR = "error";
	private static final String OK = "ok";
	
	@Autowired
	private BulkheadSemaphore test1;
	@Autowired
	private BulkheadThredPool test2;
	
	@Autowired
	private Service1 service1;
	
	@Autowired
	private Service2 service2;
	
	@Autowired
	private Service3 service3;
	
	@Autowired
	private Service4 service4;
	

	@GetMapping("/bulkhead")
	public String bulkheadSemaphore() {
		String response = test1.executeWithSemaphore();
		
		if(response.equals("error")) {
			throw new ResponseStatusException(HttpStatus.TOO_MANY_REQUESTS, "Too many request");
		}
		
		return response;
	}
	
	@GetMapping("/bulkhead-threadPool-bounded")
	public String bulkheadThreadPoolTestBoundedQueue() {
		//do somework
		test2.executeWithThreadPoolBoundedQueue();
		//do somework
		return OK;
	}
	
	@GetMapping("/bulkhead-threadPool-unbounded")
	public String bulkheadThreadPoolTestUnboundedQueue() {
		//do somework
		test2.executeWithThreadPoolUnboundedQueue();
		//do somework
		return OK;
	}
	
	@GetMapping("/bulkhead-threadPool-bounded-orquestration")
	public String bulkheadThreadPoolTestBoundedQueueOrquestration() {
		System.out.println("*** New Request, " + Thread.currentThread().getName());
		
		List<CompletableFuture<String>> responses = new ArrayList<CompletableFuture<String>>();
			responses.add(service1.doSomeWork());
			responses.add(service2.doSomeWork());
		
		return buildResponse(responses);
	}
	
	@GetMapping("/bulkhead-semaphore-async-orquestration")
	public String bulkheadAsyncTestBoundedQueueOrquestration() {
		System.out.println("*** New Request async, " + Thread.currentThread().getName());
		
		List<CompletableFuture<String>> responses = new ArrayList<CompletableFuture<String>>();
			responses.add(service3.doSomeWork());
			responses.add(service4.doSomeWork());
		
		return buildResponse(responses);
	}
	
	private String buildResponse(List<CompletableFuture<String>> responses) {
		
		System.out.println("Waiting for dependencies...");
		CompletableFuture<Void> finalResult = CompletableFuture.allOf(responses.toArray(new CompletableFuture[0]));
		
		finalResult.join();
		System.out.println("All dependencies have finished.");
		
		
		if(errorFound(responses)) {
			//Not a good idea to mix communication protocol errors with application business errors
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal application error");
		} else return OK;

	}
	
	private boolean errorFound(List<CompletableFuture<String>> response) {
		//TODO: In a real application, It could be necessary to implements some compensating actions.
		
		if (response.stream().anyMatch(resp -> {
			try {
				return resp.get().equals(ERROR);
			} catch (InterruptedException e) {
				return true;
			} catch (ExecutionException e) {
				return true;
			}
		})) {
			return true;
		} else  {
			return false;
		}		
	}
	
}