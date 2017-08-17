package day3.bookingticket;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Manager {

    FlightList list = new FlightList();

    public void create() {
        Scanner kb = new Scanner(System.in);
        Flight flight = new Flight();
        while (true) {
            System.out.println("Code? ");
            String t = kb.nextLine();
            if (t.isEmpty() || list.contains(t)) {
                System.out.println("Invalid code!");
            } else {
                flight.code = t;
                break; //exit while loop
            }
        }
        while (true) {
            System.out.println("Depart? ");
            String t = kb.nextLine();
            if (t.isEmpty()) {
                System.out.println("Empty input!");
            } else {
                flight.depart = t;
                break; //exit while loop
            }
        }
        while (true) {
            System.out.println("Arrival? ");
            String t = kb.nextLine();
            if (t.isEmpty() || t.equals(flight.depart)) {
                System.out.println("Invalid arrive!");
            } else {
                flight.arrival = t;
                break; //exit while loop
            }
        }
        DateFormat df = new SimpleDateFormat("EEE, dd MMM yyyy h:mm a");
        while (true) {

            System.out.println("Check-in(ex: Mon, 01 Aug 2016 6:45 AM)? ");
            System.out.print("Time?");
            String t = kb.nextLine();
            try {
                flight.checkin = df.parse(t);
                if (flight.checkin.compareTo(new Date()) <= 0) {
                    System.out.println("Invalid time");
                    continue;
                }
                break;
            } catch (ParseException e) {
                System.out.println("Invalid time!");
                continue;
            }

        }
        list.addToTail(flight);
        System.out.println("Create flight successfully");
    }

    public void booking() {
        Scanner kb = new Scanner(System.in);
        System.out.println("Hint: [Enter] to break");
        System.out.println("Flight list:");
        list.print();
        Flight flight;
        while (true) {
            System.out.print("Flight code? ");
            String code = kb.nextLine();
            if (code.isEmpty()) {
                return;
            }
            if (!list.contains(code)) {
                System.out.println("Invalid code!");
            } else {
                flight = list.findByCode(code);
                break;
            }
        }
        System.out.print(flight);
        flight.tickets.showSeats();
        if (flight.tickets.isFull()) {
            System.out.println("Out of tickets");
            return;
        }
        Ticket ticket = new Ticket();
        while (true) {
            System.out.print("Number? ");
            String n = kb.nextLine();
            if (n.isEmpty()) return;
            if (!n.matches("\\d+") || flight.tickets.contains(Integer.parseInt(n))) {
                System.out.println("Invalid number!");
            } else {
                ticket.number = Integer.parseInt(n);
                break;
            }

        }
        while (true) {
            System.out.print("Customer? ");
            String t = kb.nextLine();
            if (t.isEmpty()) {
                System.out.println("Blank name!");
            } else {
                ticket.customer = t;
                break;
            }
        }
        flight.tickets.addToTail(ticket);
        System.out.println("Booked successfully!");
    }

    public void cancel() {
        Scanner kb = new Scanner(System.in);
        System.out.println("Hint: [Enter] to break");
        System.out.println("Flight list:");
        list.print();
        Flight flight;
        while (true) {
            System.out.print("Flight Code? ");
            String code = kb.nextLine();
            if (code.isEmpty()) {
                System.out.println("Invalid code!");
            } else {
                flight = list.findByCode(code);
                break; //exit while loop
            }
        }
        System.out.print(flight);
        flight.tickets.showSeats();
        if (flight.tickets.isEmpty()) {
            System.out.println("Your ticket not found");
            return;
        }
        int no;
        while (true) {
            System.out.print("Number? ");
            String n = kb.nextLine();
            if (n.isEmpty()) return;
            if (!n.matches("\\d+") || !flight.tickets.contains(Integer.parseInt(n))) {
                System.out.println("Invalid number");
            } else {
                no = Integer.parseInt(n);
                break;
            }
        }
        Ticket ticket = flight.tickets.findByNo(no);
        System.out.println(ticket);
        if (flight.checkin.compareTo(new Date()) <= 0) {
            System.out.println("Can't cancel");
            return;
        }
        while (true) {
            System.out.print("Are you sure (y/n)? ");
            String yn = kb.nextLine();
            if (!yn.matches("[YyNn]"))
                System.out.println("Wrong option!");
            else {
                if (yn.matches("[Yy]"))
                    flight.tickets.delete(ticket);
                System.out.println("Cancel ticket successfully");
                break;
            }
        }
    }

    public void search() {
        Scanner kb = new Scanner(System.in);
        String keyword;
        while (true) {
            System.out.print("Keyword? ");
            keyword = kb.nextLine();
            if (keyword.isEmpty()) {
                System.out.println("Blank keyword");
            } else {
                break;
            }
            SLL<String> results = list.findByKeyWord(keyword);
            if (results.isEmpty())
                System.out.println("Not found!");
            else results.print();
        }
    }
}

