package bulkheadcomp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import bulkheadcomp.service.ComparatorService;

@RestController
public class ComparatorController {

	@Autowired
	private ComparatorService service;

	@GetMapping("/comparator/bulkhead")
	public void bulkheadTest() {
		service.executeWithSemaphore();
	}
	
	@GetMapping("/comparator/bulkheadAsync")
	public void bulkheadAsyncTest() {
		service.executeWithBulkheadAsync();
	}
	
	@GetMapping("/comparator/threadPool")
	public void threadPoolTest() {
		service.executeWithThreadPool();
	}

	@GetMapping("/comparator/threadPoolAsync")
	public void threadPoolAsyncTest() {
		service.executeWithThreadPoolAsync();
	}
	
	
}