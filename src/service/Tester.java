package service;

import model.*;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;


public class Tester {
    public static void main(String[] args) {
        CustomerService customerService = CustomerService.getReference();
        customerService.addCustomer("ledago@hotmail.com", "Leda", "Gao");
        customerService.addCustomer("gao18@wfu.com", "FanFan", "Bun");

        Customer Leda = customerService.getCustomer("ledago@hotmail.com");

        Room firstRoom = new Room("211",100.0, RoomType.Double);
        Room room2 = new Room("210",90.0, RoomType.SINGLE);
        Room room3 = new Room("213",90.0, RoomType.SINGLE);
        ReservationService reservationService = ReservationService.getReference();
        reservationService.addRoom(firstRoom);
        reservationService.addRoom(room2);
        reservationService.addRoom(room3);

        Calendar calendarStart = Calendar.getInstance();
        calendarStart.set(2014,4,1);
        Date startdate = calendarStart.getTime();
        Calendar calendarEnd = Calendar.getInstance();
        calendarEnd.set(2014,4,5);
        Date enddate = calendarEnd.getTime();

//        System.out.println(reservationService.dateToString(startdate));

        Calendar calendar3 = Calendar.getInstance();
        calendar3.set(2014,4,3);
        Date reserveDate = calendar3.getTime();
        Reservation reserve1 = reservationService.reserveARoom(Leda,firstRoom,startdate,reserveDate);
        Collection< IRoom > roomFind = reservationService.findRooms(startdate,enddate);

        for(IRoom rooms: roomFind){
            System.out.println(rooms);
        }

//        SimpleDateFormat parseFormat = new SimpleDateFormat("yyyy-MM-dd");

//        List<Date> dates = new ArrayList<Date>();
//        Calendar calendarStart = Calendar.getInstance();
//        calendarStart.set(2014,4,1);
//        Date startdate = calendarStart.getTime();
//        Calendar calendarEnd = Calendar.getInstance();
//        calendarEnd.set(2014,4,5);
//        Date enddate = calendarEnd.getTime();
//
//
//        while (calendarStart.getTime().before(enddate))
//        {
//            Date result = calendarStart.getTime();
//            dates.add(result);
//            calendarStart.add(Calendar.DATE, 1);
//        }
//        for(Date pDate: dates) {
//            System.out.println(pDate);
//        }
    }
}
