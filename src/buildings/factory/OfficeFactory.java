package buildings.factory;

import buildings.dwelling.Dwelling;
import buildings.dwelling.DwellingFloor;
import buildings.officeBuilding.Office;
import buildings.officeBuilding.OfficeBuilding;
import buildings.officeBuilding.OfficeFloor;
import interfaces.Building;
import interfaces.BuildingFactory;
import interfaces.Floor;
import interfaces.Space;

public class OfficeFactory implements BuildingFactory {

    public Space createSpace(int area){
        Space newSpace = new Office(area);
        return newSpace;
    }
    public Space createSpace(int roomsCount, int area){
        Space newSpace = new Office(area, roomsCount);
        return newSpace;
    }
    public Floor createFloor(int spacesCount){
        Floor newFloor = new OfficeFloor(spacesCount);
        return newFloor;
    }
    public Floor createFloor(Space[] spaces){
        Floor newFloor = new OfficeFloor(spaces);
        return newFloor;
    }

    public Building createBuilding(int[] spacesCounts){
        Building newBuilding = new OfficeBuilding(spacesCounts);
        return newBuilding;
    }
    public Building createBuilding(Floor[] floors){
        Building newBuilding = new OfficeBuilding(floors);
        return newBuilding;
    }
}

