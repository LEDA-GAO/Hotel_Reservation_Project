import model.Room;
import model.RoomType;
import service.CustomerService;
import service.ReservationService;
import ui.MainMenu;

public class HotelApplication {
    public static void main(String[] args) {
        Room firstRoom = new Room("211",100.0, RoomType.Double);
        Room room2 = new Room("210",90.0, RoomType.SINGLE);
        ReservationService reservationService = ReservationService.getReference();
        reservationService.addRoom(firstRoom);
        reservationService.addRoom(room2);
        CustomerService customerService = CustomerService.getReference();
        customerService.addCustomer("obiwan@hotmail.com", "ObiWan", "Kenobi");
        MainMenu mainMenu = MainMenu.getReference();
        mainMenu.menuDisplay();
    }
}