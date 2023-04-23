package service;

import model.Customer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class CustomerService {
    private static final CustomerService reference = new CustomerService();
    private final Map<String, Customer> customerMap = new HashMap<String, Customer>();
    private final ArrayList<Customer> allCustomer = new ArrayList<Customer>();
    private CustomerService(){

    }
    public static CustomerService getReference() {
        return reference;
    }

    public void addCustomer(String email, String firstName, String lastName){
        if(! customerMap.containsKey(email)){
            Customer newCustomer = new Customer(firstName, lastName, email);
            allCustomer.add(newCustomer);
            customerMap.put(email, newCustomer);
        }
        else{
            System.out.println("This email has already been used. Please try another email.");
        }
    }
    public Customer getCustomer(String customerEmail){
        if(customerMap.containsKey(customerEmail)){
            return customerMap.get(customerEmail);
        }
        else{
            System.out.println("This email address has not been created as an account.");
            return  null;
        }
    }

    public Collection<Customer> getAllCustomers(){
        return allCustomer;
    }


}
