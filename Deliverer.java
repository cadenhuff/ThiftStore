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
        tf.delivery = selectedSections;

        
    }

    public void run() {
        while(true){
            int tickToPerformAction = tick.get() + 10;
            System.out.printf(" Deliverer waiting for %d\n",tickToPerformAction);
           
            //This works, however it is definitly not optimal as it wastes CPU cycles? 
            while (tick.get() < tickToPerformAction) {
                    
                      
            }
            deliver();
                
        }
        
    }
    
    
}