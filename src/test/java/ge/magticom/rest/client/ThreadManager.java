package ge.magticom.rest.client;
import java.io.IOException;
 
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

 
public class ThreadManager {
	private static ExecutorService pool = Executors.newFixedThreadPool(50,
			new ThreadFactory() {
				public Thread newThread(Runnable runnable) {
					Thread thread = new Thread(runnable);
					thread.setName(String.format("MultiServer%s", thread.getId()));
					return thread;
				}
			});
	/**
	 * @param args
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws IOException, InterruptedException {
	    System.out.println("Server started");
	     Date d=(Date) Calendar.getInstance().getTime();
		 for(Integer i=0;i<200;i++){
			pool.submit(new ReportTest( ));
		 }
		    pool.shutdown();
		    // Wait until all threads are finish
		    while (!pool.isTerminated()) {
		    	Thread.sleep(1000);
		    }
		    Calendar cal1=Calendar.getInstance();
		    System.out.println(cal1.getTime() ) ;
		     Date d1=(Date) Calendar.getInstance().getTime();
		     System.err.println( "time difference "+(d1.getTime()-d.getTime())/1000);
		}
}