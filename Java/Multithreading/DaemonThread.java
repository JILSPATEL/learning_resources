/*
 * This program demonstrates a daemon thread and main-thread lifetime.
 * A daemon thread runs in the background and does not prevent the Java
 * Virtual Machine from exiting. Here, t1 continuously prints "Running".
 * setDaemon(true) marks it as a daemon before start(), and the main thread
 * waits for two seconds before ending. Because there are no remaining
 * non-daemon threads, the JVM may terminate t1 while it is still running.
 */
import java.util.*;
public class DaemonThread {
    public static void main(String[] args) {
        Thread t1=new Thread(()->{
            while (true) {
                System.out.println("Running");
            }
        });
        t1.setDaemon(true);
        t1.start();
        try{
            Thread.sleep(2000);
        }catch(Exception e){

        }
    }
}