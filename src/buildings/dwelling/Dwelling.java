package buildings.dwelling;
import exceptions.*;
import interfaces.*;

import java.io.Serializable;
import java.util.Iterator;

/*Создайте публичный класс buildings.Dwelling жилого здания,
основанный на массиве этажей здания.*/

public class Dwelling implements Building, Serializable, Cloneable, Iterable<Floor> {

    private Floor[] floorArray;

    //Конструктор может принимать количество этажей и массив количества квартир по этажам.
    public Dwelling(int[] amountOfFlat){
        this.floorArray = new Floor[amountOfFlat.length];
        for (int i = 0; i < floorArray.length + 1; i++){
            this.floorArray[i] = new DwellingFloor(amountOfFlat[i]);
        }

    }

    //Конструктор может принимать массив этажей дома.
    public Dwelling(Floor[] dataArray){
        this.floorArray = new Floor[dataArray.length];
        for (int i = 0; i < dataArray.length; i++) {
            this.floorArray[i] = dataArray[i];
        }
    }


    //Создайте метод получения общего количества этажей дома.
    public int getAmountOfFloors(){
        return floorArray.length;
    }

    //Создайте метод получения общего количества квартир дома.
    public int getAmountOfPlaces(){
        int totalAmountOfFlat = 0;
        for (Floor floor : getFloorsArray()) {
            totalAmountOfFlat += floor.getAmountOfPlacesOnFloor();
        }
        return totalAmountOfFlat;
    }

    //Создайте метод получения общей площади квартир дома.
    public int getTotalSpaceOfPlaces(){
        int totalSpace= 0;
        for (Floor floor : getFloorsArray()) {
            totalSpace += floor.getTotalSpaceOnFloor();
        }
        return totalSpace;
    }

    //Создайте метод получения общего количества комнат дома.
    public int getAmountOfPlacesRooms(){
        int totalAmountOfRoom = 0;
        for (Floor floor : getFloorsArray()) {
            totalAmountOfRoom += floor.getTotalAmountOfRoomsOnFloor();
        }
        return totalAmountOfRoom;
    }

    //Создайте метод получения массива этажей жилого дома.
    public Floor[] getFloorsArray(){
        return floorArray;
    }

    //Создайте метод получения объекта этажа, по его номеру в доме.
    public Floor getFloorByNum (int num){
        if(num > floorArray.length && num < 0){
            throw new FloorIndexOutOfBoundsException();
        }
        return floorArray[num];
    }

    //Создайте метод изменения этажа по его номеру в доме и ссылке на обновленный этаж.
    public void setFloorByNum(int num, Floor newFloor){
        if(num > floorArray.length && num < 0){
            throw new FloorIndexOutOfBoundsException();
        }
        floorArray[num] = newFloor;
    }

    // Создайте метод получения объекта квартиры по ее номеру в доме.
    public Space getPlaceByNum(int num){
        if(num < 0){
            throw new SpaceIndexOutOfBoundsException();
        }
        int numberOfFlat = 0;
        for(Floor floor : getFloorsArray()){
            for(Space space : floor.getPlacesArrayOnFloor()){
                numberOfFlat++;
                if(numberOfFlat == num) return floor.getPlaceOnFloorByNum(num);
            }
        }
        return null;
    }

    //Создайте метод изменения объекта квартиры по ее номеру в доме и ссылке на объект квартиры.
    public void setPlaceByNum(int num, Space newFlat){
        if(num < 0){
            throw new SpaceIndexOutOfBoundsException();
        }
        int numberOfFlat = 0;
        for(int i = 0; i < floorArray.length; i++){
            for(int j = 0; j < floorArray[i].getAmountOfPlacesOnFloor(); j++){
                numberOfFlat++;
                if(numberOfFlat == num) {
                    floorArray[i].setPlaceOnFloorByNum(j, newFlat);
                }
            }
        }
        if(num > numberOfFlat){
            throw new SpaceIndexOutOfBoundsException();
        }
    }

    /*Создайте метод добавления квартиры в дом по будущему номеру квартиры в доме
    (т.е. в параметрах указывается номер,
    который должны иметь квартира после вставки и ссылке на объект квартиры
    (количество этажей в доме при этом не увеличивается).*/

