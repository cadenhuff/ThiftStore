import java.util.concurrent.atomic.AtomicInteger;
import java.util.Random;

public class Assistant implements Runnable {
    ThriftStore tf;
    AtomicInteger tick;
    public String[] inventory;

    public Assistant(ThriftStore tf, AtomicInteger tick){
        this.tf = tf;
        this.tick = tick;
        
    }

    public void takeItemsOut(){
        synchronized(tf.delivery){
            //iterate through delivery and put in assisants inventory, assistant can only hold 10
        }
    }

    



    //Since tasks are linear
    public void run(){
        while(true){
            int tickToPerformAction = 1000;
            System.out.printf(" Assisnt waitinf for %d",tickToPerformAction);
           
            //This works, however it is definitly not optimal as it wastes CPU cycles? 
            while (tick.get() < tickToPerformAction) {
                //check for deliver
                //if there is a deliver{
                //        
                //}
                    
                      
            }
            System.out.println("heeeyy");
                
            
        }

        

    }

    





}
