package api;

import model.Customer;
import model.IRoom;
import model.Room;
import service.CustomerService;
import service.ReservationService;

import java.util.Collection;
import java.util.List;

public class AdminResource {
    private static final AdminResource reference = new AdminResource();

    private final CustomerService customerService = CustomerService.getReference();

    private final ReservationService reservationService = ReservationService.getReference();

    private AdminResource(){}

    public static AdminResource getReference(){
        return reference;
    }

    public Customer getCustomer(String email){
        return customerService.getCustomer(email);
    }

    public void addRoom(List<Room> room){
        for(IRoom eachRoom: room){
            try {
                reservationService.addRoom(eachRoom);
                System.out.println("Room " +eachRoom.getRoomNumber()+" is added successfully");
            }catch (IllegalArgumentException exception){
                System.out.println(exception.getLocalizedMessage());
            }



        }
    }

    public Collection<IRoom> getAllRooms(){
        return reservationService.getAllRoom();
    }

    public Collection<Customer> getAllCustomers(){
        return customerService.getAllCustomers();
    }

    public void displayAllReservations(){
        reservationService.printAllReservation();
    }
}
