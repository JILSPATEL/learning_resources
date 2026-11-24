package Java.OOPs.Encapsulation;
import java.util.*;

class UserCred{
    private final String UserId;
    private String password;

    public UserCred(String UserId){
        this.UserId=UserId;
    }

    public String getUserId(){
        return UserId;
    }

    public void setPassword(String password){
        if(password!=null && password.length()>=6){
            this.password=password;
            System.out.println("Password Updated");
        }else{
            System.out.println("Invalid Password!");
        }
    }
    
    public boolean verifyPassword(String input){
        return input!=null && input.equals(this.password);
    }
}

public class ReadOnlyAndWriteOnly {
    public static void main(String[] args) {
        UserCred user=new UserCred("USR-100");
        System.out.println("User Id: "+user.getUserId());
        user.setPassword("secret123");
        System.out.println("Login Valid: "+user.verifyPassword("secret123"));
    }
}
