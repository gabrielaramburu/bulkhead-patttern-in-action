package bulkheadcomp.service;

import org.springframework.stereotype.Component;

import io.github.resilience4j.bulkhead.BulkheadFullException;
import io.github.resilience4j.bulkhead.annotation.Bulkhead;

@Component
public class BulkheadSemaphore {

	private static final long DELAY = 3000;

	@Bulkhead(name = "Case1", fallbackMethod = "bulkheadFallBackMethod1")
	public String executeWithSemaphore() {
		System.out.println("Bulkhead, Executing semaphore implementation. " + Thread.currentThread().getName());
		Util.mockExternalServiceHttpCall(DELAY);
		return "ok";
	}
	
	public String bulkheadFallBackMethod1 (BulkheadFullException e) {
		System.out.println("Bulkhead fallback " + e.getMessage() + Thread.currentThread().getName());
		return "error";
	}
	
	
}
