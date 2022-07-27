package bulkhead.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class Service1 {
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private ServerProperties serverProperties;
	
	public String doSomeWork() {
		System.out.println("Excecuting service 1.." + serverProperties.getTomcat().getMaxThreads());
		ResponseEntity<String> response = 
				restTemplate.getForEntity("http://localhost:8080/service2",String.class);
		
		return "service 1 ok, " + response.getBody();
	}
}
