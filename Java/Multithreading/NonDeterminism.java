/*
 * This program demonstrates nondeterministic thread execution. Two threads
 * independently print odd and even numbers from 0 through 99. The JVM
 * scheduler decides when each thread receives CPU time, so the interleaved
 * T1/T2 output is not guaranteed to follow the same order on every run.
 * Synchronization or join() would be needed if a specific output order
 * were required.
 */
import java.util.*;

public class NonDeterminism {
    public static void main(String[] args) {
        Thread t1=new Thread(()->{
             for(int i=0;i<100;i++){
                if(i%2==1)
                    System.out.println("T1 "+i);
             }            
        });
        Thread t2=new Thread(()->{
           for(int i=0;i<100;i++){
                if(i%2!=1)
                    System.out.println("T2 "+i);
             } 
        });
        t1.start();
        t2.start();
    }
}

/*
in thread order of execution is not fixed 
*/