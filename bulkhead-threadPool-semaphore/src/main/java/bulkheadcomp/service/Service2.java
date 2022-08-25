package bulkheadcomp.service;

import org.springframework.stereotype.Component;

@Component
public class Service2 {
	
	
	public String doSomeWork() {
		System.out.println("Excecuting service 2 - " + Thread.currentThread().getName());		
		return null;
	}

}
