import buildings.dwelling.DwellingFloor;
import buildings.dwelling.Flat;
import buildings.officeBuilding.Office;
import buildings.officeBuilding.OfficeBuilding;
import buildings.officeBuilding.OfficeFloor;
import buildings.threads.*;
import interfaces.Space;

import javax.sound.midi.Soundbank;
import java.util.Comparator;
import java.util.Iterator;

public class Main {
    public static void main(String[] args) throws CloneNotSupportedException {
        /*Flat flat1 = new Flat(81, 2);
        Flat flat2 = new Flat(60);
        Flat flat3 = new Flat(110, 3);

        Flat[] flatArray = new Flat[3];
        flatArray[0] = flat1;
        flatArray[1] = flat2;
        flatArray[2] = flat3;

        Flat flat4 = new Flat (200, 5);
        System.out.println('\n' + "--------------------flatArray: ");
        for (int i = 0; i < flatArray.length; i++) {
            System.out.println(flatArray[i].getAmountOfRooms() + " " +  flatArray[i].getSpace());
        }

        DwellingFloor floor1 = new DwellingFloor(flatArray);
        System.out.println('\n' + "-----------------------floor1: ");
        Iterator<Space> iterator = floor1.iterator();
        for (Space space: floor1) {
            System.out.println(space);
        }*/
        Office[] officeArray = new Office[4];
        officeArray[0] = new Office();
        officeArray[1] = new Office(120);
        officeArray[2] = new Office(100, 2);
        officeArray[3] = new Office();
        OfficeFloor officeFloor = new OfficeFloor(officeArray);
        for (int i = 0; i < officeArray.length; i++) {
            System.out.println("Office#:" + i + ' ' + officeFloor.getPlaceOnFloorByNum(i).getAmountOfRooms() + ' ' + officeFloor.getPlaceOnFloorByNum(i).getSpace() + ' ' + officeFloor.getPlaceOnFloorByNum(i).hashCode());
        }

        System.out.println(officeFloor.getPlaceOnFloorByNum(0).equals(officeFloor.getPlaceOnFloorByNum(3)));

        OfficeFloor officeFloor1 = new OfficeFloor(officeArray.clone());
        officeFloor1.setPlaceOnFloorByNum(2, new Office());
        for (int i = 0; i < officeArray.length; i++) {
            System.out.println("Office#:" + i + ' ' + officeFloor1.getPlaceOnFloorByNum(i).getAmountOfRooms() + ' ' + officeFloor1.getPlaceOnFloorByNum(i).getSpace() + ' ' + officeFloor1.getPlaceOnFloorByNum(i).hashCode());
        }

        OfficeFloor[] floorArray = new OfficeFloor[2];
        floorArray[0] = officeFloor;
        floorArray[1] = officeFloor1;
        OfficeBuilding officeBuilding = new OfficeBuilding(floorArray);
        OfficeFloor officeFloor2 = new OfficeFloor(3);
        for (int i = 0; i < 3.; i++) {
            officeFloor2.addPlaceOnFloorByNum(i, new Office());
        }
        System.out.println("\n");
        for (int i = 0; i < officeBuilding.getAmountOfFloors(); i++) {
            System.out.println("Floor#: " + i);
            for (int j = 0; j < officeBuilding.getFloorByNum(i).getAmountOfPlacesOnFloor(); j++) {
                System.out.println("Office#:" + j + ' ' + officeBuilding.getFloorByNum(i).getPlaceOnFloorByNum(j).getAmountOfRooms() + ' ' + officeBuilding.getFloorByNum(i).getPlaceOnFloorByNum(j).getSpace() + ' ' + officeBuilding.getFloorByNum(i).getPlaceOnFloorByNum(j).hashCode());
            }
            System.out.println("\n");
        }

        officeBuilding.getFloorByNum(0).delPlaceOnFloorByNum(3);
        officeBuilding.getFloorByNum(0).setPlaceOnFloorByNum(0, new Office(360, 6));

        for (int i = 0; i < officeBuilding.getAmountOfFloors(); i++) {
            System.out.println("Floor#: " + i);
            for (int j = 0; j < officeBuilding.getFloorByNum(i).getAmountOfPlacesOnFloor(); j++) {
                System.out.println("Office#:" + j + ' ' + officeBuilding.getFloorByNum(i).getPlaceOnFloorByNum(j).getAmountOfRooms() + ' ' + officeBuilding.getFloorByNum(i).getPlaceOnFloorByNum(j).getSpace() + ' ' + officeBuilding.getFloorByNum(i).getPlaceOnFloorByNum(j).hashCode());
            }
            System.out.println("\n");
        }

        OfficeFloor[] floorArray1 = new OfficeFloor[1];
        System.out.println("cloned office building:\n");
        OfficeBuilding officeBuilding1 = new OfficeBuilding(floorArray1);
        officeBuilding1 = (OfficeBuilding) officeBuilding.clone();
        for (int i = 0; i < officeBuilding1.getAmountOfFloors(); i++) {
            System.out.println("Floor#: " + i);
            for (int j = 0; j < officeBuilding1.getFloorByNum(i).getAmountOfPlacesOnFloor(); j++) {
                System.out.println("Office#:" + j + ' ' + officeBuilding1.getFloorByNum(i).getPlaceOnFloorByNum(j).getAmountOfRooms() + ' ' + officeBuilding1.getFloorByNum(i).getPlaceOnFloorByNum(j).getSpace());
            }
            System.out.println("\n");
        }

       /* Repairer repairer = new Repairer(officeFloor);
        Cleaner cleaner = new Cleaner(officeFloor);
        repairer.setPriority(Thread.NORM_PRIORITY);
        cleaner.setPriority(Thread.NORM_PRIORITY);
        repairer.start();
        cleaner.start();*/

        Semaphore semaphore = new Semaphore();
        SequentalRepairer sr = new SequentalRepairer(semaphore, officeFloor);
        SequentalCleaner sc = new SequentalCleaner(semaphore, officeFloor);
        Thread thread1 = new Thread(sr);
        Thread thread2 = new Thread(sc);
        thread1.start();
        thread2.start();
    }
}


