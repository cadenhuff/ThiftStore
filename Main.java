import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;



public class Main{
    static int numOfAssistants = 1;
    static int numOfCustomers = 1;
    static int tickLength = 1000;
    static int daysToRun = 10;

    public static void main(String[] args){
        //Takes in User input for number of assistants and customers and for tick length with Error Handling.
        Scanner scanner = new Scanner(System.in);

        boolean validInput = false;
        while(!validInput){
            try{
                System.out.print("Number of Assistants: ");
                numOfAssistants = scanner.nextInt();
                validInput = true;

            }catch (InputMismatchException e) {
                System.out.println("Invalid input! Please enter an integer.");
                scanner.next(); // Consume the invalid input
            }

        }
        validInput = false;
        while(!validInput){
            try{
                System.out.print("Number of Customers: ");
                numOfCustomers = scanner.nextInt();
                validInput = true;

            }catch (InputMismatchException e) {
                System.out.println("Invalid input! Please enter an integer.");
                scanner.next(); // Consume the invalid input
            }

        }

        validInput = false;
        while(!validInput){
            try{
                System.out.print("Tick Length in Milliseconds: ");
                tickLength = scanner.nextInt();
                validInput = true;
            }catch (InputMismatchException e){
                System.out.println("Invalid input! Please enter an integer.");
                scanner.next();
            }
        }

        validInput = false;
        while(!validInput){
            try{
                System.out.print("Days to Run: ");
                daysToRun = scanner.nextInt();
                validInput = true;
            }catch (InputMismatchException e){
                System.out.println("Invalid input! Please enter an integer.");
                scanner.next();
            }
        }


        scanner.close();


        //Using this to syncronize ticks between threads
        AtomicInteger tick = new AtomicInteger(0);
        //Create Thrift Store Object
        ThriftStore thriftStore = new ThriftStore();
        //Create Assistant Threads
        Thread[] assistants = new Thread[numOfAssistants];
        for (int i = 0; i<numOfAssistants; i++){
            assistants[i] = new Thread(new Assistant(thriftStore, tick,daysToRun));
        }
        //Create Customer Threads
        Thread[] customers = new Thread[numOfCustomers];

        for (int i = 0; i<numOfCustomers; i++){
            customers[i] = new Thread(new Customer(thriftStore, tick));
        }
        
    
        //Create a Deliverer (Only need one for Project Specifications however using the same methods as Customers and Assistants can create multiple)
        Deliverer deliverer = new Deliverer(thriftStore, tick);
        Thread delivererThread = new Thread(deliverer);
        

        //Start all the assistants
        for (Thread thread : assistants) {
            thread.start();
        }
        //Start all the Customers
        for (Thread thread : customers) {
            thread.start();
        }
        //Start the deliverer
        delivererThread.start();
        
        //Main while loop that will continue running forever since thrift store is open forever.
        while(true){
            
            //ThriftStore method to simulate one day
            thriftStore.thriftStoreDay(tick,tickLength,daysToRun);
            
            
            
            
        }
            
         
    }




    


}
