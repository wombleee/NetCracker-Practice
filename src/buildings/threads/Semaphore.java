package buildings.threads;
import interfaces.Floor;

public class Semaphore {
    private boolean placeIsRepaired;

    public Semaphore(){
        this.placeIsRepaired = false;
    }

    public boolean placeIsRepaired(){
        return placeIsRepaired;
    }
    public void setPlaceIsRepaired(boolean data){
        placeIsRepaired = data;
    }
}
