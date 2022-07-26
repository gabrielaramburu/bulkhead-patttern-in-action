package hystrixexample.server.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ServiceController {
	
	@GetMapping("/service1")
	public String service1 () {
		return "service 1";
	}
	
}
