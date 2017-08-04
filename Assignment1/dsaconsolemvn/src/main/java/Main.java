import collection.LinkedLstDequeue;
import collection.LinkedLstQueue;
import entities.Order;

public class Main {
    public static void main(String[] args) {
        LinkedLstDequeue<Order> linkStr = new LinkedLstDequeue<Order>();
        Order order = new Order();
        order.setCcode("Ccode");
        order.setPcode("Pcode");
        order.setQuantity(100);

        linkStr.insertFirst(order);
        linkStr.insertFirst(order);
        linkStr.insertFirst(order);
        linkStr.insertLast(order);
        linkStr.insertLast(order);
        linkStr.insertLast(order);
        System.out.println(linkStr.length());
        linkStr.delete(0);
        System.out.println(linkStr.length());
        linkStr.delete(0);
        System.out.println(linkStr.length());
        linkStr.delete(0);
        System.out.println(linkStr.length());
        linkStr.delete(0);
        System.out.println(linkStr.length());
        linkStr.delete(0);
        System.out.println(linkStr.length());
        linkStr.delete(0);
        System.out.println(linkStr.length());
        linkStr.insertAt(1,order);
        System.out.println(linkStr.length());
        linkStr.insertAt(1,order);
        System.out.println(linkStr.length());
        linkStr.insertAt(1,order);
        System.out.println(linkStr.length());
        linkStr.insertAt(1,order);
        System.out.println(linkStr.length());
        System.out.println(linkStr.displayForward());

        linkStr.insertAt(1,order);
    }
}
