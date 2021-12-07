package buildings.threads;
import interfaces.Floor;



public class SequentalCleaner implements Runnable{
    private Semaphore semaphore;
    private Floor tempFloor;

    public SequentalCleaner(Semaphore semaphore, Floor floor) {
        this.semaphore = semaphore;
        tempFloor = floor;
    }
    
    @Override
    public void run() {
        synchronized (semaphore){
            for (int i = 0; i < tempFloor.getAmountOfPlacesOnFloor(); i++) {
                if(semaphore.placeIsRepaired()){
                    try{
                        semaphore.notify();
                        System.out.println("Sequental Cleaning room number " + i + " with total area " + tempFloor.getPlaceOnFloorByNum(i).getSpace() + " square meters");
                        semaphore.setPlaceIsRepaired(false);
                        semaphore.wait();
                    } catch (InterruptedException e) {
                        System.out.println("Thread error");
                        e.printStackTrace();
                    }
                }
            }
            System.out.println("Cleaner finished");
        }
    }
}
