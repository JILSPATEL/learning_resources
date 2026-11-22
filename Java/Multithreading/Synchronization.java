import java.util.*;

class Counter{
    public int count=0;
    synchronized void icrement(){
        //critical section
        count++;
    }
}

public class Synchronization {
    public static void main(String[] args) throws InterruptedException {
        Counter c1=new Counter();
        Thread t1=new Thread(()->{
            for(int i=0;i<10000;i++){
                c1.icrement();
            }
        });
        Thread t2=new Thread(()->{
            for(int i=0;i<10000;i++){
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