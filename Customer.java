import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;


public class Customer implements Runnable {
    ThriftStore tf;
    AtomicInteger tick;

    public Customer(ThriftStore tf, AtomicInteger tick){
        this.tf = tf;
        this.tick = tick;
    }

    public void run(){
                //While Program still running
        while(true){
            Random random = new Random();
        
            // Generate a random integer between 1 and 20 (inclusive)
            int randomNumber = random.nextInt(10) + 1;

            try {
                // Sleep for the specified delay
                Thread.sleep(randomNumber*1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            tf.consume(tick);
            //notify();



        }


    }



    //Buy Function..... Run ever ten seconds







}
