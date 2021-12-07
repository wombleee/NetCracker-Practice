package buildings.officeBuilding;
import interfaces.*;
import exceptions.*;

import java.util.Comparator;

public class Office implements Space, Comparable<Space> {
    static final int defSpace = 250;
    static final int defRooms = 1;

    private int space, rooms;

    public Office() {
        this(defSpace, defRooms);
    }
    public Office(int space) {
        this(space, defRooms);
    }
    public Office(int space, int rooms){
        if (space > 0 && rooms > 0) {
            this.space = space;
            this.rooms = rooms;
        }
        else if (rooms <= 0) {
            throw new InvalidRoomsCountException();
        }
        else {
            throw new InvalidSpaceAreaException();
        }
    }

    public int getSpace() {
        return space;
    }
    public void setSpace(int space) {
        if (space <= 0) {
            throw new InvalidSpaceAreaException();
        }
        this.space = space;
    }
    public int getAmountOfRooms() {
        return rooms;
    }
    public void setAmountOfRooms(int rooms){
        if (rooms <= 0) {
            throw new InvalidRoomsCountException();
        }
        this.rooms = rooms;
    }

    @Override
    public String toString(){
        String tempString = new String();
        tempString = "Office " + getAmountOfRooms() + ' ' + getSpace();
        return tempString;
    }

    @Override
    public boolean equals(Object object){
        if(!(object instanceof Office) ) return false;
        if(this.getSpace() == ((Office) object).getSpace() && this.getAmountOfRooms() == ((Office) object).getAmountOfRooms()) return true;
        else return false;
    }


    @Override
    public int hashCode() {
        int prime = 29;
        Integer amountOfRoom = Integer.parseInt(Integer.toBinaryString(getAmountOfRooms()));
        Integer space = Integer.parseInt(Integer.toBinaryString(getSpace()));
        return prime * amountOfRoom ^ space;
    }

    @Override
    public Object clone() throws CloneNotSupportedException{
        return super.clone();
    }

    @Override
    public int compareTo(Space o) {
        if(this.getSpace() > o.getSpace()) return -1;
        else if(this.getSpace() < o.getSpace()) return 1;
        else return 0;
    }
    public static class SpaceComparator implements Comparator<Space> {
        @Override
        public int compare(Space data1, Space data2) {
            if(data2.getAmountOfRooms() < data1.getAmountOfRooms()) return 1;
            else if (data2.getAmountOfRooms() > data1.getAmountOfRooms()) return -1;
            else return 0;
        }
    }
}







