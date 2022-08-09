package bulkhead.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import bulkhead.service.Service4;
import io.micrometer.core.annotation.Timed;

@Controller
@ResponseBody   //these two annotation is equivalent to use @RestController
public class ServiceController4 {
	
	@Autowired
	private Service4 serv4;
	
	@Timed("request.service4.timed")
	@GetMapping("/service4")
	public String service1() {
	
		return serv4.doSomeWork();
	}
}
