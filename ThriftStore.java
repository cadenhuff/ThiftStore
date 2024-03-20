
public class ThriftStore{

    //The funcs and fields in this class is just for testing
    private int data = 0;
    //This could hold delivery, assistant could look at the delivery by reference of TF.
    public String[] delivery;
    public synchronized void produce(){
        data++;
        System.out.printf("%d\n",data);
    }

    public synchronized void consume(){
        data--;
        System.out.printf("%d\n",data);
    }

    //delivery function

    public void thriftStoreDay(){
        
    }


}




//Make Main class