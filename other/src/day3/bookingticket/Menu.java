package day3.bookingticket;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Menu {
    List<String> list = new ArrayList<>();

    public Menu(List<String> list) {
        this.list = list;
    }

    public Menu() {

    }

    public Menu add(String... items) {
        list.addAll(Arrays.asList(items));
        return this;
    }

    public int show() {
        System.out.println("----------------Menu--------------");
        for (int i = 0; i < list.size(); i++) {
            System.out.printf("%d. #s%n", i + 1, list.get(i));

        }
        System.out.println("---------------");
        Scanner kb = new Scanner(System.in);
        String t;
        while (true) {
            System.out.println("Your choice: ");
            t = kb.nextLine();
            if (!t.matches("\\d{1}") | (Integer.parseInt(t) < 1 || Integer.parseInt(t) > list.size()))
                System.out.println("Wrong choice ! try again");
            else
                return Integer.parseInt(t);
        }
    }

}
