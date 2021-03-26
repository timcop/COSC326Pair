/* Collective Control Elevator Algorithm
 *
 * 1. As long as there’s someone inside or ahead of the elevator who wants to go in the current direction, keep heading in that direction.
 * 2. Once the elevator has exhausted the requests in its current direction, switch directions if there’s a request in the other direction. 
 *    Otherwise, stop and wait for a call.
 * 
 * Assumptions:
 * - The elevator starts from the ground floor and only picks up people there for upwards motion.
 * - For downwards motion, the elevator starts from the top most floor and only picks up people from there.
 * - The elevator only takes in the first 4 people, irrespective of their desired floor number. To address this, we could sort
 *   for the lowest floor numbers?
 * - There is no one waiting outside for the elevator. This is quite ridiculous though.
 * 
 * To Verify:
 * - Can I improve the logic of this simulator? Esp those ridiculous assumptions.
 * - Is the main method actually functioning like how I intend it to?
 * - Are the overall wait and travel times being calculated properly?
 */

import java.util.*;

public class CCElevator {

    // Properties of the elevator and building
    static final int maxF = 10; // Max number of floors
    static final int maxP = 4; // Max number of passengers
    static final int floorTime = 5; // Time(s) to travel between floors
    static final int doorTime = 10; // Time(s) for the doors to open AND close

    // Utility variables
    static int[] passengers = new int[4]; // Array of passengers with their desired floor numbers
    static int currF = 0; // Current floor
    static int currP = 0; // Current number of passengers
    static int oWait = 0; // Overall wait time for the passengers
    static int oTravel = 0; // Overall travel time for the passengers

    public static void main(String[] args) {

        loadPassengersUp(); // Load initial passengers in
        doors(); // Time for doors to open and close
        up(); // Elevator moves up

        while (currF <= passengers[passengers.length-1]) {
            for (int j = 0; j < passengers.length; j++) {
                if (passengers[j] == currF) { // If the elevator has reached a passenger's desired floor
                    doors(); // Open and close the doors
                } else {
                    if (currF != maxF) { // Don't move up past the top most floor. Wait, is this actually necessary?
                        up();
                    }
                }
            }
        }
        loadPassengersDown();
        for (int k = 0; k < passengers.length; k++) {
            if (passengers[k] == currF) { // If the elevator has reached a passenger's desired floor
                doors(); // Open and close the doors
            } else {
                if (currF >= 0) { // Don't move down past the ground floor. Wait, is this actually necessary?
                    down();
                }
            }
        }

        System.out.println("-----------------------------------------------------------");
        System.out.println("Report for Collective Control Elevator Algorithm:\n");
        System.out.println("Overall wait time for the passengers: " + oWait + "s");
        System.out.println("Overall travel time for the passengers: " + oTravel + "s");
    }

    public static void loadPassengersUp() {
        System.out.println("What floors do the passengers want to go up to? (max number of passengers is 4)");
        Scanner sc = new Scanner(System.in);
        for (int i = 0; i < passengers.length; i++) {
            passengers[i] = Integer.parseInt(sc.next());
        }
        Arrays.sort(passengers); // Sort the array in ascending order 
        System.out.println("The desired floors of the passengers in order: " + Arrays.toString(passengers));
    }

    public static void loadPassengersDown() {
        int temp;

        System.out.println("What floors do the passengers want to go down to? (max number of passengers is 4)");
        Scanner sc = new Scanner(System.in);
        for (int i = 0; i < passengers.length; i++) {
            passengers[i] = Integer.parseInt(sc.next());
        }
        // Sort the array in descending order
        for (int a = 0; a < passengers.length; a++) {     
            for (int b = a+1; b < passengers.length; b++) {     
                if(passengers[a] < passengers[b]) {
                    temp = passengers[a];    
                    passengers[a] =passengers[b];    
                    passengers[b] = temp;    
                }     
            }     
        }
        System.out.println("The desired floors of the passengers in reverse order: " + Arrays.toString(passengers));
    }

    public static void up() {
        currF += 1;
        oTravel += floorTime;
        System.out.println("Moved up to floor " + currF);
    }

    public static void down() {
        currF -= 1;
        oTravel += floorTime;
        System.out.println("Moved down to floor " + currF);
    }

    public static void doors() {
        oWait += doorTime;
    }
}