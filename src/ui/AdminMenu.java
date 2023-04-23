package ui;

import api.AdminResource;
import api.HotelResource;
import model.Customer;
import model.IRoom;
import model.Room;
import model.RoomType;

import java.util.*;

public class AdminMenu {
    private static final AdminMenu reference= new AdminMenu();
    private final Scanner scanner = new Scanner(System.in);

    private AdminMenu(){
    }

    public static AdminMenu getReference(){
        return reference;
    }

    private final AdminResource adminResource = AdminResource.getReference();

    public void adminMenuDisplay(){
        System.out.println("Admin Menu");
        System.out.println("\n"+"------------------------------------------------------");
        System.out.println("1. See all Customers");
        System.out.println("2. See all Rooms");
        System.out.println("3. See all Reservations");
        System.out.println("4. Add a room");
        System.out.println("5. Back to Main Menu");
        System.out.println("------------------------------------------------------");
        System.out.println("Please select a number for the menu option");
        getReply();
    }

    public void getReply(){
        String userInput = scanner.nextLine();
        boolean isInt = isInteger(userInput);
        while (!isInt) {
            System.out.println("You must enter a integer number from 1 to 5 ");
            userInput = scanner.nextLine();
            isInt = isInteger(userInput);
        }
        int inputNumber = Integer.parseInt(userInput);
        switch (inputNumber) {
            case 1 -> seeAllCustomers();
            case 2 -> seeAllRooms();
            case 3 -> seeAllReservations();
            case 4 -> addARoom();
            case 5 -> backToMain();
            default -> {
                System.out.println("You must enter a integer number from 1 to 5 ");
                adminMenuDisplay();
            }
        }

    }

    public void backToMain(){
        MainMenu mainMenu = MainMenu.getReference();
        mainMenu.menuDisplay();
    }
    public void seeAllCustomers(){
        Collection<Customer> allCustomers = adminResource.getAllCustomers();
        for(Customer customer:allCustomers){
            System.out.println(customer);
        }
        adminMenuDisplay();
    }
    public void seeAllRooms(){
        Collection<IRoom> allRooms = adminResource.getAllRooms();
        for(IRoom room:allRooms){
            System.out.println(room);
        }
        adminMenuDisplay();
    }
    public void seeAllReservations(){
        adminResource.displayAllReservations();
        adminMenuDisplay();
    }

    public void addARoom(){
        System.out.println("The information whether the rooms are created successfully will be given at the end. ");
        boolean keepAdd = true;
        List<Room> roomToAdd = new ArrayList<>();
        while (keepAdd) {
            System.out.println("Enter room number");
            String roomNumber = scanner.nextLine();
            while(!isInteger(roomNumber)){
                System.out.println("Enter an integer room number");
                roomNumber = scanner.nextLine();
            }
            System.out.println("Enter price per night");
            String price = scanner.nextLine();
            while(!isDouble(price)){
                System.out.println("Enter the price in a double number");
                price = scanner.nextLine();
            }
            Double dPrice = Double.parseDouble(price);
            System.out.println("Enter Room Type: 1 for single bed, 2 for double bed");
            String roomTypeEnum = scanner.nextLine();
            while(!isInteger(roomTypeEnum) || (Integer.parseInt(roomTypeEnum)!=1 && Integer.parseInt(roomTypeEnum)!=2)){
                System.out.println("You can only enter 1 or 2! ");
                roomTypeEnum = scanner.nextLine();
            }
            RoomType roomType = RoomType.SINGLE;
            if (Objects.equals(roomTypeEnum, "1")) {
            } else if (Objects.equals(roomTypeEnum, "2")) {
                roomType = RoomType.Double;
            }
            roomToAdd.add(new Room(roomNumber, dPrice, roomType));
            System.out.println("Would you like to add another a room? y/n");
            String addRoom = scanner.nextLine();
            if (Objects.equals(addRoom, "y")){
            } else if (Objects.equals(addRoom,"n")){
                keepAdd = false;
            }
        }

        adminResource.addRoom(roomToAdd);
        adminMenuDisplay();
    }

    public boolean isInteger( String input ) {
        try {
            Integer.parseInt( input );
            return true;
        }
        catch( NumberFormatException e) {
            return false;
        }
    }

    public boolean isDouble( String input ) {
        try {
            Double.parseDouble(input);
            return true;
        }
        catch( NumberFormatException e) {
            return false;
        }
    }

}
