
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class Deliverer implements Runnable{

    ThriftStore tf;
    AtomicInteger tick;
    String[] sections = {"electronics", "clothes", "furniture", "toys", "sporting goods", "books"};


    public Deliverer(ThriftStore tf, AtomicInteger tick){
        this.tf = tf;
        this.tick = tick;
    }

    //Creates an array of ten strings containing a random set of ThriftStore sections.
    public void deliver(){
        
        String[] selectedSections = new String[10];
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            selectedSections[i] = sections[random.nextInt(6) + 0];
            
        }
        
        String result = String.join(",", selectedSections);
        System.out.printf("<%d> <%s> Deposit of Items [%s]\n",tick.get(), Thread.currentThread().getId(), result);

        //Updates the ThriftStore's current delivery and sets isDelivered bool to true
        tf.delivery = selectedSections;
        tf.isDelivered = true;

        

        
    }

    @Override    
    public void run() {
        while(true){
            

            //To have deliveries come every 100 ticks, we used a gaussian Distribution with a mean of 100 to obtain the number of ticks
            //a Deliverer should wait untill they can deliver.
            Random random = new Random();
            double randomValue = Math.abs(random.nextGaussian() * 50 + 100);
            // Round the value to the nearest integer
            int randomInt = (int) Math.round(randomValue);

            
            int tickToPerformAction = tick.get() + randomInt;
            while (tick.get() < tickToPerformAction) {
                    
                      
            }
            
            //If there isn't already a delivery waiting for an assistant.
            if(!tf.isDelivered){
                deliver();
            }
            
                
        }
        
    }
    
    
}
