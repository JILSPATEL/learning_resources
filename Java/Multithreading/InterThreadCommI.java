import java.util.*;
//use wait() notify() notifyAll()
//use producer consumer problem
class Box{
    Integer item;
    Boolean flag=false;
    void producer(int value){
        item=value;
        flag=true;
        System.out.println("Producer Produces "+item);
    }
    void consumer(){
        System.out.println("Consumer Consumes "+item);
        item=null;
        flag=false;
    }
}
public class InterThreadCommI {
    public static void main(String[] args) {
        Box box=new Box();
        Thread t1=new Thread(()->{
            for(int i=0;i<=20;i++){
                try{
                    Thread.sleep(100);
                }catch(Exception e){}
                box.producer(i);
            }
        });
        Thread t2=new Thread(()->{
            for(int i=0;i<=20;i++){
                try{
                    Thread.sleep(70);
                }catch(Exception e){}
                box.consumer();
            }
        });
        t1.start();
        t2.start();
    }
}