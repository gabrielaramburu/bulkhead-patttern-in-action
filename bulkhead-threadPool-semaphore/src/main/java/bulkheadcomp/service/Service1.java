package bulkheadcomp.service;

import org.springframework.stereotype.Component;

@Component
public class Service1 {

	public String doSomeWork() {
		System.out.println("Excecuting service 1 - " + Thread.currentThread().getName());		
		return null;
	}
	
}
