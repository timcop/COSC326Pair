package elevator;
import java.util.*;


/**
 * Creates a class for a customer to record the time they started waiting,
 * the time they finished their journey, the floor they started on, and
 * the floor they finished on.
 *
 * @author Tim Copland
 * @author Magdeline Huang
 */
public class Customer {

    /* Data fields for the attributes of each customer */
    private int start_time;
    private int floor_start;
    private int floor_finish;
    private int finish_time;
    private int id;
    private int turnover;
    private int burst;
    private int wait;
    /* ATTN: Perhaps we should also include customer wait time and customer travel time */

    /**
     * Constructer for the Customer class
     * @param time The time the customer started waiting
     * @param start The floor the customer started on
     * @param finish The floor the customer finished on
     */
    public Customer(int time, int start, int finish, int id) {
        this.start_time = time;
        this.floor_start = start;
        this.floor_finish = finish;
        this.id = id;
    }

    /**
     * Returns the time the customer started waiting
     * @return The time the customer started waiting
     */
    public int getStart_time() {
        return start_time;
    }

    /**
     * Returns the time the customer finished their journey
     * @return The time the customer finished their journey
     */
    public int getFinish_time() {
        return finish_time;
    }

    /**
     * Returns the floor the customer started on
     * @return The floor the customer started on
     */
    public int getFloor_start() {
        return floor_start;
    }

    /**
     * Returns the floor the customer finished on
     * @return The floor the customer finished on
     */
    public int getFloor_finish() {
        return floor_finish;
    }

    public int getId() {
        return id;
    }

    public int getTurnover() {
        return turnover;
    }

    public int getBurst() {
        return burst;
    }

    public int getWait() {
        return wait;
    }

    public void setTurnover(int turnover) {
        this.turnover = turnover;
    }

    public void setBurst(int burst) {
        this.burst = burst;
    }

    public void setWait(int wait) {
        this.wait = wait;
    }

    public void setFinish_time(int finish) {
        this.finish_time = finish;
    }
}
