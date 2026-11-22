/*
 * This program demonstrates a race condition in a shared Counter object.
 * Two threads, t1 and t2, call icrement() 10,001 times each. Because the
 * increment operation reads count, increases it, and writes it back, two
 * threads can perform overlapping increments and lose updates. Both join()
 * calls make the main thread wait for t1 and t2 to finish, so the final
 * count is printed after both workers complete.
 *
 * The theoretically correct total is 20,002 because both loops use <=.
 * However, the unsynchronized count++ operation is not atomic, so the printed
 * result may be lower. Protecting icrement() with synchronization or using
 * an atomic integer would prevent lost updates.
 */
import java.util.*;

class Counter{
    public int count=0;
    void icrement(){
        //critical section
        count++;
    }
}

public class RaceCondition{
    public static void main(String[] args) throws InterruptedException {
        Counter c1=new Counter();
        Thread t1=new Thread(()->{
            for(int i=0;i<=10000;i++){
                c1.icrement();
            }
        });
        Thread t2=new Thread(()->{
            for(int i=0;i<=10000;i++){
                c1.icrement();
            }
        });
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(c1.count);
    }
}
//race condition occurs due to atomicity problem