import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        Theater theater = new Theater("Olympian", 8, 12);
        List<Theater.Seat> seatCopy = new ArrayList<>(theater.seats);
        printList(seatCopy);

        seatCopy.get(1).reserve();
        if(theater.reserveSeat("A02")) {
            System.out.println("Please pay for A02");
        } else {
            System.out.println("Seat already taken.");
        }

        Collections.shuffle(seatCopy);
        System.out.println("Printing shuffle seatCopy");
        printList(seatCopy);
        System.out.println("Printing theater.seat");
        printList(theater.seats);

        Theater.Seat minSeat = Collections.min(seatCopy);
        Theater.Seat maxSeat = Collections.max(seatCopy);
        System.out.println("Min seat number is " + minSeat.getSeatNumber());
        System.out.println("Max seat number is " + maxSeat.getSeatNumber());

        sortList(seatCopy);
        System.out.println("Printing sorted seatCopy");
        printList(seatCopy);
    }

   public static void printList(List<Theater.Seat> list) {
        for(Theater.Seat seat : list) {
            System.out.print(" " + seat.getSeatNumber());
        }
       System.out.println();
       System.out.println("===============================================================================");
   }


   public static void sortList(List<? extends Theater.Seat> list) {
        for (int i = 0; i < list.size() -1; i++) {
            for (int j = i + 1; j < list.size(); j++) {
                if(list.get(i).compareTo(list.get(j)) > 0) {
                    Collections.swap(list, i, j);

                }
            }
        }
   }
}

//        System.out.println(theater.getTheaterName());
//        //System.out.println(theater.getSeat());
//        if(theater.reserveSeat("B13")) {
//            System.out.println("Please Pay");
//        } else {
//            System.out.println("Sorry, seat is taken");
//        }


