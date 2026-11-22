/*
 * This program demonstrates the effect of volatile variables on visibility
 * between threads. t1 waits two seconds and then sets the shared flag to
 * true. t2 repeatedly reads flag in a while-loop until it becomes false.
 * The volatile keyword tells the JVM that flag may be changed by another
 * thread, so reads and writes must have cross-thread visibility rather than
 * relying on one thread's cached copy. Once t1 changes the flag, t2 can
 * observe the update and exit the loop.
 */
import java.util.*;

public class Visibility {
    static volatile boolean flag =false;
    public static void main(String[] args) {
        
        Thread t1=new Thread(()->{
            try{
                Thread.sleep(2000);
            }catch(Exception e){

            }
            flag=true;
        });
        Thread t2=new Thread(()->{
            //t2 read flag from cache hence it never stop
            while (!flag) {
                System.out.println("Thread 2 is running");
            }
            System.out.println("Thread 2 finished");
        });
        t1.start();
        t2.start();
    }
}