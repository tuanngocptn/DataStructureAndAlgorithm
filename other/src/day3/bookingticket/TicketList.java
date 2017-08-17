package day3.bookingticket;

public class TicketList extends SLL<Ticket> {
    public Ticket findByNo(int number) {
        if (isEmpty()) return null;
        Ticket dummy = new Ticket();
        SLLNode<Ticket> t = find(dummy);
        return t != null ? t.info : null;
    }
    public boolean isFull() {
        for (int i = 0; i < 20; i++)
            if(!contains(i)) return false;
        return true;

    }

    public boolean contains(int number) {
        return findByNo(number) != null;
    }

    public void showSeats() {
        for (int i = 0; i < 20; i++) {
            System.out.printf("%-3d", i);
        }
        System.out.println("");
        for (int i = 0; i < 20; i++) {
            System.out.printf("[%c]",contains(i)?'x':' ');
        }
        System.out.println("");
    }
}
