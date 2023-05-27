# Hotel Reservation Application Project

This is the hotel reservation application project written by Java. 

## Install and use this hotel reservation app 

You can open this whole package in IntelliJ IDEA project. All the source codes are in the folder `src`.

The excutable `hotel reservation.jar` file is in the directory `/hotel reservation/out/artifacts/hotel_reservation_jar`. 

If Java has been installed on your machine, you can run the jar file directly by command `java -jar hotel\ reservation.jar` in your terminal. 

You check if Java is installed on your machine by `java --version`. 

## Usage 

If you run the jar file successfully, you will see a menu in the terminal. It has 5 main feature listed below. 
```bash
Welcome to Hotel Reservation Application! (^-^)  

------------------------------------------------------   
1. Find and reserve a room    
2. See my reservation 
3. Create an account   
4. Admin  
5. Exit  
------------------------------------------------------  
Please select a number for the menu option 
```

If you choose number 4 and enter in the Admin menu, there are 5 other feature to help administrator organize this hotel. 

```bash
Admin Menu  

------------------------------------------------------ 
1. See all Customers   
2. See all Rooms
3. See all Reservations 
4. Add a room 
5. Back to Main Menu  
------------------------------------------------------ 
Please select a number for the menu option 
```

## Application Source Code Structure

The `src` folder has 4 subfolders to organize the codes. They are organized to mimic a real world application from backend to frontend. 

The `\src\model` provides all the backend classes used for saving the customer, room, and reservation information. 

The `\src\service` provides the backend service to save, organize, and retrieve the data. Data structures such as Hash Map, Hash Set, Array are used to organize and help retrieving the data faster. 

The `\src\api` provides the api(application programming interface) routines to connect the backend data to the front end. 

The `\src\ui` serves as user interface source code to help users communicate with the backend date. 


