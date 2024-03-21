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
        while(true){
            int tickToPerformAction = tick.get() + 10;
            System.out.printf(" Assisnt waitinf for %d",tickToPerformAction);
           
                
                while (tick.get() < tickToPerformAction) {
                    
                      
                }
                    System.out.println("heeeyy");
                
            
        }

        

    }

    





}
