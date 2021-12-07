package buildings;

import buildings.officeBuilding.OfficeFloor;
import interfaces.*;

public class SynchronizedFloor implements Floor {
    protected Floor tempFloor;

    public SynchronizedFloor(Floor floor) {
        this.tempFloor = floor;
    }

    @Override
    public synchronized int getAmountOfPlacesOnFloor() {
        return tempFloor.getAmountOfPlacesOnFloor();
    }

    @Override
    public synchronized int getTotalSpaceOnFloor() {
        return tempFloor.getTotalSpaceOnFloor();
    }

    @Override
    public synchronized int getTotalAmountOfRoomsOnFloor() {
        return tempFloor.getTotalAmountOfRoomsOnFloor();
    }

    @Override
    public synchronized Space[] getPlacesArrayOnFloor() {
        return tempFloor.getPlacesArrayOnFloor();
    }

    @Override
    public synchronized Space getPlaceOnFloorByNum(int num) {
        return tempFloor.getPlaceOnFloorByNum(num);
    }

    @Override
    public synchronized void setPlaceOnFloorByNum(int num, Space space) {
        tempFloor.setPlaceOnFloorByNum(num, space);
    }

    @Override
    public synchronized void addPlaceOnFloorByNum(int num, Space space) {
        tempFloor.addPlaceOnFloorByNum(num, space);
    }

    @Override
    public synchronized void delPlaceOnFloorByNum(int num) {
        tempFloor.delPlaceOnFloorByNum(num);
    }

    @Override
    public synchronized Space getBestSpace() {
        return tempFloor.getBestSpace();
    }

    @Override
    public synchronized String toString() {
        return tempFloor.toString();
    }

    @Override
    public synchronized int hashCode() {
        return tempFloor.hashCode();
    }

    @Override
    public synchronized boolean equals(Object object) {
        return tempFloor.equals(object);
    }

    @Override
    public synchronized Object clone() throws CloneNotSupportedException {
        Floor floor;
        floor = new OfficeFloor(getAmountOfPlacesOnFloor());
        for (int i = 0; i < getAmountOfPlacesOnFloor(); i++) {
            floor.setPlaceOnFloorByNum(i, (Space) getPlaceOnFloorByNum(i).clone());
        }
        return floor;
    }
}