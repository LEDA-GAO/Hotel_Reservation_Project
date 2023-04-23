package service;

import model.Customer;
import model.IRoom;
import model.Reservation;
import model.Room;

import java.text.SimpleDateFormat;
import java.util.*;

public class ReservationService {
    private static final ReservationService reference = new ReservationService();
//    private final Map<String, IRoom> roomMap = new HashMap<String, IRoom>();
    private final Set<IRoom> roomMap = new HashSet<>();
    private final Map<Date, Set<IRoom>> bookedRoomMap = new HashMap<Date, Set<IRoom>>();

    private final Map<String, List<Reservation>> reservationMap = new HashMap<>();

    private ReservationService(){

    }
    public static ReservationService getReference(){
        return reference;
    }


    public void addRoom(IRoom room){
        boolean inSet = roomMap.add(room);
        if (!inSet){
            throw new IllegalArgumentException("Room " +room.getRoomNumber()+ " is already exist in the system!");
        }
    }
    public IRoom getARoom(String roomId){
        return findInRoomSet(roomId,roomMap);
    }

    public Collection<IRoom> getAllRoom(){
        return roomMap;
    }
    public Reservation reserveARoom(Customer customer, IRoom room, Date checkInDate, Date checkOutDate){
        ArrayList<Date> dates = getDatesBetween(checkInDate, checkOutDate);
        for(Date date:dates){
            if(bookedRoomMap.containsKey(date)){
                Set<IRoom> bookedRoom = bookedRoomMap.get(date);
                bookedRoom.add(room);
            }
            else{
                Set<IRoom> addBookedRoom = new HashSet<IRoom>();
                addBookedRoom.add(room);
                bookedRoomMap.put(date, addBookedRoom);
            }
        }
        Reservation reservation = new Reservation(customer, room, checkInDate, checkOutDate);
        String customerEmail = customer.getEmail();
        if(reservationMap.containsKey(customerEmail)) {
            List<Reservation> customerReservation = reservationMap.get(customerEmail);
            customerReservation.add(reservation);
        }
        else {
            List<Reservation> customerReservation = new ArrayList<>();
            customerReservation.add(reservation);
            reservationMap.put(customerEmail, customerReservation);
        }
        return reservation;
    }

    private ArrayList<Date> getDatesBetween(Date checkInDate, Date checkoutDate){
        ArrayList<Date> dates = new ArrayList<Date>();
        Calendar calendarStart = Calendar.getInstance();
        calendarStart.setTime(checkInDate);
        while (calendarStart.getTime().before(checkoutDate))
        {
            dates.add(calendarStart.getTime());
            calendarStart.add(Calendar.DATE, 1);
        }
        return dates;
    }
    public Collection<IRoom> findRooms(Date checkInDate, Date checkOutDate){
        Collection<IRoom> allRoom = new ArrayList<>(roomMap);
        ArrayList<Date> dates = getDatesBetween(checkInDate, checkOutDate);
        for(Date date: dates){
            if(bookedRoomMap.containsKey(date)){
                Set<IRoom> bookedRoom = bookedRoomMap.get(date);
                for(IRoom rooms: bookedRoom){
                    allRoom.remove(rooms);
                }
            }
        }
        return allRoom;
    }

    public Collection<Reservation> getCustomersReservation(Customer customer){
        String customerEmail = customer.getEmail();
        if(reservationMap.containsKey(customerEmail)){
            return reservationMap.get(customerEmail);
        }
        else{
            System.out.println("no reservation found");
            return null;
        }
    }

    public void printAReservation(Reservation reservation){
        System.out.println("Reservation:");
        String customerName = reservation.getCustomer().getName();
        System.out.println(customerName);
        System.out.println(reservation.getRoom());
        System.out.println("Checkin Date: "+dateToString(reservation.getCheckInDate()));
        System.out.println("Checkout Date: "+dateToString(reservation.getCheckOutDate()));

    }

    public String dateToString(Date date){
        SimpleDateFormat parseFormat = new SimpleDateFormat("MM/dd/yyyy");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        String dayOfWeek = calendar.getDisplayName( Calendar.DAY_OF_WEEK ,Calendar.LONG, Locale.getDefault());
        String formatDate = parseFormat.format(date);
        return dayOfWeek+" "+formatDate;
    }
    public void printAllReservation(){
        Collection<List<Reservation>> allReservation = reservationMap.values();
        for(List<Reservation> reservationList: allReservation){
            for (Reservation reservation:reservationList){
                printAReservation(reservation);
            }
            System.out.println(" ");
        }
    }

    private IRoom findInRoomSet(String roomNumber, Set<IRoom> set)
    {
            for (IRoom iRoom : set) {
                if (Objects.equals(iRoom.getRoomNumber(), roomNumber))
                    return iRoom;
            }

            return null;

    }


}
