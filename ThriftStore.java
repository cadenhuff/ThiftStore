


public class ThriftStore{

    private int data = 0;

    public synchronized void produce(){
        data++;
        System.out.printf("%d\n",data);
    }

    public synchronized void consume(){
        data--;
        System.out.printf("%d\n",data);
    }

    class Assistant implements Runnable {
        
        public void run(){
            produce();
        }
    }

    class Customer implements Runnable {
    
        public void run(){
            consume();
        }
    
    }

    public static void main(String[] args){
        ThriftStore thriftStore = new ThriftStore();
        Runnable assistant = thriftStore.new Assistant();
        Runnable Customer = thriftStore.new Customer();
        Thread assistantThread = new Thread(assistant);
        assistantThread.start();
        Thread customerThread = new Thread(Customer);
        customerThread.start();



    }


}




//Make Main class