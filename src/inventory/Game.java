
package inventory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Game {

    private static Room currentRoom;
    private static ArrayList<Item> inventory = new ArrayList<>();

    public static void print(Object obj) {
        System.out.println(obj);
    }

    public static Room getCurrentRoom() {
        return currentRoom;
    }
    public static void addInventoryItem(Item item) {
        if (item != null && !inventory.contains(item)) {
            inventory.add(item);
            print(item.getName() + " was added to your inventory.");
        }
    }

    public static void openItem(String itemName) {

        Item item = currentRoom.getItem(itemName);

        if (item == null) {
            for (Item i : inventory) {
                if (i.getName().equalsIgnoreCase(itemName)) {
                    item = i;
                    break;
                }
            }
        }

        if (item == null) {
            print("You cannot find that item.");
        } else {
            item.open();
        }
    }

    public static void closeItem(String itemName) {

        Item item = currentRoom.getItem(itemName);

        if (item == null) {
            for (Item i : inventory) {
                if (i.getName().equalsIgnoreCase(itemName)) {
                    item = i;
                    break;
                }
            }
        }

        if (item == null) {
            print("You cannot find that item.");
        } else {
            item.close();
        }
    }

    public static void readItem(String itemName) {

        Item item = null;

        for (Item i : inventory) {
            if (i.getName().equalsIgnoreCase(itemName)) {
                item = i;
                break;
            }
        }

        if (item == null) {
            print("You must have that item in your inventory to read it.");
        } else {
            item.read();
        }
    }

    public static void move(String direction) {

        int exitNumber;

        switch (direction.toLowerCase()) {
            case "east":
                exitNumber = 0;
                break;
            case "west":
                exitNumber = 1;
                break;
            case "north":
                exitNumber = 2;
                break;
            case "south":
                exitNumber = 3;
                break;
            case "up":
                exitNumber = 4;
                break;
            case "down":
                exitNumber = 5;
                break;
            default:
                print("Invalid direction.");
                return;
        }

        Room nextRoom = currentRoom.getExit(exitNumber);

        if (nextRoom == null) {
            print("You cannot go that way.");
        } else if (nextRoom.isLocked()) {
            print("This room is locked. You need a key!");
        } else {
            currentRoom = nextRoom;
            print(currentRoom);
        }
    }

    public static void takeItem(String itemName) {

        Item item = currentRoom.getItem(itemName);

        if (item == null) {
            print("That item is not in this room.");
        } else if (item instanceof Safe
                || item.getName().equalsIgnoreCase("couch")
                || item.getName().equalsIgnoreCase("bed")) {
            print("You cannot pick up the " + item.getName() + ".");
        } else if (inventory.contains(item)) {
            print("You already have that item.");
        } else {
            currentRoom.removeItem(itemName);
            inventory.add(item);
            print("You picked up the " + item.getName() + ".");
        }
    }

    public static void takeItem() {
        Item item = currentRoom.getItem();

        if (item == null) {
            print("There is no item in this room.");
        } else if (item instanceof Safe
                || item.getName().equalsIgnoreCase("couch")
                || item.getName().equalsIgnoreCase("bed")) {
            print("You cannot pick up the " + item.getName() + ".");
        } else if (inventory.contains(item)) {
            print("You already have that item.");
        } else {
            currentRoom.removeItem();
            inventory.add(item);
            print("You picked up the " + item.getName() + ".");
        }
    }

    public static void showInventory() {

        if (inventory.isEmpty()) {
            print("Your inventory is empty.");
        } else {
            print("Your inventory:");

            for (Item item : inventory) {
                print("- " + item.getName());
            }
        }
    }

    public static void useItem(String itemName) {

        Item item = null;

        for (Item i : inventory) {
            if (i.getName().equalsIgnoreCase(itemName)) {
                item = i;
            }
        }

        if (item == null) {
            print("You don't have that item.");
        } else if (item.getName().equalsIgnoreCase("Key")) {

            if (currentRoom.getName().equalsIgnoreCase("Living Room")) {

                Room office = currentRoom.getExit(1);

                if (office.isLocked()) {
                    office.setLocked(false);
                    print("You unlocked the Office!");
                } else {
                    print("The Office is already unlocked.");
                }

            } else {
                print("You cannot use the key here.");
            }

        } else {
            item.use();
        }
    }

    public static void runGame() {

        Scanner input = new Scanner(System.in);

        HashMap<String, String> descriptions =
                RoomDescription.loadDescriptions("roomdesc.txt");

        currentRoom = World.buildWorld(descriptions);
        inventory.clear();

        print(currentRoom);

        String command = "";

        while (!command.equals("exit")) {

            print("Enter a command:");
            command = input.nextLine().trim().toLowerCase();

            switch (command) {

                case "east":
                case "west":
                case "north":
                case "south":
                case "up":
                case "down":
                    move(command);
                    break;

                case "take":
                    takeItem();
                    break;

                case "i":
                    showInventory();
                    break;

                case "use":
                    print("What item would you like to use?");
                    String usedItem = input.nextLine().trim();
                    useItem(usedItem);
                    break;

                case "open":
                    print("What item would you like to open?");
                    String openedItem = input.nextLine().trim();
                    openItem(openedItem);
                    break;

                case "close":
                    print("What item would you like to close?");
                    String closedItem = input.nextLine().trim();
                    closeItem(closedItem);
                    break;

                case "read":
                    print("What item would you like to read?");
                    String readItem = input.nextLine().trim();
                    readItem(readItem);
                    break;

                case "exit":
                    print("Thanks for playing!");
                    break;

                default:
                    if (command.startsWith("take ")) {
                        String itemName = command.substring(5).trim();
                        takeItem(itemName);
                    } else if (command.startsWith("use ")) {
                        String itemName = command.substring(4).trim();
                        useItem(itemName);

                    } else if (command.startsWith("open ")) {
                        String itemName = command.substring(5).trim();
                        openItem(itemName);

                    } else if (command.startsWith("close ")) {
                        String itemName = command.substring(6).trim();
                        closeItem(itemName);

                    } else if (command.startsWith("read ")) {
                        String itemName = command.substring(5).trim();
                        readItem(itemName);

                    } else {
                        print("Invalid command.");
                    }
                    break;
            }
        }

        input.close();
    }

    public static void main(String[] args) {
        runGame();
    }
}
