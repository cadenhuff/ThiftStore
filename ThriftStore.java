import java.util.Dictionary;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class ThriftStore{
    public static final int NUM_TICKS = 1000;
    String[] sections = {"electronics", "clothes", "furniture", "toys", "sporting goods", "books"};


    //Holds the delivery that was delivered by the Deliverer Thread
    public String[] delivery;
    //bool Value that lets assistants know if there is a delivery present in the store
    public boolean isDelivered;
    //Dictionary that holds storeInventory
    Dictionary<String, Integer> storeInventory= new Hashtable<>();
    
    
    public ThriftStore(){
        this.delivery = new String[10];
        this.isDelivered = false;
        
        //Initialize storeInventroy with 5 items in each section
        for(int i = 0; i<6; i++){
            storeInventory.put(sections[i],5);
        }
    }

    
    

    public void buy(AtomicInteger tick){
        //Used Gaussian Distribution to get an average of 10 ticks per customer for purchases.s
        Random random = new Random();
        double randomValue = Math.abs(random.nextGaussian() * 5 + 10);
        int randomNumber = (int) Math.round(randomValue);
        int tickToPerformAction = tick.get() + randomNumber;
        

        while (tick.get() < tickToPerformAction) {
                    
                      
        }
        int randomInt = random.nextInt(6);
    
        //Wait if there is nothing in the given section.
        if(storeInventory.get(sections[randomInt]) == 0){
            System.out.printf("<%d> <%s> Customer Waiting for %s \n",tick.get(), Thread.currentThread().getId(),sections[randomInt]);
            
        }
        while(storeInventory.get(sections[randomInt]) == 0){}
        

        //Only want one thread at a time to access storeInventory at a given time since this is critical data
        synchronized(storeInventory){
            int currentValue = storeInventory.get(sections[randomInt]);

            // Decrement the value by one
            int newValue = currentValue - 1;
            
            // Put the new value back into the dictionary
            storeInventory.put(sections[randomInt], newValue);
            System.out.printf("<%d> <%s> Customer Bought %s \n",tick.get(), Thread.currentThread().getId(),sections[randomInt]);
            
        }
        
    }

    public void stock(Dictionary<String,Integer> inventory, AtomicInteger tick){

        //Run until out of items  in Inventory
        while(!inventory.isEmpty() && !allValuesAreZero(inventory)){
            //Determine how long it will take to get to section with given number of items in inventory
            int tickToPerformAction = tick.get() + 10 + (countNumsInInventory(inventory));
            
            //Determine section to stock
            String sectionToStock = "";
            Enumeration<String> keys = inventory.keys();
            while (keys.hasMoreElements()) {
                String key = keys.nextElement();
                int value = inventory.get(key);
                if (value > 0){
                    sectionToStock = key; 
                    break;
                }
            }
            System.out.printf("<%d> <%s> Assistant Began Stocking Section %s = %d\n",tick.get(), Thread.currentThread().getId(),sectionToStock,storeInventory.get(sectionToStock));
            while(tick.get() < tickToPerformAction){

            }
            

            

            //Use synchronize keyword to only allow one thread to access storeInventory data at a given time. 
            synchronized(storeInventory){
                int currentValue = storeInventory.get(sectionToStock);

                // Increment the value by how many in inventory
                int newValue = currentValue + inventory.get(sectionToStock);
                
                // Put the new value back into the store Inventory
                storeInventory.put(sectionToStock, newValue);
                System.out.printf("<%d> <%s> Assistant Finished Stocking Section %s = %d\n",tick.get(), Thread.currentThread().getId(),sectionToStock,storeInventory.get(sectionToStock));

            }
            //delete from inventory
            inventory.put(sectionToStock, 0);

        }
    }

    //Helper Function for stock
    private int countNumsInInventory(Dictionary<String,Integer> inventory) {
        int count = 0;
        for (int val : ((Hashtable<String, Integer>) inventory).values()) {
            count += val;
        }
        return count;
    }

    //Helper Function for Stock
    private static boolean allValuesAreZero(Dictionary<String, Integer> map) {
        for (int value : ((Hashtable<String, Integer>) map).values()) {
            if (value != 0) {
                return false;
            }
        }
        return true;
    }
    
    //Prints the current store inventory...Used for Testing
    public void printInventory(){
        Enumeration<String> k = storeInventory.keys();
        while (k.hasMoreElements()) {
            String key = k.nextElement();
            System.out.println("Key: " + key + ", Value: "
                               + storeInventory.get(key));
        }

    }

    //Primary Function of ThriftStore Object
    //This function serves as the "tick" that all other threads look to do determine when to make their decisions
    public void thriftStoreDay( AtomicInteger tick, int tickLength,int daysToRun){
        
        while(tick.get() < (NUM_TICKS * daysToRun) ){
            
            try{
                Thread.sleep(tickLength);
            }catch(InterruptedException e){
                e.printStackTrace();
            }
            
            tick.getAndIncrement();
            

         
        }
        
        
    }


}




//Make Main class