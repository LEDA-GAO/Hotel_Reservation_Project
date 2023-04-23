package model;
import java.util.regex.Pattern;

public class Customer {
    private String firstName;
    private String lastName;
    private String email;

    public Customer(String firstName,String lastName,String email){
        String emailRegex = "^(.+)@(.+).com$";
        Pattern pattern = Pattern.compile(emailRegex);
        if(!pattern.matcher(email).matches()){
            throw new IllegalArgumentException("Error, Invalid email");
        }
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public void setFirstName(String firstName_){
        firstName = firstName_;
    }

    public void setLastName(String lastName_){
        lastName = lastName_;
    }

    public void setEmail(String email_){
        email=email_;
    }
    public String getName(){
        return firstName+" "+lastName;
    }
    public String getEmail(){
        return email;
    }
    @Override
    public String toString(){
        return "Customer Name: "+firstName+" "+lastName+", Email: "+email;
    }
}
