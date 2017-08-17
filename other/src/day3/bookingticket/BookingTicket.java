package day3.bookingticket;

public class BookingTicket {
    public static void main(String[] args) {
        SLL<Integer> list = new SLL<Integer>();
        System.out.println("\nSLL original: ");
        list.addToHead(1);
        list.addToHead(2);
        list.addToHead(3);
        list.addToHead(4);
        list.addToHead(5);
        list.addToHead(6);
        System.out.println("\nSLL is: ");
        list.print();
        System.out.println("\nAdd to tail: ");
        list.print();
        System.out.println("\n Total of nodes SLL is: "+ list.countNode());
        list.AddAsc(5);
        list.print();
        System.out.println("");
//        list.printout();
        System.out.println("Delete From head: " + list.deleteFromHead());
        System.out.println("");
        list.print();
        System.out.println();
        list.delete(4);
        list.print();
        System.out.println("");
        System.out.println(list.deleteAt(3));
        list.print();
    }
}
