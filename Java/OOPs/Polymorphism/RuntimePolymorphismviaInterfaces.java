package Java.OOPs.Polymorphism;

interface Shape{
    double area();
}

class Circle implements Shape{
    double radius;
    Circle(double radius){
        this.radius=radius;
    }

    @Override 
    public double area(){
        return Math.PI*radius*radius;
    }
}

class Rectangle implements Shape{
    double width,height;
    Rectangle(double width,double height){
        this.width=width;
        this.height=height;
    }

    @Override 
    public double area(){
        return width*height;
    }
}

public class RuntimePolymorphismviaInterfaces {
    static void printArea(Shape s) {
        System.out.println("Area: " + s.area());
    }
    public static void main(String[] args) {
        printArea(new Circle(3.0));
        printArea(new Rectangle(4.0, 5.0));
    }
}
