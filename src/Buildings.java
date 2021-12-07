import buildings.SynchronizedFloor;
import buildings.dwelling.Dwelling;
import buildings.dwelling.DwellingFloor;
import buildings.dwelling.Flat;
import buildings.factory.DwellingFactory;
import interfaces.*;

import java.io.*;
import java.util.Comparator;
import java.util.Formatter;
import java.util.Scanner;


public class Buildings {

    //Метод записи данных о здании в байтовый поток
    public static void outputBuilding(Building building, OutputStream out) throws IOException {
        DataOutputStream dos = new DataOutputStream(out);
        dos.writeInt(building.getAmountOfFloors());
        for (int i = 0; i < building.getAmountOfFloors(); i++) {
            dos.writeInt(building.getAmountOfPlaces());
            for (int j = 0; j < building.getFloorByNum(i).getAmountOfPlacesOnFloor(); j++) {
                dos.writeInt(building.getPlaceByNum(j).getAmountOfRooms());
                dos.writeInt(building.getPlaceByNum(j).getSpace());
            }
        }

    }

    //чтения данных о здании из байтового потока
    public static Building inputBuilding(InputStream in) throws IOException {
        DataInputStream dis = new DataInputStream(in);
        Floor[] floorArray = new Floor[dis.readInt()];
        for (int i = 0; i < floorArray.length; i++) {
            Space[] place = new Space[dis.read()];
            for (int j = 0; j < place.length; j++) {
                place[j] = createSpace(0, 0);
                place[j].setAmountOfRooms(dis.readInt());
                place[j].setSpace(dis.readInt());

            }
            floorArray[i] = createFloor(place);
        }

        return createBuilding(floorArray);
    }

    //записи здания в символьный поток
    public static void writeBuilding(Building building, Writer out) {
        PrintWriter pw = new PrintWriter(out);
        pw.print(building.getAmountOfFloors() + ' ');
        for (int i = 0; i < building.getAmountOfFloors(); i++) {
            pw.print(building.getFloorByNum(i));
            for (int j = 0; j < building.getFloorByNum(i).getAmountOfPlacesOnFloor(); j++) {
                pw.print(building.getFloorByNum(i).getPlaceOnFloorByNum(j).getAmountOfRooms());
                pw.print(building.getFloorByNum(i).getPlaceOnFloorByNum(j).getSpace());
            }
        }

    }

    //чтения здания из символьного потока
    public static Building readBuilding(Reader in) throws IOException {
        StreamTokenizer st = new StreamTokenizer(in);
        st.nextToken();
        Floor[] floorArray = new Floor[(int) st.nval];
        for (int i = 0; i < floorArray.length; i++) {
            st.nextToken();
            Space[] place = new Space[(int) st.nval];
            for (int j = 0; j < place.length; j++) {
                place[j] = createSpace(0, 0);
                st.nextToken();
                place[j].setSpace((int) st.nval);
                st.nextToken();
                place[j].setSpace((int) st.nval);
            }
            floorArray[i] = createFloor(place);
        }
        return createBuilding(floorArray);
    }

    //сериализации здания в байтовый поток
    public static void serializeBuilding(Building building, OutputStream out) throws IOException {
        ObjectOutputStream writeToStream = new ObjectOutputStream(out);
        writeToStream.writeObject(building);

    }

    //десериализации здания из байтового потока
    public static Building deserializeBuilding(InputStream in) throws IOException, ClassNotFoundException {
        ObjectInputStream readFromStream = new ObjectInputStream(in);
        Building building = (Dwelling) readFromStream.readObject();
        return building;
    }

    //Добавьте метод текстовой записи
    public static void writeBuildingFormat(Building building, Writer out) {
        PrintWriter pw = new PrintWriter(out);
        Formatter formatter = new Formatter();
        formatter.format("%d", building.getAmountOfFloors());
        pw.print(formatter);
        for (int i = 0; i < building.getAmountOfFloors(); i++) {
            formatter = new Formatter();
            formatter.format("%d", building.getFloorByNum(i).getAmountOfPlacesOnFloor());
            pw.print(formatter);
            for (int j = 0; j < building.getFloorByNum(i).getAmountOfPlacesOnFloor(); j++) {
                formatter = new Formatter();
                formatter.format("%d", building.getFloorByNum(i).getPlaceOnFloorByNum(j).getAmountOfRooms());
                pw.print(formatter);
                formatter = new Formatter();
                formatter.format("%d", building.getFloorByNum(i).getPlaceOnFloorByNum(j).getSpace());
                pw.print(formatter);
            }
        }

    }

