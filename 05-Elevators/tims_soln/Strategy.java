import java.util.ArrayList;
import java.util.Random;
import java.lang.Math;

/**
 * An abstract class for the strategy 
 */
public abstract class Strategy {

    public Elevator problem;

    public Strategy(int N, int final_time) {
        problem = new Elevator(N, final_time);
    }

    abstract void runSimulation();
}

class FIFS extends Strategy {

    public static void insertionSortArrayList(ArrayList<Customer> list) {
        for (int i = 1; i < list.length(); i++) {
            Customer current = list.get(i);
            current_val = current.start_time;
            int j = i-1;
            while (i > -1 && list.get(j).start_time > current_val) {
                list.set(i+1, list.get(i));
                i--;
            }
            list.set(i+1, current);
        }
        problem.customers = list;
    }

    public static void runSimulation() {
        int time = 0;

        insertionSortArrayList(problem.customers);
        // Now the customers are sorted in order of arrival time, treat customers like a queue
        int current_floor = 0
        while problem.customers.length() > 0 {
            current = problem.customers.pop();

            time += Math.abs(current_floor - current.floor_start) * problem.floor_time; //Travel to customers floor, note if it's the same then nothing added
            current_floor = current.floor_start;
            time += problem.door_time; //Open the door

            time += Math.abs(current_floor - current.floor_finish) * problem.floor_time; //Travel to desired floor
            current_floor = current.floor_finish;
            time+= problem.door_time; //Open the door

            customer.finish_time = time;
        }
        // Total wait time of each customer can be found by doing customer.finish_time - customer.start_time
        // Total wait time of problem is what time is at the end
    }
}
