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
		System.out.println("Excecuting service 2, delay: " + config.getPause());
		System.out.println(Thread.currentThread().getName());
		//pause(config.getPause());
		nonBlockingWork(config.getPause());
		
		return "service 2 ok";
	}
	
	private void pause(long milis) {
		try {
			Thread.sleep(milis);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public  static void main(String[] args) {
		Service2 ser = new Service2();
		ser.nonBlockingWork(1000);
	}
	
	
	/**
	 * is there a better way of doing this?
	 */
	public void nonBlockingWork(int milis) {
		
		int i = 0;
		long initTime = System.currentTimeMillis();
		System.out.println("ini " + i);
		while (i < milis) {
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			i++;
		}
		long endTime = System.currentTimeMillis();
		System.out.println("fin:" + " " + (endTime - initTime));
	}
}
