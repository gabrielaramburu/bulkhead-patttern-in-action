package bulkhead.service;

import org.springframework.stereotype.Component;

import bulkhead.model.Config;
import bulkhead.service.util.Util;

@Component
public class Service4 {
	
	public String doSomeWork() {
		System.out.println("Excecuting service 4.." 
				+ " " + Thread.currentThread().getName());
		
		Util.pause(Config.DEFAULT_DELAY);
		return "service 4 ok, ";
	}
	
}
