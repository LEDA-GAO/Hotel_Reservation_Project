package ui;

import api.AdminResource;
import api.HotelResource;
import model.*;
import service.CustomerService;
import service.ReservationService;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

public class Tester {

    public static void main(String[] args) {
        Room firstRoom = new Room("211",100.0, RoomType.Double);
        Room room2 = new Room("210",90.0, RoomType.SINGLE);
        ReservationService reservationService = ReservationService.getReference();
        reservationService.addRoom(firstRoom);
        reservationService.addRoom(room2);
        CustomerService customerService = CustomerService.getReference();
        customerService.addCustomer("ledago@hotmail.com", "Leda", "Gao");
        customerService.addCustomer("gao18@wfu.com", "FanFan", "Bun");


        Calendar calendarStart = Calendar.getInstance();
        calendarStart.set(2024,4,1);
        Date startdate = calendarStart.getTime();
        Calendar calendarEnd = Calendar.getInstance();
        calendarEnd.set(2024,4,5);
        Date enddate = calendarEnd.getTime();
        Calendar calendar3 = Calendar.getInstance();
        calendar3.set(2024,4,3);
        Date reserveDate = calendar3.getTime();

//        HotelResource hotelResource = HotelResource.getReference();
//        System.out.println(hotelResource.getCustomer("leda@wfu.com"));
//        IRoom roomToBook = hotelResource.getRoom("210");
//        Reservation reservation = hotelResource.bookARoom("ledagao@hotmail.com",roomToBook,startdate,reserveDate);
//        hotelResource.printAReservation(reservation);
//        Collection<IRoom> roomFind = hotelResource.findARoom(startdate,enddate);

//        for(IRoom rooms: roomFind){
//            System.out.println(rooms);
//        }
//
//        AdminResource adminResource = AdminResource.getReference();
//        Collection<IRoom> allRooms = adminResource.getAllRooms();
//        System.out.println();
//        for(IRoom room:allRooms){
//            System.out.println(room);
//        }
//
//        System.out.println();
//        roomFind = hotelResource.findARoom(startdate,enddate);
//
//        for(IRoom rooms: roomFind){
//            System.out.println(rooms);
//        }
        MainMenu mainMenu = MainMenu.getReference();
        mainMenu.menuDisplay();

//        System.out.println(mainMenu.checkValidDate("2/2/2023"));
//        SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
//        Calendar calendar = Calendar.getInstance();
//
//        System.out.println(format.format(calendar.getTime()));



    }

}
