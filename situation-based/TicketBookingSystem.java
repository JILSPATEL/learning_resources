import java.util.*;

import javax.management.ObjectName;

class TicketBooking{
    private int availableSeats;
    public TicketBooking(int availableSeats){
        this.availableSeats=availableSeats;
    }
    Object o1=new Object();
    void book(String user){
        synchronized(o1){
            if(availableSeats>0){
                System.out.println(user+" booked a ticket\n");
                availableSeats--;
            }
            else{
                System.out.println("Seats are not available!\n");
            }
        }
    }
    void displaySeats(){
        synchronized(o1){
            System.out.println("Available Seats: "+availableSeats);
            System.out.println();
        }
    }
}

public class TicketBookingSystem {
    public static void main(String[] args) throws InterruptedException {
        TicketBooking tkt1=new TicketBooking(10);
        Thread t1=new Thread(()->tkt1.book("User"));
        Thread t2=new Thread(()->tkt1.displaySeats());
        t1.start();
        t1.join();
        t2.start();
    }
}