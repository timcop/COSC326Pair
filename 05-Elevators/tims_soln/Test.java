package elevator;
import java.util.*;

public class Test {

    public static void main(String[] args) {

        // TO DO: Make it so we can test different strats on the same
        // customers
        int minute = 60;
        int hour = 3600;
        Elevator e = new Elevator(50, hour);
        Elevator e3 = new Elevator(50, hour);

        FIFS fifs = new FIFS();
        UD ud = new UD();
        SJF2 sjf2 = new SJF2();
        fifs.runSimulation(e);
        ud.runSimulation(e);
        sjf2.runSimulation(e);
    }
}
