import java.util.*;

/*
 * This example demonstrates method-level synchronization.
 *
 * Both threads call show() on the same Test object. Because show() is
 * synchronized, a thread must acquire the Test object's lock before entering
 * the method. Only one thread can hold that lock at a time, so the second
 * thread waits while the first thread is sleeping and printing.
 *
 * The two "Inside show" and "Show finish" messages for a single thread always
 * remain together. However, either thread may acquire the lock first, so the
 * names in the output can vary between runs.
 */
class Test{
    // The lock belongs to the Test object because this is an instance method.
    //even if other methods with synchronized keyword can't be access by thread-2
    synchronized void show(){
        System.out.println("Inside show "+Thread.currentThread().getName());
        try{
            Thread.sleep(2000);
        }catch(Exception e){
            
        }
        System.out.println("Show finish "+Thread.currentThread().getName());
    }
}

public class SynchronizationII {
    public static void main(String[] args) {
        Test test=new Test();
        Thread t1=new Thread(()->test.show());
        Thread t2=new Thread(()->test.show());
        t1.start();
        t2.start();
    }
}