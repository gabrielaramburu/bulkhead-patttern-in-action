package bulkhead.service;

import org.springframework.stereotype.Component;

import bulkhead.service.util.Util;

@Component
public class Service4 {
	
	public String doSomeWork() {
		System.out.println("Excecuting service 4.." 
				+ " " + Thread.currentThread().getName());
		
		Util.pause(5);
		return "service 4 ok, ";
	}
	
}
