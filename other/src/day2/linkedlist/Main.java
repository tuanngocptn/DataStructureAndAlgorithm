package day2.linkedlist;

public class Main {
    public static void main(String[] args) {
        MyList list = new MyList();
        list.insert(3);
        list.insert(4);
        list.insert(6);
        list.insert(7);
        list.insert(8);
        list.insert(2);
        list.print();
        System.out.println();
        System.out.println(list.coundNode());
        System.out.println(list.sumNode());
        list.insertFirst(1);
        list.print();
        System.out.println();
        list.insertAfter(12, 7);
        list.print();
        System.out.println();
        list.deleteTailNode();
        list.print();
        System.out.println();
        list.deleteFirst();
        list.print();
        System.out.println();
        list.delete(7);
        list.print();
        System.out.println();
        list.deletePosition(3);
        list.print();
    }
}
