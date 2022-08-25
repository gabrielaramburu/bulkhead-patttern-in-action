package bulkheadcomp.service;

import org.springframework.stereotype.Component;

import io.github.resilience4j.bulkhead.BulkheadFullException;
import io.github.resilience4j.bulkhead.annotation.Bulkhead;

@Component
public class Case1Service {

	@Bulkhead(name = "Case1", fallbackMethod = "bulkheadFallBackMethod1")
	public void executeWithSemaphore() {
		System.out.println("Bulkhead, Executing semaphore implementation. " + Thread.currentThread().getName());
		doSomething();
	}
	
	public void bulkheadFallBackMethod1 (BulkheadFullException e) {
		System.out.println("Bulkhead fallback " + e.getMessage() + Thread.currentThread().getName());
	}
	
	private void doSomething() {
		Util.pause(5000);
	}
}
