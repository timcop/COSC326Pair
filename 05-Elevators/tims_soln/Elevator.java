public class Elevator {
    public ArrayList<Customer> customers = new ArrayList();
    public final static int floor_count = 10;
    public final static int floor_time = 5;
    public final static int door_time = 10;

    public void setCustomers(int N, int final_time) {
        for (i = 0; i < N; i++){
            rand_time = rand.nextInt(final_time);
            rand_floor_start = rand.nextInt(floor_count);
            rand_floor_finish = rand.nextInt(floor_count);

            while rand_floor_finish == rand_floor_start {
                rand_floor_finish = rand.nextInt(floor_count);
            }
            Customer c = new Customer(rand_time, rand_floor_start, rand_floor_finish);
            this.customers.add(c)
        }
    }
    public Elevator(int N, int final_time) {
        setCustomers(N, final_time);
    }
}
