public class Assistant implements Runnable {
    ThriftStore tf;

    public Assistant(ThriftStore tf){
        this.tf = tf;
    }




    public void run(){
        tf.produce();


        //while...
        
    }
}
