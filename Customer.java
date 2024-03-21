import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;


public class Customer implements Runnable {
    ThriftStore tf;
    AtomicInteger tick;

    public Customer(ThriftStore tf, AtomicInteger tick){
        this.tf = tf;
        this.tick = tick;
    }

    public void run(){
                //While Program still running



    }



    //Buy Function..... Run ever ten seconds







}
