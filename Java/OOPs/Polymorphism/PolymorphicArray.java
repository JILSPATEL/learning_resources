package Java.OOPs.Polymorphism;

class Payment{
    void pay(double amount){
        System.out.println("Payment of ₹ "+amount);
    }
}

class CreditCardPayment extends Payment{
    @Override 
    void pay(double amount){
        System.out.println("Paid ₹ "+amount+" using credit card");
    }
}

class UpiPayment extends Payment{
    @Override 
    void pay(double amount){
        System.out.println("Paid ₹ "+amount+" via UPI");
    }
}

public class PolymorphicArray {
    public static void main(String[] args) {
        Payment[] payments={
            new CreditCardPayment(),
            new UpiPayment()
        };
        for(Payment p:payments){
            p.pay(500.0);
        }
    }
}
