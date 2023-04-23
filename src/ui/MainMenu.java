package ui;

import api.HotelResource;
import model.IRoom;
import model.Reservation;
import model.Room;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Pattern;


public class MainMenu {
    private static final MainMenu reference= new MainMenu();
    private final Scanner scanner = new Scanner(System.in);
    private MainMenu(){
    }

    public static MainMenu getReference(){
        return reference;
    }
    private final HotelResource hotelResource = HotelResource.getReference();
    private final AdminMenu adminMenu = AdminMenu.getReference();

    public void  menuDisplay(){
        System.out.println("Welcome to Hotel Reservation Application! (^-^)");
        System.out.println("\n"+"------------------------------------------------------");
        System.out.println("1. Find and reserve a room");
        System.out.println("2. See my reservation");
        System.out.println("3. Create an account");
        System.out.println("4. Admin");
        System.out.println("5. Exit");
        System.out.println("------------------------------------------------------");
        System.out.println("Please select a number for the menu option");
        getReply();
    }

    public void getReply() {
        String userInput = scanner.nextLine();
        boolean isInt = isInteger(userInput);
        while (!isInt) {
            System.out.println("You must enter a integer number from 1 to 5 ");
            userInput = scanner.nextLine();
            isInt = isInteger(userInput);
        }
        int inputNumber = Integer.parseInt(userInput);
        switch (inputNumber) {
            case 1 -> findAndReserveRoom();
            case 2 -> seeMyReservation();
            case 3 -> createAnAccount();
            case 4 -> admin();
            case 5 -> System.exit(0);
            default -> {
                System.out.println("You must enter a integer number from 1 to 5 ");
                menuDisplay();
            }
        }

    }
    public void findAndReserveRoom(){
        Calendar todayCalendar = Calendar.getInstance();
        Date today = todayCalendar.getTime();
        System.out.println("Enter checkIn Date mm/dd/yyyy example 04/20/2023");
        String checkIn = scanner.nextLine();
        while(!checkValidDate(checkIn)){
            checkIn = scanner.nextLine();
        }
        Date checkInDate = convertStringToDate(checkIn);
        while(checkInDate.compareTo(today)<0){
            System.out.println("CheckIn date cannot be earlier than today's date. ");
            checkIn = scanner.nextLine();
            while(!checkValidDate(checkIn)){
                checkIn = scanner.nextLine();
            }
            checkInDate = convertStringToDate(checkIn);
        }

        System.out.println("Enter checkOut Date mm/dd/yyyy example 04/24/2023");
        String checkOut = scanner.nextLine();
        while(!checkValidDate(checkOut)){
            checkOut = scanner.nextLine();
        }
        Date checkOutDate = convertStringToDate(checkOut);
        while (checkOutDate.compareTo(checkInDate)<=0){
            System.out.println("CheckOut date must be later than checkIn date. ");
            checkOut = scanner.nextLine();
            while(!checkValidDate(checkOut)){
                checkOut = scanner.nextLine();
            }
            checkOutDate = convertStringToDate(checkOut);
        }

        Collection<IRoom> freeRoomList = hotelResource.findARoom(checkInDate, checkOutDate);
        for(IRoom eachRoom:freeRoomList){
            System.out.println(eachRoom);
        }

        System.out.println("Would you like to book a room? y/n");
        String bookRoom = scanner.nextLine();
        while(!checkYesNo(bookRoom)){
            System.out.println("Please only enter y or n");
            bookRoom = scanner.nextLine();
        }
        if (Objects.equals(bookRoom, "y")){
            System.out.println("Do you have an account with us? y/n");
            String haveAccount = scanner.nextLine();
            while(!checkYesNo(haveAccount)){
                System.out.println("Please only enter y or n");
                haveAccount = scanner.nextLine();
            }
            if (Objects.equals(haveAccount,"y")){
                System.out.println("Enter your email in this format: name@domain.com");
                String customerEmail = scanner.nextLine();
                customerEmail = checkValidEmail(customerEmail);
                while(hotelResource.getCustomer(customerEmail)==null){
                    System.out.println("Please enter a valid email");
                    customerEmail = scanner.nextLine();
                    customerEmail = checkValidEmail(customerEmail);
                }
                System.out.println("What room number would you like to reserve? ");
                String roomNumber = scanner.nextLine();
                IRoom roomToBook = hotelResource.getRoom(roomNumber);
                boolean roomAvailable = false;
                while(!roomAvailable) {
                    while (!isInteger(roomNumber)) {
                        System.out.println("Please enter a valid room number");
                        roomNumber = scanner.nextLine();
                    }
                    roomToBook = hotelResource.getRoom(roomNumber);
                    for (IRoom freeRoom : freeRoomList) {
                        roomAvailable = freeRoom.equals(roomToBook);
                        if (roomAvailable) break;
                    }
                    if(!roomAvailable) {
                        System.out.println("The room number your entered is not available. Please reenter room number");
                        roomNumber = scanner.nextLine();
                    }
                }

                Reservation thisReservation = hotelResource.bookARoom(customerEmail, roomToBook, checkInDate, checkOutDate);
                hotelResource.printAReservation(thisReservation);
                System.out.println("Your room is reserved successfully!");
                menuDisplay();
            } else if (Objects.equals(haveAccount,"n")) {
                System.out.println("Please create a new account");
                createAnAccount();
            }
        } else if (Objects.equals(bookRoom, "n")) {
            menuDisplay();
        }


    }

