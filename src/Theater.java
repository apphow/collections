import java.util.ArrayList;
import java.util.*;

public class Theater {

    private final String theaterName;
    // using collections lets us use any of the Collections classes
    public static List<Seat> seats = new ArrayList<>();

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

    public static boolean reserveSeat(String seatNumber) {
        // reason binary search is fastest way to find an
        // item in a sorted list. It works by checking the element
        // in the midpoint of the list. So the method plays
        // higher or lower with the list. So if middle item
        // is higher than the item we're looking for, it knows
        // the one we want must be in the first part of the list.
        // So the list is reduced to half each time.
        // will take no more than 10 checks to find an item, or
        // decide its not present in a list of 1024
        // over a million items, 1,048,576 can be checked in
        // no more than 20 comparisons
        // and 64 comparisons are all that's required to search
        // a list with a huge number.
        //
        // comparing seats

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
//        Seat requestedSeat = new Seat(seatNumber);
//        int foundSeat = Collections.binarySearch(seats, requestedSeat, null);
//        if (foundSeat >= 0) {
//            return seats.get(foundSeat).reserve();
//        } else {
//            System.out.println("There is no seat " + seatNumber);
//            return false;
//        }
//        }
//        for (Seat seat : seats) {
//            System.out.print(".");
//            if (seat.getSeatNumber().equals(seatNumber)) {
//                requestedSeat = seat;
//                break;
//            }
//        }
//
//        if(requestedSeat == null) {
//            System.out.println("There is no seat " + seatNumber);
//            return false;
//        }
//
//        return requestedSeat.reserve();

    // for testing
    public static boolean getSeat() {
        for(Seat seat : seats) {
            System.out.println(seat.getSeatNumber());
        }
        return false;
    }
    // using comparable makes it a lot more efficient
    class Seat implements Comparable<Seat>{
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
