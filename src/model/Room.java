package model;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Room implements IRoom{
    private final String roomNumber;
    protected Double price;
    private final RoomType enumeration;

    public boolean freeRoom;

    public Room(String roomNumber, Double price, RoomType enumeration){
        this.roomNumber = roomNumber;
        this.price = price;
        this.enumeration = enumeration;
        if(price == 0.0){
            freeRoom = true;
        }
    }
    @Override
    public String getRoomNumber(){
        return roomNumber;
    }

    @Override
    public Double getRoomPrice(){
        return price;
    }

    @Override
    public RoomType getRoomType(){
        return enumeration;
    }

    @Override
    public boolean isFree(){
        return freeRoom;
    }

    @Override
    public boolean equals(Object obj){
        if(obj == this){
            return true;
        }
        if (!(obj instanceof Room)) {
            return false;
        }
        Room room = (Room) obj;
        return Objects.equals(room.roomNumber,this.roomNumber);
    }

    @Override
    public int hashCode(){
        return Integer.parseInt(this.roomNumber);
    }

    @Override
    public String toString(){
        return "roomNumber: "+roomNumber+", price per night: "+price+", RoomType: "+enumeration;
    }

}