    //перегруженный метод readBuilding для сканнера
    public static Building readBuilding(Scanner in) throws IOException {
        String str = String.format("%d", in.nextInt());
        Floor[] floorArray = new Floor[Integer.parseInt(str)];
        for (int i = 0; i < floorArray.length; i++) {
            str = String.format("%d", in.nextInt());
            Space[] place = new Space[Integer.parseInt(str)];
            for (int j = 0; j < place.length; j++) {
                place[j] = createSpace(0, 0);
                str = String.format("%d", in.nextInt());
                place[j].setAmountOfRooms(Integer.parseInt(str));
                str = String.format("%d", in.nextInt());
                place[j].setSpace(Integer.parseInt(str));
            }
            floorArray[i] = createFloor(place);
        }
        return createBuilding(floorArray);
    }

    class SpaceComparator implements Comparator<Space> {
        public int compare(Space data1, Space data2){
            return data1.getAmountOfRooms() - data2.getAmountOfRooms();
        }
    }

    class FloorComparator implements Comparator<Floor> {
        public int compare(Floor data1, Floor data2){
            return data1.getTotalSpaceOnFloor() - data2.getTotalSpaceOnFloor();
        }
    }

    public static void sortSpace(Space[] spaceArray) {
        for (int i = 0; i < spaceArray.length; i++) {
            for (int j = 0; j < spaceArray.length - 1; j++) {
                if (spaceArray[j].compareTo(spaceArray[j + 1]) > 0) {
                    Space swapBuf = spaceArray[j];
                    spaceArray[j] = spaceArray[j + 1];
                    spaceArray[j + 1] = swapBuf;
                }
            }
        }
    }

   /* public static void sortFloor(Floor[] floorArray) {
        for (int i = 0; i < floorArray.length; i++) {
            for (int j = 0; j < floorArray.length - 1; j++) {
                if (floorArray[j].compareTo(floorArray[j + 1]) > 0) {
                    Floor swapBuf = floorArray[i];
                    floorArray[i] = floorArray[i + 1];
                    floorArray[i + 1] = swapBuf;
                }
            }
        }
    }*/

    public static <T extends Comparable<T>> void sort(T[] objects) {
        for (int i = 0; i < objects.length; i++) {
            int minIndex = i;
            for (int j = i + 1; j < objects.length; j++) {
                if (objects[j].compareTo(objects[minIndex]) < 0)
                    minIndex = j;
            }
            T swapBuf = objects[i];
            objects[i] = objects[minIndex];
            objects[minIndex] = swapBuf;
        }
    }

    public static void sortSpace(Space[] spaceArray,  SpaceComparator comp) {
        for (int i = 0; i < spaceArray.length; i++) {
            for (int j = 0; j < spaceArray.length - 1; j++) {
                if (comp.compare(spaceArray[j], (spaceArray[j + 1])) > 0) {
                    Space swapBuf = spaceArray[j];
                    spaceArray[j] = spaceArray[j + 1];
                    spaceArray[j + 1] = swapBuf;
                }
            }
        }
    }

    public static void sortFloor(Floor[] floorArray, FloorComparator comp) {
        for (int i = 0; i < floorArray.length; i++) {
            for (int j = 0; j < floorArray.length - 1; j++) {
                if (comp.compare(floorArray[j], floorArray[j + 1]) > 0) {
                    Floor swapBuf = floorArray[i];
                    floorArray[i] = floorArray[i + 1];
                    floorArray[i + 1] = swapBuf;
                }
            }
        }
    }

    public static <T> void sort(T[] objects, Comparator<T> comparator) {
        for (int i = 0; i < objects.length; i++) {
            int minIndex = i;
            for (int j = i + 1; j < objects.length; j++) {
                if (comparator.compare(objects[j], objects[minIndex]) < 0)
                    minIndex = j;
            }
            T swapBuf = objects[i];
            objects[i] = objects[minIndex];
            objects[minIndex] = swapBuf;
        }
    }

    public static BuildingFactory buildingFactory = new DwellingFactory();
    public void setBuildingFactory(BuildingFactory data){
        buildingFactory = data;
    }

    public static Space createSpace(int area){
        return buildingFactory.createSpace(area);
    }
    public static Space createSpace(int roomsCount, int area){
        return buildingFactory.createSpace(roomsCount, area);
    }
    public static Floor createFloor(int spacesCount){
        return buildingFactory.createFloor(spacesCount);
    }
    public static Floor createFloor(Space[] spaces){
        return buildingFactory.createFloor(spaces);
    }
    public static Building createBuilding(int[] spacesCounts){
        return buildingFactory.createBuilding(spacesCounts);
    }
    public static Building createBuilding(Floor[] floors){
        return buildingFactory.createBuilding(floors);
    }

    public static Floor synchronizedFloor (Floor floor) throws CloneNotSupportedException {
        return (SynchronizedFloor) floor.clone();
    }
}
