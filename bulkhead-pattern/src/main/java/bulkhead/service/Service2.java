package bulkhead.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import bulkhead.model.Config;
import bulkhead.service.util.Util;

@Component
public class Service2 {
	
	@Autowired
	private Config config;
	
	public String doSomework() {
		System.out.println("Excecuting service 2, delay: " + config.getPause() 
		+ " " +Thread.currentThread().getName());
		
		Util.pause(config.getPause());
		
		return "service 2 ok";
	}

}
