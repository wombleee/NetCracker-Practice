import interfaces.*;
import exceptions.*;

public class PlacementExchanger {

    /*Метод проверки возможности обмена помещениями.
    Передаются две ссылки на объекты типа Space.
    Метод возвращает true, если общая площадь и количество комнат в помещениях равны,
    и false в других случаях.*/
    public static boolean checkForPlaceExchange(Space data1, Space data2){
        if(data1.getSpace() == data2.getSpace() && data1.getAmountOfRooms() == data2.getAmountOfRooms()){
            return true;
        }
        else return false;
    }

    /*Метод проверки возможности обмена этажами.
    Методу передаются две ссылки на объекты типа Floor.
    Метод возвращает true, если общая площадь этажей и количество помещений равны,
    и false в других случаях. */
    public static boolean checkForFloorExchange(Floor data1, Floor data2){
        if(data1.getAmountOfPlacesOnFloor() == data2.getAmountOfPlacesOnFloor() && data1.getTotalSpaceOnFloor() == data2.getTotalSpaceOnFloor()){
            return true;
        }
        else return false;
    }

    /*Метод обмена помещениями двух этажей public static void exchangeFloorRooms(Floor floor1, int index1, Floor floor2, int index2).
    Метод должен проверять возможность обмена помещениями и допустимость номеров помещений,
    выбрасывать при необходимости соответствующие исключения.*/
    public static void exchangeFloorRooms(Floor floor1, int num1, Floor floor2, int num2) throws InexchangeableSpacesException {
        if((num1 < 0 && num1 > floor1.getAmountOfPlacesOnFloor()) || (num2 < 0 && num2 > floor2.getAmountOfPlacesOnFloor())){
            throw new FloorIndexOutOfBoundsException();
        }
        if (checkForPlaceExchange(floor1.getPlaceOnFloorByNum(num1), floor2.getPlaceOnFloorByNum(num2)) == false){
            throw new InexchangeableSpacesException();
        }
        Space tempPlace = floor1.getPlaceOnFloorByNum(num1);
        floor1.setPlaceOnFloorByNum(num1, floor2.getPlaceOnFloorByNum(num2));
        floor2.setPlaceOnFloorByNum(num2, tempPlace);
    }

    /*Метод обмена этажами двух зданий public static void exchangeBuildingFloors(Building building1, int index1, Building building2, int index2).
    Методу передаются две ссылки типа Building и номера соответствующих этажей.
    Метод должен проверять возможность обмена этажами и допустимость номеров этажей,
    выбрасывать при необходимости соответствующие исключения.*/
    public static void exchangeBuildingFloors(Building building1, int num1, Building building2, int num2) throws InexchangeableFloorsException{
        if((num1 < 0 && num1 > building1.getAmountOfFloors()) || (num2 < 0 && num2 > building2.getAmountOfFloors())){
            throw new FloorIndexOutOfBoundsException();
        }
        if (checkForFloorExchange(building1.getFloorByNum(num1), building2.getFloorByNum(num2)) == false){
            throw new InexchangeableFloorsException();
        }
        Floor tempFloor = building1.getFloorByNum(num1);
        building1.setFloorByNum(num1, building2.getFloorByNum(num2));
        building2.setFloorByNum(num2, tempFloor);
    }
}
