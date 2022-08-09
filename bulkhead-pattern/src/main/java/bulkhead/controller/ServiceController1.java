package bulkhead.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import bulkhead.service.Service1;
import io.micrometer.core.annotation.Counted;
import io.micrometer.core.annotation.Timed;

@Controller
@ResponseBody  
public class ServiceController1 {
	
	@Autowired
	private Service1 serv1;
	
	@Timed("request.service1.timed")
	@Counted(value = "request.service1.counted")
	@GetMapping("/service1")
	public String service1() {
	
		return serv1.doSomeWork();
	}
}
