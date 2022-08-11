package bulkhead.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.server.ResponseStatusException;

import bulkhead.service.Service1;
import io.micrometer.core.annotation.Counted;
import io.micrometer.core.annotation.Timed;

@Controller
@ResponseBody  
public class ServiceController1 {
	
	@Autowired
	private Service1 serv1;
	
	private enum InvocationType {
		SIMPLE_CALL, CIRCUIT_BRAKER, BULKHEAD;
	}
	
	private final InvocationType invocation = InvocationType.SIMPLE_CALL;
	
	@Timed("request.service1.timed")
	@Counted(value = "request.service1.counted")
	@GetMapping("/service1")
	public String service1() {
		String response;
		if (invocation.equals(InvocationType.BULKHEAD)) {
			response = serv1.doSomeWorkUsingBulkhead();
			
		} else if (invocation.equals(InvocationType.CIRCUIT_BRAKER)) {
			response = serv1.doSomeWorkUsingCircuitBreaker();
			
		} else {
			response = serv1.doSomeWork();
		}
		
		if (response.equals("error")) {
			 throw new ResponseStatusException(
			           HttpStatus.TOO_MANY_REQUESTS, "Too many request");
		}
		return response;
	}
}
