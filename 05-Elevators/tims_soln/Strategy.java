package elevator;
import java.util.*;
import java.lang.Math;

/**
 * An abstract class for the strategy
 */
public abstract class Strategy {
    public abstract void runSimulation(Elevator e);

    public void printSimulation(List<Customer> customers) {
        System.out.println("|  ID  |  Arrival  |  Completed  |  Turnover  |  Burst  |  Wait  |");
        System.out.println("------------------------------------------------------------------");
        customers.sort(Comparator.comparing(Customer::getStart_time));
        for (Customer c : customers) {
            System.out.print("| " + c.getId() + " ");
            System.out.print("| " + c.getStart_time() + " ");
            System.out.print("| " + c.getFinish_time() + " ");
            System.out.print("| " + c.getTurnover() + " ");
            System.out.print("| " + c.getBurst() + " ");
            System.out.println("| " + c.getWait() + " |");
        }
        double average_wait = 0;
        double average_turnover = 0;
        for (Customer c : customers) {
            average_wait += c.getWait();
            average_turnover += c.getTurnover();
        }
        average_wait = average_wait/customers.size();
        average_turnover = average_turnover/customers.size();

        System.out.println("Average wait: " +  average_wait);
        System.out.println("Average turnover: " +  average_turnover);
    }

    public void printInitial(List<Customer> customers) {
        customers.sort(Comparator.comparing(Customer::getStart_time));
        for (Customer c : customers) {
            System.out.print("| " + c.getStart_time() + " ");
            System.out.print("| " + c.getFloor_start() + " ");
            System.out.println("| " + c.getFloor_finish() + " ");
        }
    }

    public void printData(List<Customer> customers) {
        double average_wait = 0;
        double average_turnover = 0;
        for (Customer c : customers) {
            average_wait += c.getWait();
            average_turnover += c.getTurnover();
        }
        average_wait = average_wait/customers.size();
        average_turnover = average_turnover/customers.size();

        System.out.println("Average wait: " +  average_wait);
        System.out.println("Average turnover: " +  average_turnover);
    }

    public double averageWait(List<Customer> customers) {
        double average_wait = 0;
        for (Customer c : customers) {
            average_wait += c.getWait();
        }
        average_wait = average_wait/customers.size();
        return average_wait;
    }

    public double averageTurnover(List<Customer> customers) {
        double average_turnover = 0;
        for (Customer c : customers) {
            average_turnover += c.getTurnover();
        }
        average_turnover = average_turnover/customers.size();
        return average_turnover;
    }
}
