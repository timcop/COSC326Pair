package elevator;
import java.util.*;

public class Test {

    public static void main(String[] args) {

        Elevator e1 = new Elevator(10, 100);
        for (Customer c : e1.customers) {
            System.out.println(c.getFinish_time());
        }
        FIFS fifs = new FIFS();
        fifs.runSimulation(e1);

    }
}
