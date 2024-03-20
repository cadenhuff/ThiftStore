
public class ThriftStore{

    //The funcs and fields in this class is just for testing
    private int data = 0;

    public synchronized void produce(){
        data++;
        System.out.printf("%d\n",data);
    }

    public synchronized void consume(){
        data--;
        System.out.printf("%d\n",data);
    }

    public static void main(String[] args){
        //This section is gonna be moved to MAIN class
        /*ThriftStore thriftStore = new ThriftStore();
        Runnable assistant = thriftStore.new Assistant();
        Runnable Customer = thriftStore.new Customer();
        Thread assistantThread = new Thread(assistant);
        assistantThread.start();
        Thread customerThread = new Thread(Customer);
        customerThread.start();*/



    }


}




//Make Main class