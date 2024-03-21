import java.util.concurrent.atomic.AtomicInteger;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Random;

public class Assistant implements Runnable {
    ThriftStore tf;
    AtomicInteger tick;
    public String[] inventory;

    public Assistant(ThriftStore tf, AtomicInteger tick){
        this.tf = tf;
        this.tick = tick;
        this.inventory = new String[10];
        
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
            for (int i = 0; i < itemsToAdd.size(); i++) {
                inventory[i] = itemsToAdd.get(i);
            }

            // Remove items from delivery
            tf.delivery = new String[10];
            tf.isDelivered = false;
            
        }
        //notify();
    }
    public int countNumsInInventory(){
        int count = 0;
        for (String str : inventory) {
            if (str != null) {
                count++;
            }
        }



        return count;
    }
    public void stock(){
        //Look at first item in inventory
        //this first while loop might be buggy
        while(inventory[0] != null){
            int tickToPerformAction = tick.get() + 10 + (countNumsInInventory());
            System.out.printf("<%d> <%s> Assistant Began Stocking Section %s = %d\n",tick.get(), Thread.currentThread().getId(),inventory[countNumsInInventory()-1],tf.storeInventory.get(inventory[countNumsInInventory()-1]));
            while(tick.get() < tickToPerformAction){

            }
            
            synchronized(tf.storeInventory){
                int currentValue = tf.storeInventory.get(inventory[countNumsInInventory()-1]);

                

                // Increment the value by one
                int newValue = currentValue + 1;
                System.out.println("IM HERE");

                // Put the new value back into the dictionary
                tf.storeInventory.put(inventory[countNumsInInventory() - 1], newValue);
                System.out.printf("<%d> <%s> Assistant Finished Stocking Section %s = %d\n",tick.get(), Thread.currentThread().getId(),inventory[countNumsInInventory()-1],tf.storeInventory.get(inventory[countNumsInInventory()-1]));
            }
        }
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
                    stock();
                    
            

                    
                }
                    
                      
            }
            System.out.println("heeeyy");
                
            
        }

        

    }

    





}
