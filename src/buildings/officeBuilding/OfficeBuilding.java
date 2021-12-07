package buildings.officeBuilding;
import buildings.dwelling.Dwelling;
import interfaces.*;
import exceptions.*;

import java.io.Serializable;
import java.util.Iterator;

public class OfficeBuilding implements Building, Serializable, Cloneable, Iterable<Floor> {

    private Node head = null;
    private Node tail = null;
    private int size = 0;

    private class Node {
        private Node next;
        private Node prev;
        private Floor data;

        public Node (Floor data) {
            next = null;
            prev = null;
            this.data = data;
        }
    }
    private Node getNodeByNum(int num) {
        Node temp = head;
        for (int i = 0; i < num; i++) {
            temp = temp.next;
        }
        return temp;
    }
    private void addNodeByNum(int num, Floor data) {
        if (head == null && num == 0) {
            head = new Node (data);
            size++;
        }
        else if (num == 0) {
            Node temp = head;
            head.data = data;
            head.next = temp;
            temp.next.prev = temp;
            temp.prev = head;
            size++;
        }
        else if(num == size) {
            Node temp = head;
            for (int i = 0; i < num - 1; i++) {
                temp = temp.next;
            }
            tail = new Node(data);
            temp.next = tail;
            temp.next.prev = temp;
            tail.next = head;
            size++;
        }
        else {
            Node temp = head;
            for (int i = 0; i < num - 1; i++) {
                temp = temp.next;
            }
            Node superTemp = new Node(data);
            superTemp = temp.next;
            temp.next = superTemp;
            superTemp.next.prev = superTemp;
            superTemp.prev = temp;
            size++;
        }
    }
    private void delNodeByNum(int num) {
        if (num == 0) {
            head = head.next;
            head.prev = null;
            size--;
        }
        else if (num == size - 1) {
            Node temp = head;
            for (int i = 0; i < num - 1; i++) {
                temp = temp.next;
            }
            temp.next = null;
            size--;
        }
        else {
            Node temp = head;
            for (int i = 0; i < num - 1; i++) {
                temp = temp.next;
            }
            temp.next = temp.next.next;
            temp.next.next.prev = temp;
            size--;
        }
    }

    public OfficeBuilding(int[] amountOfOfficesOnFloors) {
        Space temp = new Office();
        for (int i = 0; i < amountOfOfficesOnFloors.length; i++) {
            Floor tempFloor = new OfficeFloor(amountOfOfficesOnFloors[i]);
            addNodeByNum(i, tempFloor);
        }
    }
    public OfficeBuilding(Floor[] building) {
        for (int i = 0; i < building.length; i++) {
            addNodeByNum(i, building[i]);
        }
    }

    public int getAmountOfFloors() { return size; }
    public int getAmountOfPlaces() {
        int totalOffices = 0;
        for (int i = 0; i < size; i++) {
            totalOffices += getNodeByNum(i).data.getAmountOfPlacesOnFloor();
        }
        return totalOffices;
    }
    public int getTotalSpaceOfPlaces() {
        int totalSquare = 0;
        for (int i = 0; i < size; i++) {
            totalSquare += getNodeByNum(i).data.getTotalSpaceOnFloor();
        }
        return totalSquare;
    }
    public int getAmountOfPlacesRooms() {
        int totalRooms = 0;
        for (int i = 0; i < size; i++) {
            totalRooms += getNodeByNum(i).data.getTotalAmountOfRoomsOnFloor();
        }
        return totalRooms;
    }
    public Floor[] getFloorsArray() {
        Floor[] temp = new Floor[size];
        for (int i = 0; i < size; i++) {
            temp[i] = getNodeByNum(i).data;
        }
        return temp;
    }
    public Floor getFloorByNum(int num) {
        if ((num >= getAmountOfFloors()) || (num < 0)) {
            throw new FloorIndexOutOfBoundsException();
        }
        return getNodeByNum(num).data;
    }
    public void setFloorByNum(int num, Floor data) {
        if ((num >= getAmountOfFloors()) || (num < 0)) {
            throw new FloorIndexOutOfBoundsException();
        }
        getNodeByNum(num).data = data;
    }

