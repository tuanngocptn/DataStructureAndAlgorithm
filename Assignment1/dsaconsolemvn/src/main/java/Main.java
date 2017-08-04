import collection.LinkedLstQueue;

public class Main {
    public static void main(String[] args) {
        LinkedLstQueue<String> linkStr = new LinkedLstQueue<String>();
        linkStr.add("1");
        linkStr.add("2");
        linkStr.add("3");
        linkStr.add("4");
        linkStr.add("5");
        linkStr.add("6");
        linkStr.add("7");
        linkStr.add("8");
        linkStr.add("9");
        System.out.printf(linkStr.toString());
    }
}
