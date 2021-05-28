package elevator;
import java.util.*;
import java.lang.Math;

// SJF Where we sort jobs for people IN the elevator based on burst time.
// If we're currently handling a job and the evelator isn't full, then let people in on the way.
// Then resort the customers in elevator based on new burst time
// This is prone to starvation...
class SJF2 extends Strategy {
    public List<Customer> finished = new ArrayList<Customer>();

    public void runSimulation(Elevator e) {
        List<Customer> customers_out = e.customers; //All the customers that are waiting to use the lift, hence "outside"
        List<Customer> customers_in = new ArrayList<Customer>(); //Customers currently in the lift
        int time = 0;
        int current_floor = 0;

        // A potential data structure would be a queue at each floor (makes sense in real life)

        while (customers_out.size() > 0 || customers_in.size() > 0) { // While there are still customers outside or inside the lift
            if (customers_in.size() != 0){
                customers_in.sort(Comparator.comparing(Customer::getBurst));
                Customer job = customers_in.get(0);
                int destination_floor = job.getFloor_finish();
                if (destination_floor == current_floor) {
                    /// this might be the reason
                }
                while (destination_floor != current_floor) {
                    int direction = (destination_floor - current_floor)/Math.abs(current_floor-destination_floor);
                    current_floor += direction;
                    time += e.floor_time;

                    // Handle exiting and entering customers
                    List<Customer> exit_customers = customersExitFloor(customers_in, current_floor);
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
                        if (!opened_doors && customers_in.size() != 4) {
                            time += e.door_time;
                        }

                        //Load in customers
                        while (customers_in.size() != 4) {
                            Customer c = ready_customers.get(0);
                            customers_in.add(c);
                            customers_out.remove(c);
                            ready_customers.remove(c);
                        }
                        ready_customers.clear();
                    }
                    customers_in.sort(Comparator.comparing(Customer::getBurst));
                    if (customers_in.size() != 0 && job != customers_in.get(0)) {
                        job = customers_in.get(0);
                        destination_floor = job.getFloor_finish();
                    }
                }

            } else {
                customers_out.sort(Comparator.comparing(Customer::getStart_time)); //FIFS
                Customer job = customers_out.get(0);

                if (job.getStart_time() < time) { //Wait for customer to press button
                    time = job.getStart_time();
                }
                time += Math.abs(current_floor - job.getFloor_start()) * e.floor_time; //Travel to customers floor, note if it's the same then nothing added
                current_floor = job.getFloor_start();
                time += e.door_time; //Open the door and close door
                List<Customer> ready_customers = customersAtFloor(customers_out, current_floor, time);
                for (Customer c : ready_customers) {
                    customers_in.add(c);
                    customers_out.remove(c);
                }
                ready_customers.clear();
            }
                // job is earliest arrival
            customers_in.sort(Comparator.comparing(Customer::getBurst));
            if (customers_in.size() != 0) {
                Customer job = customers_in.get(0);
            }
        }
        printSimulation(finished);
        e.customers = finished;
    }

    // Check to see if customers are waiting at a floor we're at
    public List<Customer> customersAtFloor(List<Customer> customers, int current_floor, int time) {
        List<Customer> customers_floor = new ArrayList<Customer>();
        for (Customer c : customers) {
            if (c.getFloor_start() == current_floor && c.getStart_time() <= time) {
                customers_floor.add(c);
            }
        }
        customers_floor.sort(Comparator.comparing(Customer::getStart_time));
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
