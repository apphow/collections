import java.util.ArrayList;
import java.util.*;

public class Theater {

    private final String theaterName;
    // using collections lets us use any of the Collections classes
    private List<Seat> seats = new ArrayList<>();

    public Theater(String theaterName, int numRows, int seatsPerRow) {
        this.theaterName = theaterName;

        int lastRow = 'A' + (numRows -1);
        // going through all rows A-Z
        for(char row = 'A'; row <= lastRow; row++){
            // for each row we need to go through and allocate
            // the seats for that section
            for(int seatNum = 1; seatNum <= seatsPerRow; seatNum++) {
                Seat seat = new Seat(row + String.format("%02d", seatNum));
                seats.add(seat);
            }
        }
    }

    public String getTheaterName() {
        return theaterName;
    }

    public boolean reserveSeat(String seatNumber) {

            int low = 0;
            int high = seats.size()-1;
            while(low <= high) {
                System.out.print(".");
                int mid = (low + high / 2);
                Seat midVal = seats.get(mid);
                int cmp = midVal.getSeatNumber().compareTo(seatNumber);
                if(cmp < 0) {
                    low = mid + 1;
                } else if (cmp > 0) {
                    high = mid =1;
                } else {
                    return seats.get(mid).reserve();
                }
            }
            System.out.println("There is no seat" + seatNumber);
            return false;
        }

    // for testing
    public boolean getSeat() {
        for(Seat seat : seats) {
            System.out.println(seat.getSeatNumber());
        }
        return false;
    }
    // using comparable makes it a lot more efficient
    private class Seat implements Comparable<Seat>{
        private final String seatNumber;
        private boolean reserved = false;

    public Seat(String seatNumber) {
        this.seatNumber = seatNumber;
    }
    // adding compare to, we can start implementing this
        // binary search, making it more efficient
        @Override
        public int compareTo(Seat seat) {
            return this.seatNumber.compareToIgnoreCase(seat.getSeatNumber());
        }

    public boolean reserve() {
        // if seat is already reserved,we don't want to
        // process it
        if(!this.reserved) {
            this.reserved = true;
            System.out.println("Seat " + seatNumber + " reserved");
            return true;
        } else {
            return false;
        }
    }

    public boolean cancel() {
        if(this.reserved) {
            this.reserved = false;
            System.out.println("Reservation of seat " + seatNumber + " canceled.");
            return true;
        } else {
            return false;
        }
    }

        public String getSeatNumber() {
            return seatNumber;
        }
    }
}
