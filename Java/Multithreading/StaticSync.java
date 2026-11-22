import java.util.*;

/*
 * This example demonstrates static method synchronization.
 *
 * count is shared by every Counter object because it is static. The
 * synchronized static method uses the Counter.class object as its lock, so
 * only one thread can execute increment() at a time, even if different
 * Counter instances are used.
 *
 * The two threads may start in either order, but the synchronized method
 * prevents them from updating count simultaneously. Therefore, the printed
 * values are 1 and 2, although their thread scheduling order is not fixed.
 */
class Counter{
    static int count=0;

    // A static synchronized method locks Counter.class.
    synchronized static void increment(){
        try{
            Thread.sleep(2000);
        }catch(Exception e){}
        count++;
        System.out.println(count);
    }
}

public class StaticSync {
    public static void main(String[] args) {
        Thread t1=new Thread(()->Counter.increment());
        Thread t2=new Thread(()->Counter.increment());
        t1.start();
        t2.start();
    }
}