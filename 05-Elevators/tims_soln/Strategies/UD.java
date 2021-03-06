package elevator;
import java.util.*;
import java.lang.Math;
// TO:DO ACCOUNT FOR MAX CAPACITY

class UD extends Strategy {
    public List<Customer> finished = new ArrayList<Customer>();
    public double average_wait;
    public double average_turnover;

    public void runSimulation(Elevator e) {
        List<Customer> customers = e.customers;
        List<Customer> customers_in = new ArrayList<Customer>();
        int time = 0;

        // customers.sort(Comparator.comparing(Customer::getStart_time));

        int current_floor = 0;
        int direction = 1; // direction = 1 (going up), -1 (going down)
        while (customers.size() > 0 || customers_in.size() > 0) {
            //Check for customers in elevator ready to leave at current floor
            List<Customer> exit_customers = customersExitFloor(customers_in, current_floor);

            //Check for waiting customers at current floor
            Boolean opened_doors = false;
            if (exit_customers.size() != 0) {
                time += e.door_time/2;
                opened_doors = true;
                for (Customer c : exit_customers) {
                    // Handle timing
                    int turnover = time - c.getStart_time();
                    int burst = (e.door_time + Math.abs(current_floor - c.getFloor_start())*e.floor_time); //Burst time = doortime + time to travel between floors
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
            List<Customer> ready_customers = customersAtFloor(customers, current_floor, time);
            if (ready_customers.size() != 0 && customers_in.size() != 4) {

                //incredment time by door if they havent been opened already
                if (!opened_doors) {
                    time += e.door_time/2; //open door
                }

                //Load in customers
                while (customers_in.size() != 4 && ready_customers.size() > 0) {
                    Customer c = ready_customers.get(0);
                    customers_in.add(c);
                    customers.remove(c);
                    ready_customers.remove(c);
                }
                ready_customers.clear();
            }
            if (opened_doors) {
                time += e.door_time/2;
            }

            current_floor += direction;
            time += e.floor_time;
            if (current_floor == e.floor_count - 1) {
                // Then at top, switch direction
                direction = -1;
            } else if (current_floor == 0) {
                //Then at bottom, switch direction
                direction = 1;
            }
        }
        // System.out.println("--- UD --- ");
        // printData(finished);
        average_wait = averageWait(finished);
        average_turnover = averageTurnover(finished);
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
