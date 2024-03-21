import java.util.concurrent.atomic.AtomicInteger;



public class Main{
    
    



    public static void main(String[] args){
        //Using this to syncronize ticks between threads
        AtomicInteger tick = new AtomicInteger(0);
        //Create Thrift Store Object
        ThriftStore thriftStore = new ThriftStore();
        //Create an Assistant 
        Assistant assistant = new Assistant(thriftStore,tick);
        Thread assistantThread = new Thread(assistant);
        //Create a Customer
        Customer customer = new Customer(thriftStore,tick);
        Thread customerThread = new Thread(customer);

        //Create a Deliverer
        Deliverer deliverer = new Deliverer(thriftStore, tick)
        Thread delivererThread = new Thread(deliverer);
        //Main while loop that will continue running forevewr since thrift store is open forever.
        assistantThread.start();
        customerThread.start();
        delivererThread.start();
        while(true){

            thriftStore.thriftStoreDay(assistantThread, customerThread,tick);
            break;
            //reset tick
            //tick.set(0);
            
        }
            
         
    }




    


}
