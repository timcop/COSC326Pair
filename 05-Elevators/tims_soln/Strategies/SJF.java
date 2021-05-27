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
        List<Customer> customers_burst_sorted = new ArrayList<Customer>(); //Customers currently in the lift sorted by burst time
        int time = 0;
        int current_floor = 0;

        // A potential data structure would be a queue at each floor (makes sense in real life)

        while (customers_out.size() > 0 || customers_in.size() > 0) { // While there are still customers outside or inside the lift
            //Check for customers in elevator ready to leave at current floor
            List<Customer> exit_customers = customersExitFloor(customers_in, current_floor);

            //Check for waiting customers at current floor
            List<Customer> ready_customers = customersAtFloor(customers_out, current_floor, time);
            Boolean opened_doors = false;
            if (exit_customers.size() != 0) {
                time += e.door_time;
                opened_doors = true;
                for (Customer c : exit_customers) {
                    // Handle timing
                    int turnover = time - c.getStart_time();
                    int burst = (2*e.door_time + Math.abs(current_floor - c.getFloor_start())*e.floor_time); //Burst time = 2*doortime + time to travel between floors
                    int wait = turnover - burst;
                    c.setFinish_time(time);
                    c.setTurnover(turnover);
                    c.setBurst(burst);
                    c.setWait(wait);
                    customers_in.remove(c);
                    finished.add(c);
                }
                exit_customers.clear();
            }

            if (ready_customers.size() != 0) {
                //increment time by door if they havent been opened already
                if (!opened_doors) {
                    time += e.door_time;
                }

                //Load in customers
                for (Customer c : ready_customers) {
                    customers_in.add(c);
                    customers_out.remove(c);
                }
                ready_customers.clear();
            }

            // Sort the customers in the lift according to burst time
            for (Customer c : customers_in) {
                int burst = (2*e.door_time + Math.abs(current_floor - c.getFloor_start())*e.floor_time); //Burst time = 2*doortime + time to travel between floors
                c.setBurst(burst);
                customers_burst_sorted.add(c);
                customers_burst_sorted.sort(Comparator.comparing(Customer::getBurst));

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
        
        printSimulation(finished);
    }

    // Check to see if customers are waiting at a floor we're at
    public List<Customer> customersAtFloor(List<Customer> customers, int current_floor, int time) {
        List<Customer> customers_floor = new ArrayList<Customer>();
        for (Customer c : customers) {
            if (c.getFloor_start() == current_floor && c.getStart_time() <= time) {
                customers_floor.add(c);
            }
        }
        return customers_floor;
    }

    // Check to see if we've hit a floor that our customers already in the elevator are waiting to exit at.
    public List<Customer> customersExitFloor(List<Customer> customers, int current_floor) {
        List<Customer> exit_customers = new ArrayList<Customer>();
        for (Customer c : customers) {
            if (c.getFloor_finish() == current_floor) {
                exit_customers.add(c);
            }
        }
        return exit_customers;
    }
}
