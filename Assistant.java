import java.util.concurrent.atomic.AtomicInteger;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;


public class Assistant implements Runnable {
    ThriftStore tf;
    AtomicInteger tick;
    Dictionary<String, Integer> inventory= new Hashtable<>();
    int daysToRun;

    public Assistant(ThriftStore tf, AtomicInteger tick, int daysToRun){
        this.tf = tf;
        this.tick = tick;
        this.daysToRun = daysToRun;

        //Initialize Inventory to 0 for every section
        for(int i = 0; i<6; i++){
            inventory.put(tf.sections[i],0);
        }
        
    }

    public void unload() {

        //Only allow one assistant to access the delivery...This is necessary for multiple assistants.
        synchronized (tf.delivery) {
            if(tf.isDelivered == false){
                return;
            }
            System.out.printf("<%d> <%s> Assistant Grabbed Delivery\n",tick.get(), Thread.currentThread().getId());

            // Iterate through delivery and collect items to add to the assistant's inventory
            List<String> itemsToAdd = new ArrayList<>();
            for (String item : tf.delivery) {
                if (itemsToAdd.size() < 10) {
                    itemsToAdd.add(item);
                } else {
                    // Break if the inventory is full
                    break;
                }
            }

            // Copy items from the list to the assistant's inventory
            for (String item : itemsToAdd) {
                inventory.put(item, inventory.get(item) + 1);
            }

            // Remove items from delivery
            tf.delivery = new String[10];
            tf.isDelivered = false;
            
        }
    }





    



    @Override
    public void run(){
        while(true){
            int tickToPerformAction = (1000 * daysToRun);
            //This works, however it is definitly not optimal as it wastes CPU cycles? 
            while (tick.get() < tickToPerformAction) {
                //check if there is a delivery
                if((tf.isDelivered == true)){
                    //if there is a delivery, we want to unload
                    
                    unload();
                    //After adding those items to our inventory, we can begin to stock
                    tf.stock(inventory, tick);
                    
            

                    
                }
                    
                      
            }
            
            
            
                
            
        }

        

    }

    





}
