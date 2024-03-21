import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;


public class Customer implements Runnable {
    ThriftStore tf;
    AtomicInteger tick;

    public Customer(ThriftStore tf, AtomicInteger tick){
        this.tf = tf;
        this.tick = tick;
    }


    public synchronized void buy(){
        int tickToPerformAction = tick.get() + 10;
        while(tick.get() < tickToPerformAction){

        }
        Random random = new Random();
        int randomNumber = random.nextInt(6);
        try{
            while(tf.storeInventory.get(tf.sections[randomNumber]) == 0){
                System.out.println("Nothign in this section gonna wait ");
                wait();
            }
        }catch(InterruptedException e){
            e.printStackTrace();
        }
        //System.out.println("Found it");
        synchronized(tf.storeInventory){
            int currentValue = tf.storeInventory.get(tf.sections[randomNumber]);

            

            // Increment the value by one
            int newValue = currentValue - 1;
            //System.out.println("IM HERE");

            // Put the new value back into the dictionary
            tf.storeInventory.put(tf.sections[randomNumber], newValue);
            System.out.printf("<%d> <%s> Customer Bought %s \n",tick.get(), Thread.currentThread().getId(),tf.sections[randomNumber]);
        }
    }
    public void run(){
                //While Program still running
        while(true){
            buy();
        }


    }



    //Buy Function..... Run ever ten seconds







}
