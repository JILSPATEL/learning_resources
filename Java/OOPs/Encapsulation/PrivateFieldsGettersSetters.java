package Java.OOPs.Encapsulation;
import java.util.*;
class Student {
    private String name;
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
public class PrivateFieldsGettersSetters {
    public static void main(String[] args) {
        Student s = new Student();
        s.setName("Aarav");
        s.setAge(20);

        System.out.println("Name: " + s.getName());
        System.out.println("Age: " + s.getAge());
    }
}