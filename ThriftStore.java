import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

public class ThriftStore{

    public static final int NUM_THREADS = 1;
    public static final int NUM_TICKS = 1000;
    public static final Semaphore tickSemaphore = new Semaphore(0);
    public static final CountDownLatch latch = new CountDownLatch(NUM_THREADS);




    //The funcs and fields in this class is just for testing
    public int data = 0;
    //This could hold delivery, assistant could look at the delivery by reference of TF.
    public String[] delivery;

    public int electronics = 0;

    public int clothing = 0;



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

    public void delivery(){
        electronics++;
        clothing++;
    }


    public void thriftStoreDay(Thread assistant, Thread customer, AtomicInteger tick){
        
        while(tick.get() < NUM_TICKS){
            try{
                Thread.sleep(20000);
            }catch(InterruptedException e){
                e.printStackTrace();
            }
            System.out.printf("%d", tick.get());
            tick.getAndIncrement();
            //notify();

         
        }
        
    }


}




//Make Main class