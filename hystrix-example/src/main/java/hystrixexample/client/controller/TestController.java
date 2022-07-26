package hystrixexample.client.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import hystrixexample.client.model.Test1TimeOut;

@Controller
public class TestController {
	
	@Autowired
	private Test1TimeOut test1;
	
	@GetMapping("/test1")
	public String test1() {
		test1.sendHystrixCommand();
		return "test1";
	}

}
