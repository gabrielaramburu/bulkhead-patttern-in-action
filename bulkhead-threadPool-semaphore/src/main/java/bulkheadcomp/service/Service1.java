package bulkheadcomp.service;

import java.util.concurrent.CompletableFuture;

import org.springframework.stereotype.Component;

import io.github.resilience4j.bulkhead.BulkheadFullException;
import io.github.resilience4j.bulkhead.annotation.Bulkhead;

@Component
public class Service1 {
	private static final int DELAY = 5000;
	
	@Bulkhead(name = "Service1", fallbackMethod = "futureFallback", type = Bulkhead.Type.THREADPOOL )
	public CompletableFuture<String> doSomeWork() {
		System.out.println("Excecuting service 1 - " + Thread.currentThread().getName());	
		Util.mockExternalServiceHttpCall(DELAY);
		return CompletableFuture.completedFuture("ok");
	}
	
	private CompletableFuture<String> futureFallback(BulkheadFullException ex) {
		System.out.println("Service 1 - Request rejected, bulkhead is full: " + Thread.currentThread().getName());
        return CompletableFuture.completedFuture("error");
    }
}
