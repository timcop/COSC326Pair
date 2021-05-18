public class Customer() {

    private int start_time;
    private int floor_start;
    private int floor_finish;
    private int finish_time;

    public Customer(int time, int start, int finish) {
        this.start_time = time;
        this.floor_start = start;
        this.floor_finish = finish;
    }

    public int getStart_time() {
        return start_time;
    }

    public int getFinish_time() {
        return finish_time;
    }

    public int getFloor_start() {
        return floor_start;
    }

    public int getFloor_finish() {
        return floor_finish;
    }
}
