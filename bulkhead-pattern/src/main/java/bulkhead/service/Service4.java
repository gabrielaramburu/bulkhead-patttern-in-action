package bulkhead.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class Service4 {
	
	public String doSomeWork() {
		System.out.println("Excecuting service 4..");
		System.out.println(Thread.currentThread().getName());
		
		pause(2);
		return "service 4 ok, ";
	}
	
	private void pause(long milis) {
		try {
			Thread.sleep(milis);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
