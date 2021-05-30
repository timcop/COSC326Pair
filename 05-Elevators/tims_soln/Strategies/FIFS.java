package elevator;
import java.util.*;
import java.lang.Math;

// First in first served
class FIFS extends Strategy {
    public List<Customer> finished = new ArrayList<Customer>();
    public double average_wait;
    public double average_turnover;

    public void runSimulation(Elevator e) {
        List<Customer> customers = e.customers;
        int time = 0;

        customers.sort(Comparator.comparing(Customer::getStart_time));
        // Now the customers are sorted in order of arrival time, treat customers like a queue

        int current_floor = 0;
        while (customers.size() > 0) {
            Customer current = customers.get(0);
            customers.remove(0);

            // Wait until button is pressed by customer
            if (time < current.getStart_time()) {
                time = current.getStart_time();
            }

            time += Math.abs(current_floor - current.getFloor_start()) * e.floor_time; //Travel to customers floor, note if it's the same then nothing added
            current_floor = current.getFloor_start();
            time += e.door_time; //Open the door and close door

            time += Math.abs(current_floor - current.getFloor_finish()) * e.floor_time; //Travel to desired floor
            current_floor = current.getFloor_finish();
            time+= e.door_time; //Open and close the door

            int turnover = time - current.getStart_time();
            int burst = (e.door_time + Math.abs(current.getFloor_finish() - current.getFloor_start())*e.floor_time); //Burst time = 2*doortime + time to travel between floors
            int wait = turnover - burst;
            current.setFinish_time(time);
            current.setTurnover(turnover);
            current.setBurst(burst);
            current.setWait(wait);
            finished.add(current);
        }
        // printSimulation(finished);
        // System.out.println("--- FIFS --- ");
        // printData(finished);
        average_wait = averageWait(finished);
        average_turnover = averageTurnover(finished);
        e.customers = finished;
    }
}
