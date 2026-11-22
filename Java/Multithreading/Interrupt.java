/*
 * This program demonstrates cooperative thread interruption. t1 repeatedly
 * checks isInterrupted() and prints "Running" until another thread calls
 * interrupt(), which sets t1's interruption flag. The main thread starts
 * t1, waits two seconds, and then interrupts it. Interrupted status can be
 * checked with isInterrupted(); interrupted() also reports the status but
 * clears it for the calling thread. Java does not forcefully stop arbitrary
 * running code, so the thread must check or handle the interruption.
 */
import java.util.*;
/*
stop thread pool
cancle long running task
isInterrupted() return flag value
interrupted() set it false
*/
public class Interrupt {
    public static void main(String[] args) throws InterruptedException {
        Thread t1=new Thread(()->{
            while(!Thread.currentThread().isInterrupted()){
                System.out.println("Running");
            }
        });
        t1.start();
        Thread.sleep(2000);
        t1.interrupt();
    }
}