package bulkhead.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import bulkhead.service.Service1;

@Controller
@ResponseBody   //Agregar estas dos anotaciones es lo mismo que usar únicamente @RestController
public class ServiceController1 {
	
	@Autowired
	private Service1 serv1;
	
	@GetMapping("/service1")
	public String service1() {
	
		return serv1.doSomeWork();
	}
}
