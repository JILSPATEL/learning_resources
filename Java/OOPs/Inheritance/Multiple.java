package Java.OOPs.Inheritance;

interface Printable{
    void print();
}

interface Scannable{
    void scan();
}

class AllInOnePrinter implements Printable,Scannable{
    public void print(){
        System.out.println("Print the label");
    }
    public void scan(){
        System.out.println("Scanning the document");
    }
}

public class Multiple {
    public static void main(String[] args) {
        AllInOnePrinter p1=new AllInOnePrinter();
        p1.print();
        p1.scan();
    }
}
