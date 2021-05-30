package elevator;
import java.util.*;
import java.io.*;

public class Test {

    public static void main(String[] args) {

        // TO DO: Make it so we can test different strats on the same
        // customers
        int minute = 60;
        int hour = 3600;
        Elevator e = new Elevator(Integer.parseInt(args[0]), 3600);
        // Elevator e3 = new Elevator(50, hour);

        FIFS fifs = new FIFS();
        UD ud = new UD();
        SJF2 sjf2 = new SJF2();
        fifs.runSimulation(e);
        ud.runSimulation(e);
        sjf2.runSimulation(e);
        var fileName = "data250/averages" + args[0] +".txt";
        try (FileWriter f = new FileWriter(fileName, true);
                BufferedWriter b = new BufferedWriter(f);
                PrintWriter p = new PrintWriter(b);) {
                p.print(fifs.average_wait + " " + fifs.average_turnover + " ");
                p.print(ud.average_wait + " " + ud.average_turnover + " ");
                p.println(sjf2.average_wait + " " + sjf2.average_turnover + " ");

        } catch (IOException i) {
            i.printStackTrace();
        }
    }
}
