package bulkhead.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import bulkhead.service.util.InfluxDBRegister;
import bulkhead.service.util.Util;
import io.github.resilience4j.bulkhead.BulkheadFullException;
import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@Component
public class Service1 {
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private InfluxDBRegister register;
			
	
	public String doSomeWork() {
		System.out.println("Excecuting service 1..");		
		return callService2();
	}
	
	@Bulkhead(name = "Service1", fallbackMethod = "bulkheadFallBackMethod")
	public String doSomeWorkUsingBulkhead() {
		System.out.println("Using bulkhead pattern");
		Util.pause(5);
		return callService2();
	}
	
	
	public String bulkheadFallBackMethod(BulkheadFullException e) {
		System.out.println("Bulkhead fallback: Failed to send the request: " + e.getMessage() + Thread.currentThread().getName());
		register.registerBulkheadFullError();
		return "error";
	}
	

	@CircuitBreaker(name = "Service1", fallbackMethod = "circuitBreakerFallBackMethod")
	public String doSomeWorkUsingCircuitBreaker() {
		return callService2();
	}
	
	public void circuitBreakerFallBackMethod(Throwable throwable ) {
		System.out.println("Circuti Breaker fallback: Failed to send the request: " + throwable.getMessage());
	}
	
	
	private String callService2() {
		ResponseEntity<String> response = 
				restTemplate.getForEntity("http://localhost:8080/service2",String.class);
		
		String resp = response.getBody();
		System.out.println("response:" + resp);
		return "service 1 ok, " + resp;
	}

	
	

	
}
