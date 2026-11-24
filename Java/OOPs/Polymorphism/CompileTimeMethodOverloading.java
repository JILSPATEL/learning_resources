package Java.OOPs.Polymorphism;

class Calculator{
    int add(int a,int b){
        return a+b;
    }
    int add(int a,int b,int c){
        return a+b+c;
    }
    double add(double a,double b){
        return a+b;
    }
}

public class CompileTimeMethodOverloading {
    public static void main(String[] args) {
        Calculator c1=new Calculator();
        System.out.println(c1.add(10,20));
        System.out.println(c1.add(4.5, 2.5));
        System.out.println(c1.add(10, 20, 30));
    }
}
