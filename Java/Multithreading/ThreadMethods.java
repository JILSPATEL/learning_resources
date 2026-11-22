/*
 * This program demonstrates Thread.sleep() and Thread.join().
 * The main thread starts t1, which sleeps for two seconds before printing
 * "Thread-0 starts". Thread.sleep() pauses t1 without releasing locks it
 * happens to hold. The main thread calls join(), waits until t1 finishes,
 * and then prints "Main thread ends". As a result, the output enforces a
 * sleep-delay followed by ordered completion of the worker and main threads.
 */
import java.util.*;

public class ThreadMethods {
    public static void main(String[] args) throws InterruptedException{
        System.out.println("main thread starts");
        Thread t1=new Thread(()->{
            try{
                Thread.sleep(2000);//not release lock
            }catch(InterruptedException e){

            }
            System.out.println("Thread-0 starts");
        });
        t1.start();
        t1.join();//let t1 thread complete its execution
        System.out.println("Main thread ends");
    }
}

/*
Thread.yield() if other same priority thread comes then current thread let them to execute
*/