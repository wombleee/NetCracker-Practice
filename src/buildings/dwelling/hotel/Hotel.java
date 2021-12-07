package buildings.dwelling.hotel;
import buildings.dwelling.Dwelling;
import buildings.dwelling.Flat;
import interfaces.Floor;
import interfaces.Space;

public class Hotel extends Dwelling {
    
    public Hotel(int[] amountOfFlat) {
        super(amountOfFlat);
    }
    public Hotel(Floor[] dataArray) {
        super(dataArray);
    }

    public int getAmountOfStars(){
        int stars = 0;
        for (Floor floor : getFloorsArray()) {
            if(floor instanceof HotelFloor){
                stars = ((HotelFloor) floor).getStars();
            }
        }
        return stars;
    }

    @Override
    public Space getBestSpace(){
        Space bestPlace = null;
        double tempCoeff = 0.0, coeff = 0.0, bestSpaceCoeff = 0.0;
        for (Floor floor : getFloorsArray()) {
            if (floor instanceof HotelFloor) {
                if (((HotelFloor) floor).getStars() == 1) coeff = 0.25;
                if (((HotelFloor) floor).getStars() == 2) coeff = 0.5;
                if (((HotelFloor) floor).getStars() == 3) coeff = 1;
                if (((HotelFloor) floor).getStars() == 4) coeff = 1.25;
                if (((HotelFloor) floor).getStars() == 5) coeff = 1.5;
                for (Space flat : floor.getPlacesArrayOnFloor()) {
                    tempCoeff = flat.getSpace() * coeff;
                    if (bestSpaceCoeff < tempCoeff) {
                        bestPlace = flat;
                        bestSpaceCoeff = tempCoeff;
                    }
                }
            }
        }
        return bestPlace;
    }


    @Override
    public String toString(){
        String tempString;
        tempString = "Hotel" + ",";
        for (Floor floor : getFloorsArray()) {
            tempString += ((HotelFloor) floor).getStars() + ", " +  getAmountOfPlaces() + ", "+ "DwellingFloor" + "(";
            for (Space flat : floor.getPlacesArrayOnFloor()) {
                tempString += " " + flat.getAmountOfRooms() + " " + flat.getSpace();
            }
            tempString += ")";
        }
        return tempString;
    }

    @Override
    public boolean equals(Object object){
        if(!(object instanceof Dwelling) ) return false;
        if(((Dwelling) object).getAmountOfFloors() == getAmountOfPlaces()){
            for (Floor floor : getFloorsArray()) {
                if(!object.equals(floor))return false;
                for (Space flat : floor.getPlacesArrayOnFloor()) {
                    if(!object.equals(flat)) return false;
                }
            }
            return true;
        }
        else return false;
    }

    @Override
    public int hashCode() {
        int prime = 29;
        Integer amountOfRoom = Integer.parseInt(Integer.toBinaryString(getAmountOfPlacesRooms()));
        Integer space = Integer.parseInt(Integer.toBinaryString(getTotalSpaceOfPlaces()));
        return prime * amountOfRoom ^ space;
    }
}
