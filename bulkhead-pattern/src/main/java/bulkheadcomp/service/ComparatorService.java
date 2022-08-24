package bulkheadcomp.service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.RejectedExecutionException;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import bulkhead.service.util.Util;
import io.github.resilience4j.bulkhead.BulkheadFullException;
import io.github.resilience4j.bulkhead.annotation.Bulkhead;

@Component
public class ComparatorService {
	
	@Bulkhead(name = "Test1", fallbackMethod = "bulkheadFallBackMethod1")
	public String executeWithSemaphore() {
		System.out.println("Executing semaphore impl. bulkhead: " + Thread.currentThread().getName());
	
		doSomething();
		return "";
	}
	
	public String bulkheadFallBackMethod1 (BulkheadFullException e) {
		System.out.println("Bulkhead fallback: Failed to send the request: " 
				+ e.getMessage() + Thread.currentThread().getName());
		
		return "";
	}

	
	@Bulkhead(name = "Test2", fallbackMethod = "futureFallback", type = Bulkhead.Type.THREADPOOL )
	public CompletableFuture<String> executeWithThreadPool() {
		System.out.println("Executing threadPool impl. bulkhead: " + Thread.currentThread().getName());
		doSomething();
		return CompletableFuture.completedFuture("");
	}
	
	private CompletableFuture<String> futureFallback(BulkheadFullException ex) {
		System.out.println("Request rejected, bulkhead is full: " + Thread.currentThread().getName());
        return CompletableFuture.completedFuture("Recovered specific TimeoutException: " + ex.toString());
    }
	
	public void executeWithThreadPoolAsync() {
	}

	@Async
	@Bulkhead(name = "Test1", fallbackMethod = "bulkheadFallBackMethod3")
	public CompletableFuture<String> executeWithBulkheadAsync() {
		doSomething();
		System.out.println("Executing async with bulkhead:" + Thread.currentThread().getName());
		return CompletableFuture.completedFuture("end");
	}
	
	public CompletableFuture<String> bulkheadFallBackMethod3 (BulkheadFullException e) {
		System.out.println("Bulkhead fallback: Failed to send the request: " 
				+ e.getMessage() + Thread.currentThread().getName());
		
		return CompletableFuture.completedFuture("");
	}
	
	private void doSomething() {
		Util.pause(5000);
	}

	
}
