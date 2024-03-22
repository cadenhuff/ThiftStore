
import java.util.concurrent.atomic.AtomicInteger;


public class Customer implements Runnable {
    ThriftStore tf;
    AtomicInteger tick;

    public Customer(ThriftStore tf, AtomicInteger tick){
        this.tf = tf;
        this.tick = tick;
    }


    @Override
    public void run(){
        

        //Since Customer's one job is to buy, we basically just allow the buy function to define a Customers complete exectution.
        while(true){
            
            tf.buy(tick);

        }


    }



    







}
