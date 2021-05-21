import java.util.ArrayList;
import java.util.Random;
import java.lang.Math;

/**
 * Creates a class for the elevator with customers
 * 
 * @author Tim Copland
 * @author Magdeline Huang
 */
public class Elevator {

    public ArrayList<Customer> customers = new ArrayList<Customer>(); /* Initialises the elevator with a list of customers */
    public final static int floor_count = 10; /* The total number of floors in the elevator */
    public final static int floor_time = 5; /* The time it takes to travel from one floor to the next */
    public final static int door_time = 10; /* The time it takes for a door to open and close */

    /**
     * Constructor for the Elevator class
     * ATTN: What do these parameters mean?
     * @param N
     * @param final_time
     */
    public Elevator(int N, int final_time) {
        setCustomers(N, final_time);
    }

    /**
     * Sets the customers in the elevator
     * ATTN: What do these parameters mean?
     * @param N
     * @param final_time
     */
    public void setCustomers(int N, int final_time) {
        for (int i = 0; i < N; i++){
            Random random;
            int rand_time = random.nextInt(final_time); /* Starts the customer at a random time */
            int rand_floor_start = random.nextInt(floor_count); /* Starts the customer at a random floor */
            int rand_floor_finish = random.nextInt(floor_count); /* Sets a desired target floor for the customer */

            /* ATTN: What is this loop doing? */
            while (rand_floor_finish == rand_floor_start) {
                rand_floor_finish = random.nextInt(floor_count);
            }

            /* Initialises the customer class and adds it to the list of customers in the elevator */
            Customer c = new Customer(rand_time, rand_floor_start, rand_floor_finish);
            this.customers.add(c)
        }
    }
}
