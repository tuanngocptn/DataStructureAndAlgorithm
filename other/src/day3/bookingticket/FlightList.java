package day3.bookingticket;

public class FlightList extends SLL<Flight> {
    public Flight findByCode(String code) {
        if (isEmpty()) return null;
        Flight dummy = new Flight();
        dummy.code = code;
        SLLNode<Flight> t = find(dummy);
        return t != null ? t.info : null;
    }

    public boolean contains(String code) {
        return findByCode(code) != null;
    }

    public SLL<String> findByKeyWord(String keyword) {
        SLL<String> results = new SLL<String>();
        keyword = keyword.toLowerCase();
        for (SLLNode<Flight> p = head; p != null; p = p.next) {
            TicketList list = p.info.tickets;
            for(SLLNode<Ticket> q = list.head;q!=null;q=q.next){
                if(q.info.customer.toLowerCase().contains(keyword)){
                    String s = String.format("[%s]%s-%s",p.info,p.info.depart,p.info.arrival);
                    results.addToTail(s);
                }
            }
        }
        return results;
    }
}
