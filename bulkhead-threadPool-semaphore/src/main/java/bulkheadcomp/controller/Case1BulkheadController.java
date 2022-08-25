package bulkheadcomp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import bulkheadcomp.service.Case1Service;

@RestController
@RequestMapping("/case1/")
public class Case1BulkheadController {
	
	@Autowired
	private Case1Service case1service;

	@GetMapping("/bulkhead")
	public String bulkheadTest() {
		case1service.executeWithSemaphore();
		return "ok";
	}
	
	

}