    public void seeMyReservation(){
        System.out.println("Enter your email in this format: name@domain.com");
        String customerEmail = scanner.nextLine();
        customerEmail = checkValidEmail(customerEmail);
        while(hotelResource.getCustomer(customerEmail)==null){
            System.out.println("Please enter a valid email");
            customerEmail = scanner.nextLine();
            customerEmail = checkValidEmail(customerEmail);
        }
        System.out.println("Your reservations are list below. ");
        Collection<Reservation> myReservation = hotelResource.getCustomersReservations(customerEmail);
        for(Reservation reservation: myReservation){
            hotelResource.printAReservation(reservation);
        }
        menuDisplay();

    }

    public void createAnAccount(){
        System.out.println("Enter your email in this format: name@domain.com");
        String customerEmail = scanner.nextLine();
        customerEmail = checkValidEmail(customerEmail);
        while(hotelResource.getCustomer(customerEmail)!=null){
            System.out.println("This email has already been used. Please try another email.");
            customerEmail = scanner.nextLine();
            customerEmail = checkValidEmail(customerEmail);
        }
        System.out.println("This email is good!");
        System.out.println("First Name: ");
        String firstName = scanner.nextLine();
        System.out.println("Last Name: ");
        String lastName = scanner.nextLine();
        try {
            hotelResource.createACustomer(customerEmail, firstName, lastName);
        }catch (IllegalArgumentException exception){
            System.out.println(exception.getLocalizedMessage());
            System.out.println("Please try again to create an account! ");
            menuDisplay();
        }
        System.out.println("Your account was created successfully! ");
        menuDisplay();
    }

    public void admin(){
        adminMenu.adminMenuDisplay();
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
    public String checkValidEmail(String customerEmail){
        String emailRegex = "^(.+)@(.+).com$";
        Pattern pattern = Pattern.compile(emailRegex);
        while(!pattern.matcher(customerEmail).matches()){
            System.out.println("Your email address is invalid! ");
            System.out.println("Please enter again in this format: name@domain.com");
            customerEmail = scanner.nextLine();
        }
        return customerEmail;
    }

    public Date convertStringToDate(String date){
        String[] tokens = date.split("/");
        int month = Integer.parseInt(tokens[0]);
        int day = Integer.parseInt(tokens[1]);
        int year = Integer.parseInt(tokens[2]);
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month-1,day);
        calendar.set(Calendar.MILLISECOND, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.HOUR_OF_DAY, 11);
        return calendar.getTime();
    }

    public boolean checkYesNo(String reply){
        if(Objects.equals(reply,"y") || Objects.equals(reply,"n")){
            return true;
        }
        else{
            return false;
        }
    }
    public boolean checkValidDate(String date){
//        String dateRegex = "^(.+)/(.+)/(.+)$";
//        Pattern pattern = Pattern.compile(dateRegex);
        SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
        format.setLenient(false);
        try {
            format.parse(date);
            return true;
        }
        catch(ParseException e){
            System.out.println("The date you enter is invalid.");
            System.out.println("Please reenter with format mm/dd/yyyy example 04/20/2023");
            return false;
        }
    }
}
