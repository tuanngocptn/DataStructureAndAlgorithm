package day3.bookingticket;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Flight implements Comparable<Flight>{

    String code;
    String depart;
    String arrival;
    Date checkin;
    TicketList tickets = new TicketList();

    @Override
    public int compareTo(Flight o) {
        return code.compareTo(o.code);
    }



    @Override
    public String toString() {
        String s = String.format("[%s]%s-%s",code,depart,arrival);
        DateFormat df = new SimpleDateFormat("EEE,dd MMM yyyy h:mm a");
        s += String.format("Check in: %s%n",df.format(checkin));
        return s;
    }
}


