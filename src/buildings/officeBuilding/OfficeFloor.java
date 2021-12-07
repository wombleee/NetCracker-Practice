package buildings.officeBuilding;
import buildings.dwelling.DwellingFloor;
import interfaces.*;
import exceptions.*;

import java.io.Serializable;
import java.util.Comparator;
import java.util.Iterator;

public class OfficeFloor implements Floor, Serializable, Cloneable, Iterable<Space> {
    private class Node{
        Space data;
        Node next;
        public Node(Space data) {
            this.data = data;
            this.next = null;
        }
    }
    private Node head = null;
    private Node tail = null;
    private int size = 0;
    private Node getNodeByNum(int num) {
        Node temp = head;
        for (int i = 0; i < num; i++) {
            temp = temp.next;
        }
        return temp;
    }
    private void addNodeByNum(int num, Space data) {
        if (head == null) {
            head = new Node(data);
            size++;
        }
        else if (num == 0) {
            if (head.next == null) {
                tail = head;
                head.data = data;
                head.next = tail;
                tail.next = head;
                size++;
            }
            else {
                Node temp = new Node(data);
                temp.next = head;
                head = temp;
                size++;
            }
        }
        else if(num == size) {
            Node temp = head;
            for (int i = 0; i < num - 1; i++) {
                temp = temp.next;
            }
            tail = new Node(data);
            temp.next = tail;
            tail.next = head;
            size++;
        }
        else {
            Node temp = head;
            for (int i = 0; i < num - 1; i++) {
                temp = temp.next;
            }
            Node superTemp = new Node(data);
            superTemp.next = temp.next;
            temp.next = superTemp;
            size++;
        }
    }
    private void delNodeByNum(int num) {
        if (num == 0) {
            head = head.next;
            size--;
        }
        else if (num == size - 1){
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
            size--;
        }
    }

    public OfficeFloor(int numOfOfficesOnFloor) {
        Space temp = new Office();
        for (int i = 0; i < numOfOfficesOnFloor; i++) {
            addNodeByNum(i, temp);
        }
    }
    public OfficeFloor(Space[] OfficesOnFloor) {
        for (int i = 0; i < OfficesOnFloor.length; i++) {
            addNodeByNum(i, OfficesOnFloor[i]);
        }
    }

    public int getAmountOfPlacesOnFloor() {
        return size;
    }
    public int getTotalSpaceOnFloor() {
        Node temp = head;
        int totalSquare = 0;
        while (temp.next != null) {
            temp = temp.next;
            totalSquare += temp.data.getSpace();
        }
        return totalSquare;
    }
    public int getTotalAmountOfRoomsOnFloor() {
        Node temp = head;
        int totalRooms = 0;
        while (temp.next != null) {
            temp = temp.next;
            totalRooms += temp.data.getAmountOfRooms();
        }
        return totalRooms;
    }
    //
    public Space[] getPlacesArrayOnFloor() {
        Space[] OfficesArray = new Space[size];
        for (int i = 0; i < size; i++) {
            OfficesArray[i] = getPlaceOnFloorByNum(i);
        }
        return OfficesArray;
    }

    public Space getPlaceOnFloorByNum(int num) {
        if (num < size && num >= 0) {
            return getNodeByNum(num).data;
        }
        else {
            throw new SpaceIndexOutOfBoundsException();
        }
    }
    public void setPlaceOnFloorByNum(int num, Space data) {
        if (num < size && num >=0) {
            getNodeByNum(num).data = (Office) data;
        }
        else {
            throw new SpaceIndexOutOfBoundsException();
        }
    }

    public void addPlaceOnFloorByNum(int num, Space data) {
        if (num > size && num < 0) {
            throw new SpaceIndexOutOfBoundsException();
        }
        else {
            addNodeByNum(num, (Office)data);
        }
    }
    public void delPlaceOnFloorByNum(int num) {
        if (num > size && num < 0) {
            throw new SpaceIndexOutOfBoundsException();
        }
        else {
            delNodeByNum(num);
        }
    }
    public Space getBestSpace() {
        Space bestSpace = head.data;
        for (int i = 1; i < size; i++) {
            if (getNodeByNum(i).data.getSpace() > bestSpace.getSpace()) {
                bestSpace = getNodeByNum(i).data;
            }
        }
        return bestSpace;
    }

    @Override
    public String toString(){
        String tempString = new String();
        tempString = "OfficeFloor " + size + ",";
        for (int i = 0; i < size; i++) {
            tempString += " " + getNodeByNum(i).data.getAmountOfRooms() + " " + getNodeByNum(i).data.getSpace();
        }
        return tempString;
    }

    @Override
    public boolean equals(Object object){
        if(!(object instanceof OfficeFloor) ) return false;
        if(((OfficeFloor) object).getAmountOfPlacesOnFloor() == size){
            for (int i = 0; i < size; i++) {
                if(!object.equals(getPlaceOnFloorByNum(i))) return false;
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
    public Object clone() throws CloneNotSupportedException {
        OfficeFloor officeFloor = new OfficeFloor(this.getAmountOfPlacesOnFloor());
        for (int i = 0; i < officeFloor.getAmountOfPlacesOnFloor(); i++) {
           officeFloor.setPlaceOnFloorByNum(i, (Space) this.getPlaceOnFloorByNum(i).clone());
        }
        return officeFloor;
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




