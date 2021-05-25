package elevator;
import java.util.*;

public class Test {

    public static void main(String[] args) {

        Elevator e1 = new Elevator(10, 100);
        Elevator e2 = new Elevator(10, 100);
        FIFS fifs = new FIFS();
        UD ud = new UD();
        fifs.runSimulation(e1);
        ud.runSimulation(e2);
    }
}
