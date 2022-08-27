package bulkheadcomp.service;

import java.util.concurrent.CompletableFuture;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import io.github.resilience4j.bulkhead.BulkheadFullException;
import io.github.resilience4j.bulkhead.annotation.Bulkhead;

@Component
public class BulkheadThredPool {
	
	
	private static final long DELAY = 3000;

	@Bulkhead(name = "Case2", fallbackMethod = "futureFallback", type = Bulkhead.Type.THREADPOOL )
	public CompletableFuture<String> executeWithThreadPoolUnboundedQueue() {
		System.out.println("Bulkhead, Executing ThreadPool Unbounded Queue. " + Thread.currentThread().getName());
		Util.mockExternalServiceHttpCall(DELAY);
		return CompletableFuture.completedFuture("");
	}
	
	

	@Bulkhead(name = "Case3", fallbackMethod = "futureFallback", type = Bulkhead.Type.THREADPOOL )
	public CompletableFuture<String>  executeWithThreadPoolBoundedQueue() {
		System.out.println("Bulkhead, Executing ThreadPool bounded Queue. " + Thread.currentThread().getName());
		Util.mockExternalServiceHttpCall(DELAY);
		return CompletableFuture.completedFuture("");
	}

	private CompletableFuture<String> futureFallback(BulkheadFullException ex) {
		System.out.println("Request rejected, bulkhead is full: " + Thread.currentThread().getName());
        return CompletableFuture.completedFuture("Recovered specific TimeoutException: " + ex.toString());
    }
	
}
