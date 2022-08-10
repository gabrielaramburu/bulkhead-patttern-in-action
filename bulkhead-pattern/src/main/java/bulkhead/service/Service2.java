package bulkhead.service;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import bulkhead.model.Config;

@Component
public class Service2 {
	
	@Autowired
	private Config config;
	
	public String doSomework() {
		System.out.println("Excecuting service 2, delay: " + config.getPause() 
		+ " " +Thread.currentThread().getName());
		
		pause(config.getPause());
		
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
