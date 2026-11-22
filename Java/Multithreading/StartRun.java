/*
 * This program compares direct run() invocation with normal thread startup.
 * Calling t1.run() executes the lambda in the calling thread; because this
 * is called from main(), no separate operating-system thread is created.
 * start() is required to schedule the runnable on a new thread, and it should
 * normally be called only once for a Thread instance.
 */
import java.util.*;

public class StartRun {
    public static void main(String[] args) {
        Thread t1=new Thread(()->{
             System.out.println(Thread.currentThread().getName());
             System.out.println(Thread.currentThread().getId());
        });
        //t1.start();
        t1.run();
    }
}

/*
os cant create new thread without calling start()
we cant start one thread multiple time
*/