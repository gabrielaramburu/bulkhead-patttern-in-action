package bulkheadcomp.service;

public class Util{
	public static void mockExternalServiceHttpCall(long milis) {
		try {
			Thread.currentThread().sleep(milis);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
