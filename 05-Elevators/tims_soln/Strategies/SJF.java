package elevator;
import java.util.*;
import java.lang.Math;

// SJF Where we sort jobs for people IN the elevator based on burst time.
// If we're currently handling a job and the evelator isn't full, then let people in on the way.
// Then resort the customers in elevator based on new burst time
// This is prone to starvation...
class SJF extends Strategy {
    public List<Customer> finished = new ArrayList<Customer>();

    public void runSimulation(Elevator e) {
        List<Customer> customers_out = e.customers; //All the customers that are waiting to use the lift, hence "outside"
        List<Customer> customers_in = new ArrayList<Customer>(); //Customers currently in the lift


        // A potential data structure would be a queue at each floor (makes sense in real life)

        while (customers.size() > 0 || customers_in.size() > 0) {
            // Wait until a customer is ready
            // Travel to customer, on the way check each floor to see if anyone new has come
            // If so, let them in if there is space

            // Special case: Elevator is empty
            // When we have our first job, travel to the that job letting in customers on the way, but don't handle their jobs until first person is picked up.
            // Also leave space for first customer yet to be picked up
            // Once we pick that person up, then we sort based on burst time and continue with job with lowest burst.
            // Still check customers on each floor on the way, if we let one in recalculate burst times and continue with job with lowest burst.

        }


    }
}
