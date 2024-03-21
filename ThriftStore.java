import java.util.Dictionary;
import java.util.Enumeration;
import java.util.Hashtable;
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



    

    public synchronized void stock(int section){
        //Could have waiting for ticks in here
    }

    public synchronized void buy(){
        Random random = new Random();
        int randomNumber = random.nextInt(6);
        try{
            while(storeInventory.get(sections[randomNumber]) == 0){
                System.out.println("Nothign in this section gonna wait ");
                wait();
            }
        }catch(InterruptedException e){
            e.printStackTrace();
        }
        System.out.println("Found it");
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
            printInventory();
            try{
                Thread.sleep(1000);
            }catch(InterruptedException e){
                e.printStackTrace();
            }
            System.out.printf("%d\n", tick.get());
            tick.getAndIncrement();
            //notify();

         
        }
        
    }


}




//Make Main class