package buildings.dwelling;
import interfaces.*;
import exceptions.*;
import java.io.Serializable;
import java.util.Comparator;

public class Flat implements Space, Serializable, Cloneable, Comparable<Space>{

    private int space;
    private int amountOfRoom;

    private final int defAmountOfRoom = 2;
    private final int defSpace = 50;

    //Конструктор по умолчанию создает квартиру из 2 комнат площадью 50 кв.м. (эти числа должны быть константами в классе)
    public Flat(){
        this.amountOfRoom = defAmountOfRoom;
        this.space = defSpace;
    }

    //Конструктор может принимать площадь квартиры (создается квартира с 2 комнатами).
    public Flat(int space)throws InvalidSpaceAreaException{
        if(space > 0) {
            this.space = space;
            this.amountOfRoom = defAmountOfRoom;
        }
        else{
            throw new InvalidSpaceAreaException();
        }
    }

    //Конструктор может принимать площадь квартиры и количество комнат.
    public Flat(int space, int amountOfRoom){
        if(space > 0 && amountOfRoom > 0) {
            this.space = space;
            this.amountOfRoom = amountOfRoom;
        }
        else if (space <= 0){
            throw new InvalidSpaceAreaException();
        }
        else {
            throw new InvalidRoomsCountException();
        }
    }

    //Создайте метод получения количества комнат в квартире.
    public int getAmountOfRooms(){
        return this.amountOfRoom;
    }

    //Создайте метод получения площади квартиры.
    public int getSpace(){
        return this.space;
    }
    //Создайте метод изменения количества комнат в квартире.
    public void setAmountOfRooms(int amountOfRoom){
        if(amountOfRoom == 0 && amountOfRoom < 0) {
            throw new InvalidRoomsCountException();
        }
        this.amountOfRoom = amountOfRoom;
    }
    //Создайте метод изменения площади квартиры.
    public void setSpace(int space){
        if(space == 0 && space < 0){
            throw new InvalidSpaceAreaException();
        }
        this.space = space;
    }


    @Override
    public String toString(){
        String tempString = new String();
        tempString = "Flat " + getAmountOfRooms() + ' ' + getSpace();
        return tempString;
    }


    @Override
    public boolean equals(Object object){
        if(!(object instanceof Flat) ) return false;
        if(this.getSpace() == ((Flat) object).getSpace() && this.getAmountOfRooms() == ((Flat) object).getAmountOfRooms()) return true;
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
