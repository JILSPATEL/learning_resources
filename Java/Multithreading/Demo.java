/*
 * This program demonstrates creating a thread by extending Thread.
 * MyTread overrides run() to print "Thread is running", and main() creates
 * a MyTread instance and calls start(). Calling start() requests a new
 * thread of execution and eventually invokes run(); calling run() directly
 * would execute the method in the current thread instead. Extending Thread
 * is possible, but implementing Runnable is generally more flexible because
 * it separates the task from the threading mechanism.
 */
public class Demo {
    public static void main(String[] args) {
        MyTread t1 = new MyTread();
        t1.start();
    }
}

class MyTread extends Thread{
    @Override
    public void run(){
        System.out.println("Thread is running");
    }
}

/*
This is a simple multi-threading example in Java.
t1.start() is called to start the thread, which invokes the run() method of MyThread class.

*/
