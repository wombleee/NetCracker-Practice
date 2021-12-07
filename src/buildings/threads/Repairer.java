package buildings.threads;
import interfaces.Floor;

public class Repairer extends Thread{
    private Floor tempFloor;
    public Repairer(Floor data) {
        this.tempFloor = data;
    }
    @Override
    public void run() {
        super.run();
        for (int i = 0; i < tempFloor.getAmountOfPlacesOnFloor(); i++) {
            System.out.println("Repairing room number " + i + " with total area " +tempFloor.getPlaceOnFloorByNum(i).getSpace() + " square meters");
        }
        System.out.println("Repairer finish!");
    }
    @Override
    public void interrupt() {
        super.interrupt();
        System.out.println("Repairer finish!");
    }
}
