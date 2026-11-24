import java.util.*;

/**
 * A one-slot producer-consumer buffer.
 *
 * The producer may write only when the slot is empty, and the consumer may
 * read only when the slot contains a value. Both operations synchronize on
 * this Box object's monitor, so checking the flag and changing the item happen
 * as one protected operation.
 */
class Box{
    volatile Integer item;
    volatile Boolean flag=false;

    /**
     * Stores one value in the buffer and wakes a waiting consumer.
     *
     * wait() releases this object's monitor while the producer is waiting.
     * After another thread calls notify(), the producer must reacquire the
     * monitor before continuing, so the condition is checked again in a loop.
     */
    synchronized void producer(int value) throws InterruptedException{
        while(flag==true){
            wait();
        }
        item=value;
        flag=true;
        System.out.println("Producer Produces "+item);
        notify();
    }

    /**
     * Removes the value from the buffer and wakes a waiting producer.
     *
     * The while condition is required because a thread can wake up without
     * the condition it needs being true, and because the other thread may
     * acquire the monitor first after notification.
     */
    synchronized void consumer() throws InterruptedException{
        while (flag==false) {
            wait();
        }
        System.out.println("Consumer Consumes "+item);
        item=null;
        flag=false;
        notify();
    }
}

/**
 * Demonstrates inter-thread communication with wait/notify.
 *
 * InterThreadCommI writes and reads without coordination, and
 * InterThreadCommII uses busy waiting. This version lets waiting threads
 * sleep instead of repeatedly consuming CPU time.
 */
public class InterThreadCommIII {
    public static void main(String[] args) {
        Box box=new Box();
        Thread t1=new Thread(()->{
            for(int i=0;i<=20;i++){
                try{
                    Thread.sleep(100);
                    box.producer(i);
                }catch(Exception e){}
            }
        });
        Thread t2=new Thread(()->{
            for(int i=0;i<=20;i++){
                try{
                    Thread.sleep(70);
                    box.consumer();
                }catch(Exception e){}
            }
        });
        t1.start();
        t2.start();
    }
}