package buildings.factory;

import buildings.dwelling.Flat;
import buildings.dwelling.hotel.Hotel;
import buildings.dwelling.hotel.HotelFloor;
import interfaces.Building;
import interfaces.BuildingFactory;
import interfaces.Floor;
import interfaces.Space;


public class HotelFactory implements BuildingFactory {

    public Space createSpace(int area){
        Space newSpace = new Flat(area);
        return newSpace;
    }
    public Space createSpace(int roomsCount, int area){
        Space newSpace = new Flat(area, roomsCount);
        return newSpace;
    }
    public Floor createFloor(int spacesCount){
        Floor newFloor = new HotelFloor(spacesCount);
        return newFloor;
    }
    public Floor createFloor(Space[] spaces){
        Floor newFloor = new HotelFloor(spaces);
        return newFloor;
    }
    public Building createBuilding(int[] spacesCounts){
        Building newBuilding = new Hotel(spacesCounts);
        return newBuilding;
    }
    public Building createBuilding(Floor[] floors){
        Building newBuilding = new Hotel(floors);
        return newBuilding;
    }
}
