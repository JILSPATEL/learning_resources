/*
 * This program observes several states of a Java thread during execution.
 * getState() initially shows NEW before start(), and later it may show
 * RUNNABLE or TERMINATED depending on thread scheduling and timing. The
 * program starts t1, prints its state, pauses the main thread for two
 * seconds, and prints t1's state again. Thread state reporting can vary
 * because it depends on what the thread is doing when getState() runs.
 */
import java.util.*;

public class Phases {
    public static void main(String[] args) {
        Thread t1=new Thread(()->{
            System.out.println(Thread.currentThread().getName());
        });
        System.out.println(t1.getState());
        t1.start();
        System.out.println(t1.getState());
        try{
            Thread.sleep(2000);
        }catch(Exception e){}
        System.out.println(t1.getState());
    }
}