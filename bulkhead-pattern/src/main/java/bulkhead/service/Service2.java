package bulkhead.service;

import org.apache.tomcat.util.json.JSONParser;
import org.springframework.stereotype.Component;

@Component
public class Service2 {
	
	public String doSomework() {
		System.out.println(Thread.currentThread().getName() + " " + "Excecuting service 2..");
		pause(3000);
		
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
