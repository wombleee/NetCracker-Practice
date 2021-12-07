package buildings.factory;

import buildings.dwelling.Dwelling;
import buildings.dwelling.DwellingFloor;
import buildings.dwelling.Flat;
import interfaces.Building;
import interfaces.BuildingFactory;
import interfaces.Floor;
import interfaces.Space;

public class DwellingFactory implements BuildingFactory {

    public Space createSpace(int area){
        Space newSpace = new Flat(area);
        return newSpace;
    }
    public Space createSpace(int roomsCount, int area){
        Space newSpace = new Flat(area, roomsCount);
        return newSpace;
    }

    public Floor createFloor(int spacesCount){
        Floor newFloor = new DwellingFloor(spacesCount);
        return newFloor;
    }
    public Floor createFloor(Space[] spaces){
        Floor newFloor = new DwellingFloor(spaces);
        return newFloor;
    }
    public Building createBuilding(int[] spacesCounts){
        Building newBuilding = new Dwelling(spacesCounts);
        return newBuilding;
    }
    public Building createBuilding(Floor[] floors){
        Building newBuilding = new Dwelling(floors);
        return newBuilding;
    }
}
