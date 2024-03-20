public class Main {
    
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
        //Create a Customer
        Customer customer = new Customer(thriftStore);

        while(true){
            thriftStore.thriftStoreDay();
        }
            
         
    }




    


}
