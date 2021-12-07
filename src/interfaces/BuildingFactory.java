package interfaces;
import interfaces.*;
public interface BuildingFactory {
    public Space createSpace(int area);
    public Space createSpace(int roomsCount, int area);
    public Floor createFloor(int spacesCount);
    public Floor createFloor(Space[] spaces);
    public Building createBuilding(int[] spacesCounts);
    public Building createBuilding(Floor[] floors);

}
