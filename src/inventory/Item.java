
package inventory;

public class Item {

    private String name;

    public Item(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String toString() {
        return name;
    }

    public void use() {
        System.out.println("You can't use this item.");
    }
    public void open() {
        System.out.println("You cannot open this item.");
    }

    public void close() {
        System.out.println("You cannot close this item.");
    }

    public void read() {
        System.out.println("You cannot read this item.");
    }
}
