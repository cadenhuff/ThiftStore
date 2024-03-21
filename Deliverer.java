import java.util.concurrent.atomic.AtomicInteger;

public class Deliverer implements Runnable{

    ThriftStore tf;
    AtomicInteger tick;

    public Deliverer(ThriftStore tf, AtomicInteger tick){
        this.tf = tf;
        this.tick = tick;
    }


    public void deliver(){
        
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
