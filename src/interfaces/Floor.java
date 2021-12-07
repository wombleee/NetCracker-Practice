package interfaces;
import java.util.Comparator;
import java.util.Iterator;

public interface Floor extends Cloneable {
    public int getAmountOfPlacesOnFloor();

    public int getTotalSpaceOnFloor();

    public int getTotalAmountOfRoomsOnFloor();

    public Space[] getPlacesArrayOnFloor();

    public Space getPlaceOnFloorByNum(int num);

    public void setPlaceOnFloorByNum(int num, Space space);

    public void addPlaceOnFloorByNum(int num, Space space);

    public void delPlaceOnFloorByNum(int num);

    public Space getBestSpace();

    public Object clone() throws CloneNotSupportedException;


}