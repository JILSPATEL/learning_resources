package Java.OOPs.Polymorphism;

class Animal{
    void sound(){
        System.out.println("Animal makes a sound");
    }
}

class Dog extends Animal{
    @Override 
    void sound(){
        System.out.println("Dog barks:Woof!");
    }
}

class Cat extends Animal{
    @Override 
    void sound(){
        System.out.println("Cat meows: Meow!");
    }
}

public class RuntimePolymorphismMethodOverriding {
    public static void main(String[] args) {
        Animal myPet;
        myPet=new Dog();
        myPet.sound();
        myPet=new Cat();
        myPet.sound();
    }
}
