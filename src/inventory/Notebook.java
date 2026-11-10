
package inventory;

public class Notebook extends Item {

    private boolean passwordAdded = false;

    public Notebook() {
        super("Notebook");
    }

    @Override
    public void read() {

        System.out.println("You read the notebook.");
        System.out.println("The notebook contains a password!");

        if (!passwordAdded) {

            Game.addInventoryItem(new Item("Password"));

            passwordAdded = true;

        } else {
            System.out.println("You already have the password.");
        }
    }
}
