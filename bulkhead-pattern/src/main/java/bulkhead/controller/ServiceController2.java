package bulkhead.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import bulkhead.service.Service2;
import io.micrometer.core.annotation.Timed;

@RestController
public class ServiceController2 {
	
	@Autowired
	private Service2 serv2;
	
	@Timed("request.service2.timed")
	@GetMapping("/service2")
	public String service2() {
		return serv2.doSomework();		
	}
}
