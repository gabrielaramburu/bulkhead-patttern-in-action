package bulkhead.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.annotation.ApplicationScope;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@Component
@ApplicationScope
public class Service1 {
	@Autowired
	private RestTemplate restTemplate;
	
	private enum InvocationType {
		SIMPLE, CIRCUIT_BRAKER, BULKHEAD;
	}
	
	private InvocationType invocation = InvocationType.BULKHEAD;
	
	public String doSomeWork() {
		System.out.println("Excecuting service 1..");
		
		if (invocation.equals(InvocationType.BULKHEAD)) return callServiceWithBulkhead();
		
		if (invocation.equals(InvocationType.CIRCUIT_BRAKER)) return callServiceWithCircuitBreaker();
			
		return callService2();
	}
	
	@Bulkhead(name = "Service1", fallbackMethod = "bulkheadFallBackMetho")
	public String callServiceWithBulkhead() {
		System.out.println("Using bulkhead pattern");
		return callService2();
	}
	
	public String bulkheadFallBackMethod(Throwable throwable ) {
		System.out.println("Bulkhead fallback: Failed to send the request: " + throwable.getMessage());
		return "";
	}
	
	@CircuitBreaker(name = "Service1", fallbackMethod = "circuitBreakerFallBackMethod")
	public String callServiceWithCircuitBreaker() {
		System.out.println("Using circuit braker pattern");
		return callService2();
	}
	
	public String circuitBreakerFallBackMethod(Throwable throwable ) {
		System.out.println("Circuti Breaker fallback: Failed to send the request: " + throwable.getMessage());
		return "";
	}
	
	
	private String callService2() {
		ResponseEntity<String> response = 
				restTemplate.getForEntity("http://localhost:8080/service2",String.class);
		
		return "service 1 ok, " + response.getBody();
	}
}
