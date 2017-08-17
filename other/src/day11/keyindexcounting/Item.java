package day11.keyindexcounting;

public class Item {
    private String name;
    private int key;

    public Item(String name, int key) {
        this.name = name;
        this.key = key;
    }

    public int getKey() {
        return key;
    }

    @Override
    public String toString() {
        return String.format("%-20s%d", name, key);
    }
}
