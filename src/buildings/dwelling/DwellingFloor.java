package buildings.dwelling;
import interfaces.*;
import exceptions.*;

import java.io.Serializable;
import java.util.Comparator;
import java.util.Iterator;

/*Создайте публичный класс buildings.DwellingFloor этажа жилого здания,
основанный на массиве квартир.*/
public class DwellingFloor implements Floor, Serializable, Cloneable, Iterable<Space>{

    private Space[] flatArray;

    //Конструктор может принимать количество квартир на этаже.
    public DwellingFloor(int amountOfFlat){
        flatArray = new Flat[amountOfFlat];
        for (int i = 0; i < amountOfFlat; i++) {
            flatArray[i] = new Flat();
        }
    }

    //Конструктор может принимать массив квартир этажа.
    public DwellingFloor(Space[] flatArray){
        this.flatArray = new Flat[flatArray.length];
        for (int i = 0; i < flatArray.length; i++) {
            this.flatArray[i] = flatArray[i];
        }

    }

    //Создайте метод получения количества квартир на этаже.
    public int getAmountOfPlacesOnFloor(){
        return flatArray.length;
    }

    //Создайте метод получения общей площади квартир этажа.
    public int getTotalSpaceOnFloor(){
        int totalSpace = 0;
        for (Space space : getPlacesArrayOnFloor()){
            totalSpace += space.getSpace();
        }
        return totalSpace;
    }

    //Создайте метод получения общего количества комнат этажа.
    public int getTotalAmountOfRoomsOnFloor(){
        int totalAmountOfRoom = 0;
        for (Space space : getPlacesArrayOnFloor()){
            totalAmountOfRoom += space.getAmountOfRooms();
        }
        return totalAmountOfRoom;
    }

    //Создайте метод получения массива квартир этажа.
    public Space[] getPlacesArrayOnFloor(){
        Space[] flatArray = new Space[getAmountOfPlacesOnFloor()];
        for (int i = 0; i < getAmountOfPlacesOnFloor(); i++) {
            flatArray[i] = getPlaceOnFloorByNum(i);
        }
        return flatArray;
    }

    //Создайте метод получения объекта квартиры по ее номеру на этаже.

    public Space getPlaceOnFloorByNum(int num) {
        if(num > flatArray.length && num <= 0) {
           throw new SpaceIndexOutOfBoundsException();
        }
        return flatArray[num];
    }

    //Создайте метод изменения квартиры по ее номеру на этаже и ссылке на новую квартиру.
    public void setPlaceOnFloorByNum(int num, Space newFlat){
        if(num >= flatArray.length && num < 0) {
            throw new SpaceIndexOutOfBoundsException();
        }
        flatArray[num] = newFlat;
    }

    /*Создайте метод добавления новой квартиры на этаже по будущему номеру квартиры
    (т.е. в параметрах указывается номер,
    который должны иметь квартира после вставки) и ссылке на объект квартиры.*/

    public void addPlaceOnFloorByNum(int num, Space data){
        if(num >= flatArray.length && num < 0) {
            throw new SpaceIndexOutOfBoundsException();
        }
        Space[] newFlatArray = new Space[flatArray.length+1];
        System.arraycopy(flatArray, 0, newFlatArray, 0, num);
        newFlatArray[num] = data;
        System.arraycopy(flatArray, num, newFlatArray, num + 1, flatArray.length - num);
        flatArray = new Space[flatArray.length+1];
        flatArray = newFlatArray;
    }
    //Создайте метод удаления квартиры по ее номеру на этаже.
    public void delPlaceOnFloorByNum(int num){
        if(num >= flatArray.length && num < 0) {
            throw new SpaceIndexOutOfBoundsException();
        }
        Space[] newFlatArray = new Space[flatArray.length - 1];
        for (int i = 0; i < newFlatArray.length; i++){
            if (i == num) {
                continue;
            }
            else if (i < num){
                newFlatArray[i] = flatArray[i];
            }
            else {
                newFlatArray[i] = flatArray[i+1];
            }
        }
        flatArray = newFlatArray;
    }

    //Создайте метод getBestSpace() получения самой большой по площади квартиры этажа.
    public Space getBestSpace(){
        Space tempPlace = flatArray[0];
        for (Space space : getPlacesArrayOnFloor()){
            if(tempPlace.getSpace() > space.getSpace()){
                tempPlace = space;
            }
        }
        return tempPlace;
    }


    //методы из лабы №5
    @Override
    public String toString(){
        String tempString = new String();
        tempString = "DwellingFloor " + flatArray.length + ",";
        for (Space space : getPlacesArrayOnFloor()) {
            tempString += " " + space.getAmountOfRooms() + " " + space.getSpace();
        }
        return tempString;
    }

    @Override
    public boolean equals(Object object){
        if(!(object instanceof DwellingFloor) ) return false;
        if(((DwellingFloor) object).getAmountOfPlacesOnFloor() == flatArray.length){
            for (Space space : getPlacesArrayOnFloor()) {
                if(!object.equals(space)) return false;
            }
            return true;
        }
        else return false;
    }

    @Override
    public int hashCode() {
        int prime = 29;
        Integer amountOfRoom = Integer.parseInt(Integer.toBinaryString(getTotalAmountOfRoomsOnFloor()));
        Integer space = Integer.parseInt(Integer.toBinaryString(getTotalSpaceOnFloor()));
        return prime * amountOfRoom ^ space;
    }

    @Override
    public Object clone() throws CloneNotSupportedException{
        DwellingFloor dwellingFloor = null;
                try{
                    dwellingFloor = (DwellingFloor) super.clone();
                    dwellingFloor.flatArray = flatArray.clone();
                    for (int i = 0; i < flatArray.length; i++) {
                        dwellingFloor.flatArray[i] = (Space) this.flatArray[i].clone();
                    }
                }
                catch (CloneNotSupportedException e){};
        return dwellingFloor;
    }

    public Iterator<Space> iterator() {
        return new FloorIterator();
    }

    public class FloorIterator implements Iterator<Space> {

        int position;

        @Override
        public boolean hasNext() {
            return  position < getAmountOfPlacesOnFloor();
        }

        @Override
        public Space next() {
            return getPlaceOnFloorByNum(position++);
        }
    }


    public int compareTo(Floor o) {
        if(this.getAmountOfPlacesOnFloor() > o.getAmountOfPlacesOnFloor()) return -1;
        else if(this.getAmountOfPlacesOnFloor() > o.getAmountOfPlacesOnFloor()) return 1;
        else return 0;
    }

    public static class FloorComparator implements Comparator<Floor> {
        @Override
        public int compare(Floor data1, Floor data2) {
            if (data1.getTotalAmountOfRoomsOnFloor() < data2.getTotalAmountOfRoomsOnFloor()) { return 1; }
            else if (data1.getTotalAmountOfRoomsOnFloor() > data2.getTotalAmountOfRoomsOnFloor()) { return -1; }
            else return 0;
        }
    }
}

