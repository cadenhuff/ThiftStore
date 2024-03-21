import java.util.concurrent.atomic.AtomicInteger;
import java.util.Random;

public class Assistant implements Runnable {
    ThriftStore tf;
    AtomicInteger tick;

    public Assistant(ThriftStore tf, AtomicInteger tick){
        this.tf = tf;
        this.tick = tick;
    }




    public void run(){
        

        
        while (tick.get() < tf.NUM_TICKS) {
            
            try {
                // Wait for the semaphore to be released indicating the start of a new tick
                ThriftStore.tickSemaphore.acquire();
                //Thread.sleep(1000);
                System.out.println("Hi " );
                

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
           
            System.out.println("Assistant's task for tick " + tick.get());
                
                // Allow the thrift store to proceed
            
            try{
                Thread.sleep(1000);
            }catch(InterruptedException e){
                e.printStackTrace();
            }
            ThriftStore.tickSemaphore.release();
        }


        


        
    }

    





}
