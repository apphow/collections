public class Main {

    public static void main(String[] args) {

        Theater theater = new Theater("Olympian", 8, 12);
        System.out.println(theater.getTheaterName());
        //System.out.println(theater.getSeat());
        if(theater.reserveSeat("B13")) {
            System.out.println("Please Pay");
        } else {
            System.out.println("Sorry, seat is taken");
        }
    }
}
