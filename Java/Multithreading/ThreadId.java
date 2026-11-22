/*
 * This program shows how Java identifies threads by name and numeric ID.
 * It prints the name and ID of the main thread, then creates two custom
 * threads whose lambdas print their own thread names and IDs. Calling
 * start() schedules each custom thread independently, so the order and
 * timing of their output are not guaranteed.
 */
import java.util.*;

public class ThreadId {
    public static void main(String[] args) {
        System.out.println("Current thread name: " + Thread.currentThread().getName());
        System.out.println(Thread.currentThread().getId());
        Thread t1=new Thread(()->{
             System.out.println(Thread.currentThread().getName());
             System.out.println(Thread.currentThread().getId());
        });
        Thread t2=new Thread(()->{
             System.out.println(Thread.currentThread().getName());
             System.out.println(Thread.currentThread().getId());
        });
        t1.start();
        t2.start();
    }   
}

/*
Execution order of the thread can't be preseved
*/