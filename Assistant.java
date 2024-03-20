import java.util.concurrent.atomic.AtomicInteger;

public class Assistant implements Runnable {
    ThriftStore tf;
    AtomicInteger tick;

    public Assistant(ThriftStore tf, AtomicInteger tick){
        this.tf = tf;
        this.tick = tick;
    }




    public void run(){
        tf.produce(tick);


        //while...


        


        
    }

    





}
