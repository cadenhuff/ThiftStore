import java.util.concurrent.atomic.AtomicInteger;



public class Main{
    
    



    public static void main(String[] args){
        
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

        //Main while loop that will continue running forevewr since thrift store is open forever.
        while(true){

            thriftStore.thriftStoreDay(assistantThread, customerThread,tick);
            //reset tick
            tick.set(0);
            
        }
            
         
    }




    


}
