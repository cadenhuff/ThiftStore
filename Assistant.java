public class Assistant implements Runnable {
    private ThriftStore tf;

    public Assistant(ThriftStore tf){
        this.tf = tf;
    }


    public void run(){
        tf.produce();
    }
}
