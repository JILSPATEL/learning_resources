package Java.OOPs.Inheritance;

class Animal{
    void eat(){
        System.out.println("Animal eats food");
    }
}

class Dog extends Animal{
    void bark(){
        System.out.println("Dog Barks");
    }
}

public class Single {
    public static void main(String[] args) {
        Dog d=new Dog();
        d.eat();
        d.bark();
    }
}
