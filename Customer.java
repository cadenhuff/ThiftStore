public class Customer implements Runnable {
    ThriftStore tf;

    public Customer(ThriftStore tf){
        this.tf = tf;
    }

    public void run(){
        tf.consume();

        
    }



    //Buy Function..... Run ever ten seconds







}
