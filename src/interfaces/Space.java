package interfaces;

import java.util.Comparator;

public interface Space extends Comparable<Space>, Cloneable{
    public int getAmountOfRooms();

    public int getSpace();

    public void setAmountOfRooms(int rooms);

    public void setSpace(int space);

    public Object clone() throws CloneNotSupportedException;
}