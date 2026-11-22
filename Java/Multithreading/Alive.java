/*
 * This program demonstrates Thread.isAlive(). t1 is initially not alive
 * before start() because it has not begun execution. After start(), isAlive()
 * reports whether t1 has been started and has not yet terminated. The worker
 * then sleeps for two seconds, so the output of isAlive() depends on whether
 * the check happens before or after t1 completes.
 */
import java.util.*;

public class Alive {
    public static void main(String[] args) {
        Thread t1=new Thread(()->{
            try{
                Thread.sleep(2000);
            }catch(Exception e){

            }
        });
        System.out.println(t1.isAlive());
        t1.start();
        System.out.println(t1.isAlive());
    }
}