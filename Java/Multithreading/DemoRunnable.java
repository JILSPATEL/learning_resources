/*
 * This file presents two approaches for creating a Java thread with code
 * that implements Runnable. Approach 1 creates MyRunnable and passes that
 * object to a Thread before calling start(), while the alternative uses a
 * lambda expression to provide run() behavior directly. In both approaches,
 * start() schedules execution asynchronously and causes the thread to print
 * "Thread is running". The file contains duplicate public DemoRunnable
 * declarations, so the currently saved source would need to be reduced to
 * one class definition before it can compile.
 */
import java.util.*;
public class DemoRunnable {
    public static void main(String[] args) {
        MyRunnable myRunnable = new MyRunnable();
        Thread t1 = new Thread(myRunnable);
        t1.start();
    }
}

class MyRunnable implements Runnable{
    @Override
    public void run() {
        System.out.println("Thread is running");
    }
}

/*
This is a simple multi-threading example in Java using the Runnable interface.
A new instance of MyRunnable is created, and then a new Thread is created with that instance.
The start() method is called on the thread to begin execution.
*/

//Approach:2
// Using lambda expression
public class DemoRunnable {
    public static void main(String[] args) {
        MyRunnable myRunnable = new MyRunnable();
        Thread t1 = new Thread(()->System.out.println("Thread is running"));
        t1.start();
    }
}