package elevator;
import java.util.*;
import java.lang.Math;

class FIFS extends Strategy {
    public List<Customer> finished = new ArrayList<Customer>();
    // public void insertionSortArrayList(List<Customer> list) {
    //     for (int i = 1; i < list.size(); i++) {
    //         Customer current = list.get(i);
    //         int current_val = current.getStart_time();
    //         int j = i-1;
    //         while (i > -1 && list.get(j).getStart_time() > current_val) {
    //             list.set(i+1, list.get(i));
    //             i--;
    //         }
    //         list.set(i+1, current);
    //     }
    //     problem.customers = list;
    // }

    public void runSimulation(Elevator e) {
        List<Customer> customers = e.customers;
        int time = 0;

        customers.sort(Comparator.comparing(Customer::getStart_time));
        // insertionSortArrayList(customers);
        // Now the customers are sorted in order of arrival time, treat customers like a queue
        int current_floor = 0;
        while (customers.size() > 0) {
            Customer current = customers.get(0);
            customers.remove(0);

            time += Math.abs(current_floor - current.getFloor_start()) * e.floor_time; //Travel to customers floor, note if it's the same then nothing added
            current_floor = current.getFloor_start();
            time += e.door_time; //Open the door

            time += Math.abs(current_floor - current.getFloor_finish()) * e.floor_time; //Travel to desired floor
            current_floor = current.getFloor_finish();
            time+= e.door_time; //Open the door

            int turnover = time - current.getStart_time();
            int burst = (2*e.door_time + Math.abs(current.getFloor_finish() - current.getFloor_start())*e.floor_time); //Burst time = 2*doortime + time to travel between floors
            int wait = turnover - burst;
            current.setFinish_time(time);
            current.setTurnover(turnover);
            current.setBurst(burst);
            current.setWait(wait);
            finished.add(current);
        }
        // Total wait time of each customer can be found by doing customer.finish_time - customer.start_time
        // Total wait time of problem is what time is at the end
        System.out.println("|  ID  |  Arrival  |  Completed  |  Turnover  |  Burst  |  Wait  |");
        System.out.println("------------------------------------------------------------------");
        finished.sort(Comparator.comparing(Customer::getId));
        for (Customer c : finished) {
            System.out.print("| " + c.getId() + " ");
            System.out.print("| " + c.getStart_time() + " ");
            System.out.print("| " + c.getFinish_time() + " ");
            System.out.print("| " + c.getTurnover() + " ");
            System.out.print("| " + c.getBurst() + " ");
            System.out.println("| " + c.getWait() + " |");

            // System.out.println(c.getId() + " " + c.getStart_time() + " " + c.getFinish_time() + " " + c.getTurnover() + " " + c.getBurst() + " " + c.getWait());
        }
    }
}
