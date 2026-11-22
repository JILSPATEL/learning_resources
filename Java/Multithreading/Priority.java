/*
 * This program demonstrates Java thread-priority values. Thread priorities
 * range from MIN_PRIORITY (1) through NORM_PRIORITY (5) to MAX_PRIORITY (10).
 * It sets t1 to priority 3, prints that value, and starts both threads.
 * Priority is only a scheduling hint: the operating system and JVM ultimately
 * decide execution order, so priorities do not guarantee t1 runs first or
 * that scheduling behavior is consistent across platforms.
 */
import java.util.*;

public class Priority {
    public static void main(String[] args) {
        Thread t1=new Thread(()->{
            System.out.println("Custom thread running");
        });
        Thread t2=new Thread(()->{
            System.out.println("Custom2 thread running");
        });
        t1.start();
        t2.start();
        t1.setPriority(3);
        System.out.println(t1.getPriority());
    }
}
/*
MAX_PRIO=10
MIN_PTIO=1
NORM_PRIO=5
It depends on os 
partialy respect priority
*/