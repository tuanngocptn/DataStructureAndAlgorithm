package day3.bookingticket;

public class Ticket implements Comparable<Ticket> {

    int number;
    String customer;

    @Override
    public int compareTo(Ticket o) {
        return new Integer(number).compareTo(o.number);
    }

    @Override
    public String toString() {
        return String.format("[%d]%s",number,customer);
    }
}
