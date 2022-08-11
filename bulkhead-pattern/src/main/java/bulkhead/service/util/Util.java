package bulkhead.service.util;

public class Util{
	public static void pause(long milis) {
		try {
			Thread.currentThread().sleep(milis);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
