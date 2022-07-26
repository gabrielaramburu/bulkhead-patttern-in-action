package bulkhead.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import bulkhead.model.Config;
import bulkhead.service.util.Util;

@Component
public class Service3 {
	@Autowired
	private RestTemplate restTemplate;
	

	public String doSomeWork() {
		System.out.println("Excecuting service 3..");
		Util.pause(Config.DEFAULT_DELAY);
		
		
		ResponseEntity<String> response = 
				restTemplate.getForEntity("http://localhost:8080/service4",String.class);
		
		
		return "service 3 ok, " + response.getBody();
	}
}
