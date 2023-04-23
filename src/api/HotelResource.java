package api;

import model.Customer;
import model.IRoom;
import model.Reservation;
import java.util.Date;
import java.util.Collection;

import service.CustomerService;
import service.ReservationService;

public class HotelResource {
    private static final HotelResource reference = new HotelResource();
    private final CustomerService customerService = CustomerService.getReference();

    private final ReservationService reservationService = ReservationService.getReference();
    private HotelResource(){

    }

    public static HotelResource getReference(){
        return reference;
    }
    public Customer getCustomer(String email){
        return customerService.getCustomer(email);
    }

    public void createACustomer(String email, String firstName, String lastName){
        customerService.addCustomer(email,firstName,lastName);
    }

    public IRoom getRoom(String roomNumber){
        return reservationService.getARoom(roomNumber);
    }

    public Reservation bookARoom(String customerEmail, IRoom room, Date checkInDate, Date checkOutDate){
        return reservationService.reserveARoom(getCustomer(customerEmail),room,checkInDate,checkOutDate);
    }

    public Collection<Reservation> getCustomersReservations(String customerEmail){
        return reservationService.getCustomersReservation(getCustomer(customerEmail));
    }

    public void printAReservation(Reservation reservation){
        reservationService.printAReservation(reservation);
    }
    public Collection<IRoom> findARoom(Date checkIn, Date checkOut){
        return reservationService.findRooms(checkIn,checkOut);
    }
}
