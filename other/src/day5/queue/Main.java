package day5.queue;

public class Main {
    public static void main(String[] args) throws Exception{
        MyQueue q = new MyQueue();
        System.out.println("\nEnqueue elements in queue: ");
        q.enqueue(12);
        q.enqueue(23);
        q.enqueue(11);
        q.enqueue(22);
        q.enqueue(27);
        q.enqueue(25);
        q.enqueue(29);
        System.out.println("First element is:" + q.front());
        while (!q.isEmpty()){
            System.out.print(q.dequeue()+"=>");
        }
    }
}
