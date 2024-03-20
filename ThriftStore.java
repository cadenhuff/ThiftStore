import java.util.concurrent.atomic.AtomicInteger;

public class ThriftStore{

    //The funcs and fields in this class is just for testing
    private int data = 0;
    //This could hold delivery, assistant could look at the delivery by reference of TF.
    public String[] delivery;

    public int electronics = 0;




    public synchronized void produce(AtomicInteger tick){
        data++;
        System.out.printf("%d and time is %d\n",data, tick);
    }

    public synchronized void consume(){
        data--;
        System.out.printf("%d\n",data);
    }

    // need delivery function...... Actually Delivery could be its own thread




    public void thriftStoreDay(Thread assistant, Thread customer, AtomicInteger tick){
        //int tick = 0;
        //delivery.start();
        //AtomicInteger tick = new AtomicInteger(0);
        assistant.start();
        customer.start();


        //Using this to syncronize ticks between threads
        //AtomicInteger tick = new AtomicInteger(0);
        while (tick.get() < 1000){
            System.out.println("Tick: " + tick.incrementAndGet());

        }
    }


}




//Make Main class