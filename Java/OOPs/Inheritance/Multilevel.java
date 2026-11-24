package Java.OOPs.Inheritance;

class Vehicle {
    void start() {
        System.out.println("Vehicle starts");
    }
}

class Car extends Vehicle {
    void drive() {
        System.out.println("Car is driving");
    }
}

class ElectricCar extends Car {
    void chargeBattery() {
        System.out.println("Charging electric battery");
    }
}

public class Multilevel {
    public static void main(String[] args) {
        ElectricCar e1=new ElectricCar();
        e1.start();
        e1.drive();
        e1.chargeBattery();
    }   
}
