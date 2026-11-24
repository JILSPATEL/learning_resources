package Java.OOPs.Encapsulation;
import java.util.*;

class BankAccount{
    private double balance;
    public double getBalance(){
        return balance;
    }
    public void deposit(double amount){
        if(amount>0){
            balance+=amount;
            System.out.println("New Balance is: "+balance);
        }else{
            System.out.println("Enter Valid Amount!");
        }
    }
    public void withdraw(double amount){
        if(amount>0 && balance>=amount){
            balance-=amount;
            System.out.println("New Balance is: "+balance);
        }else{
            System.out.println("Insufficient Balance!");
        }
    }
}

public class DataValidation {
    public static void main(String[] args) {
        BankAccount b1=new BankAccount();
        b1.deposit(10000);
        b1.withdraw(3500);
        b1.withdraw(7000);
        System.out.println("Current Balanace: "+b1.getBalance());
    }
}
