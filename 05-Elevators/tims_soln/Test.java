package elevator;
import java.util.*;

public class Test {

    public static void main(String[] args) {

        // TO DO: Make ti so we can test different strats on the same
        // customers
        Elevator e1 = new Elevator(10, 100);
        Elevator e2 = new Elevator(10, 100);
        FIFS fifs = new FIFS();
        UD ud = new UD();
        fifs.runSimulation(e1);
        ud.runSimulation(e2);
    }
}
