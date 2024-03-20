import java.util.concurrent.atomic.AtomicInteger;



public class Main{
    
    //Create Thrift Store Object
    ThriftStore thriftStore = new ThriftStore();
    //Create an Assistant 
    Assistant assistant = new Assistant(thriftStore);
    //Create a Customer
    Customer customer = new Customer(thriftStore);




    
    

    public static void main(String[] args){
        
        
        //Create Thrift Store Object
        ThriftStore thriftStore = new ThriftStore();
        //Create an Assistant 
        Assistant assistant = new Assistant(thriftStore);
        Thread assistantThread = new Thread(assistant);
        //Create a Customer
        Customer customer = new Customer(thriftStore);
        Thread customerThread = new Thread(customer);

        //Create a Deliverer

        while(true){
            thriftStore.thriftStoreDay(assistantThread, customerThread);
        }
            
         
    }




    


}
