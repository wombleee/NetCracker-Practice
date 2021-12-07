package buildings.threads;

import interfaces.Floor;

public class SequentalRepairer implements Runnable{

    private Semaphore semaphore;
    private Floor tempFloor;

    public SequentalRepairer(Semaphore sem, Floor floor) {
        this.semaphore = sem;
        tempFloor = floor;
    }
    @Override
    public void run() {
        synchronized (semaphore){
            for (int i = 0; i < tempFloor.getAmountOfPlacesOnFloor(); i++) {
                if(!semaphore.placeIsRepaired()){
                    try{
                        semaphore.notify();
                        System.out.println("Sequental Repairing room number " + i + " with total area " + tempFloor.getPlaceOnFloorByNum(i).getSpace() + " square meters");
                        semaphore.setPlaceIsRepaired(true);
                        semaphore.wait();
                    } catch (InterruptedException e) {
                        System.out.println("Thread error");
                        e.printStackTrace();
                    }
                }
            }
            System.out.println("Repairer finished");
        }
    }
}

