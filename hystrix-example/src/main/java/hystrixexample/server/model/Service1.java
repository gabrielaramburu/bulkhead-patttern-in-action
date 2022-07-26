package hystrixexample.server.model;

public class Service1 {
	
	public void doSometask(long millis) throws InterruptedException {
		System.out.println("Service 1: waiting= " + millis);
		Thread.sleep(millis);
		System.out.println("Service 1: end");
	}
}