    public Space getPlaceByNum(int num) {
        if ((num >= getAmountOfPlaces()) || (num < 0)) {
            throw new SpaceIndexOutOfBoundsException();
        }
        int numOfOffice = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < getNodeByNum(i).data.getAmountOfPlacesOnFloor(); j++) {
                if (numOfOffice == num) {
                    return getNodeByNum(i).data.getPlaceOnFloorByNum(j);
                }
                numOfOffice++;
            }
        }
        return null;
    }
    public void setPlaceByNum(int num, Space data) {
        if ((num >= getAmountOfPlaces()) || (num < 0)) {
            throw new SpaceIndexOutOfBoundsException();
        }
        int numOfOffice = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < getNodeByNum(i).data.getAmountOfPlacesOnFloor(); j++) {
                if (numOfOffice == num) {
                    getNodeByNum(i).data.setPlaceOnFloorByNum(j, data);
                }
                numOfOffice++;
            }
        }
    }
    public void addPlaceByNum(int num, Space data) {
        if ((num >= getAmountOfPlaces()) || (num < 0)) {
            throw new SpaceIndexOutOfBoundsException();
        }
        else {
            int numOfOffice = 0;
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < getNodeByNum(i).data.getAmountOfPlacesOnFloor(); j++) {
                    if (numOfOffice == num) {
                        getNodeByNum(i).data.addPlaceOnFloorByNum(j, data);
                    }
                    numOfOffice++;
                }
            }
        }
    }
    public void delPlaceByNum(int num) {
        if ((num >= getAmountOfPlaces()) || (num < 0)) {
            throw new SpaceIndexOutOfBoundsException();
        }
        int numOfOffice = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < getNodeByNum(i).data.getAmountOfPlacesOnFloor(); j++) {
                if (numOfOffice == num) {
                    getNodeByNum(i).data.delPlaceOnFloorByNum(j);
                }
                numOfOffice++;
            }
        }
    }
    public Space getBestSpace() {
        Space bestSpaceOffice =  getNodeByNum(0).data.getBestSpace();
        for (int i = 1; i < size; i++) {
            if (bestSpaceOffice.getSpace() < getNodeByNum(i).data.getBestSpace().getSpace()) {
                bestSpaceOffice = getNodeByNum(i).data.getBestSpace();
            }
        }
        return bestSpaceOffice;
    }

    public Space[] getSortedArray() {
        int numOfOffices = 0;
        for (int i = 0; i < size; i++) {
            numOfOffices += getNodeByNum(i).data.getAmountOfPlacesOnFloor();
        }
        Space[] officesSortedBySpace = new Space[numOfOffices];
        for (int i = 0; i < numOfOffices; i++) {
            officesSortedBySpace[i] = getPlaceByNum(i);
        }
        for (int i = 0; i < numOfOffices - 1; i++) {
            for (int j = 0; j < numOfOffices - i - 1; j++)
                if (officesSortedBySpace[j].getSpace() > officesSortedBySpace[j + 1].getSpace()) {
                    Space temp = officesSortedBySpace[j];
                    officesSortedBySpace[j] = officesSortedBySpace[j + 1];
                    officesSortedBySpace[j + 1] = temp;
                }
        }
        return officesSortedBySpace;
    }

    @Override
    public String toString(){
        String tempString = new String();
        tempString = "OfficeBuilding" + size + ",";
        for (int i = 0; i < size; i++) {
            tempString += getAmountOfPlaces() + "," + "OfficeFloor" + "(";
            for (int j = 0; j < getFloorByNum(i).getAmountOfPlacesOnFloor(); j++) {
                tempString += " " + getPlaceByNum(j).getAmountOfRooms() + " " + getPlaceByNum(j).getSpace();
            }
            tempString += ")";
        }
        return tempString;
    }

    @Override
    public boolean equals(Object object){
        if(!(object instanceof OfficeBuilding) ) return false;
        if(((Dwelling) object).getAmountOfFloors() == size){
            for (int i = 0; i < size; i++) {
                if(!object.equals(getFloorByNum(i))) return false;
                for (int j = 0; j < getFloorByNum(i).getAmountOfPlacesOnFloor(); j++) {
                    if(!object.equals(getFloorByNum(i).getPlaceOnFloorByNum(j))) return false;
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
        int[] numOfFloors = new int[getAmountOfFloors()];
        for (int i = 0; i < getAmountOfFloors(); i++) {
            numOfFloors[i] = getFloorByNum(i).getAmountOfPlacesOnFloor();
        }
        Building officeBuilding = new OfficeBuilding(numOfFloors);
        for (int i = 0; i < numOfFloors.length; i++) {
            officeBuilding.setFloorByNum(i, (Floor) getFloorByNum(i).clone());
        }
        return officeBuilding;
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

