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
        while(true){
            //when I dont have this system print,,, concurrency doesnt work wtf
            //System.out.printf("This is customer %s\n",Thread.currentThread().getId());
            tf.buy(tick);

        }


    }



    //Buy Function..... Run ever ten seconds







}
