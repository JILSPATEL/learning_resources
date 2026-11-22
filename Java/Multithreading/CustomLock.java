import java.util.*;

class Bank{
    Object lock1=new Object();
    Object lock2=new Object();

    void deposit(){
        synchronized(lock1){
            System.out.println("Deposit");
            try{
                Thread.sleep(2000);
            }catch(Exception e){}
        }
    }
    void withdraw(){
        synchronized(lock2){
            System.out.println("withdraw");
            try{
                Thread.sleep(2000);
            }catch(Exception e){}
        }
    }
}

public class CustomLock {
    public static void main(String[] args) {
        Bank b1=new Bank();
        Thread t1=new Thread(()->b1.deposit());
        Thread t2=new Thread(()->b1.withdraw());
        t1.start();
        t2.start();
    }
}