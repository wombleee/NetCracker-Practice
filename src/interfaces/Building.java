package interfaces;
public interface Building extends Iterable<Floor>{
    public int getAmountOfFloors();

    public int getAmountOfPlaces();

    public int getTotalSpaceOfPlaces();

    public int getAmountOfPlacesRooms();

    public Floor[] getFloorsArray();

    public Floor getFloorByNum(int index);

    public void setFloorByNum(int num, Floor dataFloor);

    public Space getPlaceByNum(int num);

    public void setPlaceByNum(int num, Space dataSpace);

    public void addPlaceByNum(int num, Space dataSpace);

    public void delPlaceByNum(int num);

    public Space getBestSpace();

    public Space[] getSortedArray();

    public Object clone() throws CloneNotSupportedException;
}