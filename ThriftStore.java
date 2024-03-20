import java.util.concurrent.atomic.AtomicInteger;

public class ThriftStore{

    //The funcs and fields in this class is just for testing
    public int data = 0;
    //This could hold delivery, assistant could look at the delivery by reference of TF.
    public String[] delivery;

    public int electronics = 0;




    public synchronized void produce(AtomicInteger tick){
        while(data == 5){
            try{
                wait();
            }catch (InterruptedException e){
                Thread.currentThread().interrupt();
            }
        }
        //Wait one second
        data++;
        notify();
        System.out.printf("%d and time is %d\n",data, tick.get());
    }

    public synchronized void consume(AtomicInteger tick){
        while(data == 0){
            try {
                wait(); // Waiting as the queue is full
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt(); // Reset the interrupted status
            }
        }
        //Wait one second
        data--;
        notify();
        System.out.printf("%d and time is %d\n",data, tick.get());
    }

    // need delivery function...... Actually Delivery could be its own thread




    public void thriftStoreDay(Thread assistant, Thread customer, AtomicInteger tick){
        //int tick = 0;
        //delivery.start();
        //AtomicInteger tick = new AtomicInteger(0);
        


        
        //AtomicInteger tick = new AtomicInteger(0);
        while (tick.get() < 100){
            System.out.println("Tick: " + tick.incrementAndGet());
            try {
                // Sleep for the specified delay
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


        }
    }


}




//Make Main class