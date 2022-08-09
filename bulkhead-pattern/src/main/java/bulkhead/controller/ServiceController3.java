package bulkhead.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import bulkhead.service.Service3;
import io.micrometer.core.annotation.Counted;
import io.micrometer.core.annotation.Timed;

@Controller
@ResponseBody   //these two annotations are equivalent to use @RestController
public class ServiceController3 {
	
	@Autowired
	private Service3 serv3;
	
	@Timed("request.service3.timed")
	@Counted("request.service3.counted")
	@GetMapping("/service3")
	public String service1() {
	
		return serv3.doSomeWork();
	}
}
