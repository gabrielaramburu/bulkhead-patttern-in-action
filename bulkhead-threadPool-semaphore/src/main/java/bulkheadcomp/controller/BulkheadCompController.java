package bulkheadcomp.controller;

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

@RestController
public class BulkheadCompController {
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
	private Service3 service4;
	

	@GetMapping("/bulkhead")
	public String bulkheadSemaphore() {
		String response = test1.executeWithSemaphore();
		
		if(response.equals("error")) {
			throw new ResponseStatusException(HttpStatus.TOO_MANY_REQUESTS, "Too many request");
		}
		
		return response;
	}
	
	@GetMapping("/bulkhead-threadPool-unbounded")
	public String bulkheadThreadPoolTestUnboundedQueue() {
		test2.executeWithThreadPoolUnboundedQueue();
		return OK;
	}
	
	@GetMapping("/bulkhead-threadPool-bounded")
	public String bulkheadThreadPoolTestBoundedQueue() {
		test2.executeWithThreadPoolBoundedQueue();
		return OK;
	}

	@GetMapping("/bulkhead-threadPool-bounded-orquestration")
	public String bulkheadThreadPoolTestBoundedQueueOrquestration() {
		System.out.println("*** New Request, " + Thread.currentThread().getName());
		
		CompletableFuture<String> response1 = service1.doSomeWork();
		CompletableFuture<String> response2 = service2.doSomeWork();
		
		System.out.println("Waiting for dependencies...");
		CompletableFuture<Void> finalResult = CompletableFuture.allOf(response1, response2);
		finalResult.join();
		System.out.println("All dependencies have finished.");
		
		String response = executeCompensatingActions(response1, response2);
		
		if(response.equals("error")) {
			//Not a good idea to mix communication protocol errors with application business errors
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal application error");
		}
		return response;
	}

	private String executeCompensatingActions(CompletableFuture<String> response1, CompletableFuture<String> response2) {
		//TODO: In a real application, It could be necessary to implements some compensating actions.
		try {
			if (response1.get().equals(ERROR) || response2.get().equals(ERROR)) {
				return ERROR;
			} else
				return OK;
		} catch (InterruptedException | ExecutionException e) {
			return ERROR;
		}
	}
	
	
	@GetMapping("/bulkhead-async-bounded-orquestration")
	public String bulkheadAsyncTestBoundedQueueOrquestration() {
		System.out.println("*** New Request async, " + Thread.currentThread().getName());
		
		CompletableFuture<String> response1 = service3.doSomeWork();
		CompletableFuture<String> response2 = service4.doSomeWork();
		
		System.out.println("Waiting for dependencies...");
		CompletableFuture<Void> finalResult = CompletableFuture.allOf(response1, response2);
		finalResult.join();
		System.out.println("All dependencies have finished.");
		
		String response = executeCompensatingActions(response1, response2);
		
		if(response.equals("error")) {
			//Not a good idea to mix communication protocol errors with application business errors
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal application error");
		}
		return response;
	}
	
}