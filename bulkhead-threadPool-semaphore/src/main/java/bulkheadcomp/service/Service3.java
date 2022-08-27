package bulkheadcomp.service;

import java.util.concurrent.CompletableFuture;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import io.github.resilience4j.bulkhead.BulkheadFullException;
import io.github.resilience4j.bulkhead.annotation.Bulkhead;

@Component
public class Service3 {
	private static final int DELAY = 5000;
	
	@Bulkhead(name = "Service3", fallbackMethod = "futureFallback")
	@Async
	public CompletableFuture<String> doSomeWork() {
		System.out.println("Excecuting service 3 - " + Thread.currentThread().getName());	
		Util.mockExternalServiceHttpCall(DELAY);
		return CompletableFuture.completedFuture("ok");
	}
	
	public CompletableFuture<String> futureFallback(BulkheadFullException ex) {
		System.out.println("Service 3 - Request rejected, bulkhead is full: " + Thread.currentThread().getName());
        return CompletableFuture.completedFuture("error");
    }
	
}
