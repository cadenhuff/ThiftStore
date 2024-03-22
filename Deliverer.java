import java.util.Arrays;
import java.util.Collections;
import java.util.List;
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


    public void deliver(){
        
        String[] selectedSections = new String[10];

        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            selectedSections[i] = sections[random.nextInt(5) + 0];
            
        }
        //Do i need syncrnoized here
        String result = String.join(",", selectedSections);
        System.out.printf("<%d> <%s> Deposit of Items [%s]\n",tick.get(), Thread.currentThread().getId(), result);
        tf.delivery = selectedSections;
        tf.isDelivered = true;

        

        
    }

    public void run() {
        while(true){
            //I still dont know how right that is
            Random random = new Random();

            double randomValue = Math.abs(random.nextGaussian() * 50 + 100);

            // Round the value to the nearest integer
            int randomInt = (int) Math.round(randomValue);
            System.out.println(randomInt);
            int tickToPerformAction = tick.get() + randomInt;
            //System.out.printf(" Deliverer waiting for %d\n",tickToPerformAction);
           
            //This works, however it is definitly not optimal as it wastes CPU cycles? 
            while (tick.get() < tickToPerformAction) {
                    
                      
            }
            //while((random.nextInt(100) + 1) != 100){

            //}
            
            if(!tf.isDelivered){
                deliver();
            }
            
                
        }
        
    }
    
    
}
