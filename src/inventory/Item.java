
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
}
