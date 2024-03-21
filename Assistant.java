import java.util.concurrent.atomic.AtomicInteger;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;
import java.util.Random;

public class Assistant implements Runnable {
    ThriftStore tf;
    AtomicInteger tick;
    Dictionary<String, Integer> inventory= new Hashtable<>();

    public Assistant(ThriftStore tf, AtomicInteger tick){
        this.tf = tf;
        this.tick = tick;
        for(int i = 0; i<6; i++){
            inventory.put(tf.sections[i],0);
        }
        
    }

    public void unload() {
        System.out.printf("<%d> <%s> Assistant Grabbed Delivery\n",tick.get(), Thread.currentThread().getId());
        synchronized (tf.delivery) {
            List<String> itemsToAdd = new ArrayList<>();

            // Iterate through delivery and collect items to add to the assistant's inventory
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
        //notify();
    }





    



    //Since tasks are linear
    public void run(){
        while(true){
            int tickToPerformAction = 1000;
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
            System.out.println("heeeyy");
                
            
        }

        

    }

    





}