    public void addPlaceByNum (int num, Space newFlat){
        if(num < 0){
            throw new SpaceIndexOutOfBoundsException();
        }
        int numberOfFlat = 0;
        for (int i = 0; i < floorArray.length; i++) {
            for (int j = 0; j < floorArray[i].getAmountOfPlacesOnFloor(); j++) {
                numberOfFlat++;
                if (num == numberOfFlat) {
                    continue;
                } else if (num > numberOfFlat) {
                    floorArray[floorArray.length - 1].addPlaceOnFloorByNum(num, newFlat);
                }
            }
        }
        if(num > numberOfFlat){
            throw new SpaceIndexOutOfBoundsException();
        }
    }
    //Создайте метод удаления квартиры по ее номеру в доме.
    public void delPlaceByNum(int num){
        if(num < 0){
            throw new SpaceIndexOutOfBoundsException();
        }
        int numberOfFlat = 0;
        for (int i = 0; i < floorArray.length; i++) {
            for (int j = 0; j < floorArray[i].getAmountOfPlacesOnFloor(); j++) {
                numberOfFlat++;
                if (numberOfFlat == num) {
                    floorArray[i].delPlaceOnFloorByNum(j);
                }
            }
        }
        if(num > numberOfFlat){
            throw new SpaceIndexOutOfBoundsException();
        }
    }

    //Создайте метод getBestSpace() получения самой большой по площади квартиры дома.
    public Space getBestSpace(){
        Space tempFlat = floorArray[0].getBestSpace();
        int i;
        for (Floor floor : getFloorsArray()) {
            for (Space space : floor.getPlacesArrayOnFloor()) {
                if (tempFlat.getSpace() < floor.getBestSpace().getSpace()) {
                    tempFlat = floor.getBestSpace();
                }
            }
        }
        return tempFlat;
    }

    //Создайте метод получения отсортированного по убыванию площадей массива квартир.
    public Space[] getSortedArray() {
        Space[] sortedFlatArray = new Space[getAmountOfPlaces()];
        Space tempFlat;
        int num = 0;
        for (int i = 1; i < floorArray.length; i++) {
            for (int j = 0; j < floorArray[i].getAmountOfPlacesOnFloor(); j++) {
                sortedFlatArray[num] = floorArray[i].getPlaceOnFloorByNum(j);
                num++;
            }
        }
        for (int i = 0; i < sortedFlatArray.length - 1; i++) {
            for (int j = 0; j < sortedFlatArray.length - 1; j++) {
                if (sortedFlatArray[j].getSpace() < sortedFlatArray[j + 1].getSpace()) {
                    tempFlat = sortedFlatArray[j];
                    sortedFlatArray[j] = sortedFlatArray[j + 1];
                    sortedFlatArray[j + 1] = tempFlat;
                }
            }
        }
        return sortedFlatArray;
    }


    //методы из лабы №5
    @Override
    public String toString(){
        String tempString = new String();
        tempString = "Dwelling" + floorArray.length + ",";
        for (int i = 0; i < floorArray.length; i++) {
            tempString += getAmountOfPlaces() + "," + "DwellingFloor" + "(";
            for (int j = 0; j < getFloorByNum(i).getAmountOfPlacesOnFloor(); j++) {
                tempString += " " + getPlaceByNum(j).getAmountOfRooms() + " " + getPlaceByNum(j).getSpace();
            }
            tempString += ")";
        }
        return tempString;
    }

    @Override
    public boolean equals(Object object){
        if(!(object instanceof Dwelling) ) return false;
        if(((Dwelling) object).getAmountOfFloors() == floorArray.length){
            for (int i = 0; i < floorArray.length; i++) {
                if(!object.equals(getFloorByNum(i))) return false;
                for (int j = 0; j < floorArray[i].getAmountOfPlacesOnFloor(); j++) {
                   if(!object.equals(floorArray[i].getPlaceOnFloorByNum(j))) return false;
                }
            }
            return true;
        }
        else return false;
    }

    @Override
    public int hashCode() {
        int prime = 29;
        Integer amountOfRoom = Integer.parseInt(Integer.toBinaryString(getAmountOfPlacesRooms()));
        Integer space = Integer.parseInt(Integer.toBinaryString(getTotalSpaceOfPlaces()));
        return prime * amountOfRoom ^ space;
    }

    @Override
    public Object clone() throws CloneNotSupportedException{
        Dwelling dwelling = null;
        try{
            dwelling = (Dwelling) super.clone();
            dwelling.floorArray = floorArray.clone();
            for (int i = 0; i < floorArray.length; i++) {
                dwelling.floorArray[i] = (DwellingFloor) this.floorArray[i].clone();
            }
        }
        catch (CloneNotSupportedException e){};
        return dwelling;
    }

    public Iterator<Floor> iterator() {
        return new BuildingIterator();
    }

    public class BuildingIterator implements Iterator<Floor> {

        int position;

        @Override
        public boolean hasNext() {
            return  position < getAmountOfFloors();
        }

        @Override
        public Floor next() {
            return getFloorByNum(position++);
        }
    }

}
