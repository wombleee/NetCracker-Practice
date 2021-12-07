package buildings.dwelling.hotel;
import buildings.dwelling.DwellingFloor;
import interfaces.Floor;
import interfaces.Space;

public class HotelFloor extends DwellingFloor {

    private int stars;
    private static final int defStars = 1;

    public HotelFloor(int amountOfPlaces) {
        super(amountOfPlaces);
        this.stars = defStars;
    }
    public HotelFloor(Space[] placeArray) {
        super(placeArray);
        this.stars = defStars;
    }
    public void setStars(int stars) {
        this.stars = stars;
    }
    public int getStars() {
        return stars;
    }

    @Override
    public String toString(){
        String tempString = new String();
        tempString = "HotelFloor "  + getStars() + ' ' + getAmountOfPlacesOnFloor() + ",";
        for (Space flat : getPlacesArrayOnFloor()) {
            tempString += " " + flat.getAmountOfRooms() + " " + flat.getSpace();
        }
        return tempString;
    }

    @Override
    public boolean equals(Object object){
        if(!(object instanceof DwellingFloor) ) return false;
        if(((DwellingFloor) object).getAmountOfPlacesOnFloor() == getAmountOfPlacesOnFloor()){
            for (Space flat : getPlacesArrayOnFloor()) {
                if(!object.equals(flat)) return false;
            }
            return true;
        }
        else return false;
    }

    @Override
    public int hashCode() {
        int prime = 29;
        Integer amountOfRoom = Integer.parseInt(Integer.toBinaryString(getTotalAmountOfRoomsOnFloor()));
        Integer space = Integer.parseInt(Integer.toBinaryString(getTotalSpaceOnFloor()));
        return prime * amountOfRoom ^ space;
    }
}

