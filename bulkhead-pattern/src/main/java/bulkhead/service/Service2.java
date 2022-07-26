package bulkhead.service;

import org.apache.tomcat.util.json.JSONParser;
import org.springframework.stereotype.Component;

@Component
public class Service2 {
	
	public String doSomework() {
		System.out.println("Excecuting service 2..");
		pause(500);
		
		return "service 2 ok";
	}
	
	private void pause(long milis) {
		try {
			Thread.sleep(milis);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
