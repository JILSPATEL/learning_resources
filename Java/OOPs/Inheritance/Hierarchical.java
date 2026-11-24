package Java.OOPs.Inheritance;

class Shape {
    void draw() {
        System.out.println("Drawing a shape");
    }
}

class Circle extends Shape {
    void area() {
        System.out.println("Area = pi * r * r");
    }
}

class Square extends Shape {
    void area() {
        System.out.println("Area = side * side");
    }
}

public class Hierarchical {
    public static void main(String[] args) {
        Circle c = new Circle();
        c.draw();
        c.area();

        Square s = new Square();
        s.draw();
        s.area();
    }
}