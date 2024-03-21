import java.util.Dictionary;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

public class ThriftStore{

    public static final int NUM_THREADS = 1;
    public static final int NUM_TICKS = 1000;
    String[] sections = {"electronics", "clothes", "furniture", "toys", "sporting goods", "books"};



    //The funcs and fields in this class is just for testing
    public int data = 0;
    //This could hold delivery, assistant could look at the delivery by reference of TF.
    public String[] delivery;
    public boolean isDelivered;


    //Maybe shoudlnt use dict cause if both customer and prod want to access differnet sections... jhow would that work
    Dictionary<String, Integer> storeInventory= new Hashtable<>();
    
    
    public ThriftStore(){
        this.delivery = new String[10];
        this.isDelivered = false;
        
        for(int i = 0; i<6; i++){
            storeInventory.put(sections[i],5);
        }
    }

    private int countNumsInInventory(Dictionary<String,Integer> inventory) {
        int count = 0;
        for (int val : ((Hashtable<String, Integer>) inventory).values()) {
            count += val;
        }
        return count;
    }


    private static boolean allValuesAreZero(Dictionary<String, Integer> map) {
        for (int value : ((Hashtable<String, Integer>) map).values()) {
            if (value != 0) {
                return false;
            }
        }
        return true;
    }


    public void stock(Dictionary<String,Integer> inventory, AtomicInteger tick){

        //Look at first item in inventory
        //this first while loop might be buggy
        //NEED TO MAKE INVENTORY A DICT
        while(!inventory.isEmpty() && !allValuesAreZero(inventory)){
            int tickToPerformAction = tick.get() + 10 + (countNumsInInventory(inventory));
            System.out.println(inventory);
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
            //When I put this func into Thriftstore class, change tf.storeInventory to this
            //This right here is the critical section, but only critical for the speific section that the assistant is stocking...In other
            //Words two assistants can stock differnest sections, but jsut not the same one. 


            
            synchronized(storeInventory){
                int currentValue = storeInventory.get(sectionToStock);

                // Increment the value by how many in

                int newValue = currentValue + inventory.get(sectionToStock);
                

                // Put the new value back into the dictionary
                storeInventory.put(sectionToStock, newValue);
                System.out.printf("<%d> <%s> Assistant Finished Stocking Section %s = %d\n",tick.get(), Thread.currentThread().getId(),sectionToStock,storeInventory.get(sectionToStock));

            }
            //delete from inventory
            
            inventory.put(sectionToStock, 0);
            System.out.println(storeInventory);
        }
    }

    

    public void printInventory(){
        Enumeration<String> k = storeInventory.keys();
        while (k.hasMoreElements()) {
            String key = k.nextElement();
            System.out.println("Key: " + key + ", Value: "
                               + storeInventory.get(key));
        }

    }


    public void thriftStoreDay(Thread assistant, Thread customer, AtomicInteger tick){
        
        while(tick.get() < NUM_TICKS){
            //printInventory();
            //System.out.println(storeInventory);
            try{
                Thread.sleep(50);
            }catch(InterruptedException e){
                e.printStackTrace();
            }
            //System.out.printf("%d\n", tick.get());
            tick.getAndIncrement();
            //notify();

         
        }
        
    }


}




//Make Main class