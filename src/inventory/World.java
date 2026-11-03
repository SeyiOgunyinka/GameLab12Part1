
package inventory;

import java.util.HashMap;

public class World {

    public static Room buildWorld(
            HashMap<String, String> descriptions) {

        Room livingRoom = new Room(
                "Living Room",
                descriptions.getOrDefault(
                        "Living Room", "No description available."));

        Room bedroom = new Room(
                "Bedroom",
                descriptions.getOrDefault(
                        "Bedroom", "No description available."));

        Room attic = new Room(
                "Attic",
                descriptions.getOrDefault(
                        "Attic", "No description available."));

        Room garage = new Room(
                "Garage",
                descriptions.getOrDefault(
                        "Garage", "No description available."));

        Room kitchen = new Room(
                "Kitchen",
                descriptions.getOrDefault(
                        "Kitchen", "No description available."));

        Room office = new Room(
                "Office",
                descriptions.getOrDefault(
                        "Office", "No description available."));

        office.setLocked(true);
        attic.setLocked(true);

        livingRoom.addExit(kitchen, 0);
        livingRoom.addExit(office, 1);
        livingRoom.addExit(bedroom, 2);

        office.addExit(livingRoom, 0);

        bedroom.addExit(livingRoom, 3);
        bedroom.addExit(attic, 4);

        attic.addExit(bedroom, 5);

        kitchen.addExit(livingRoom, 1);
        kitchen.addExit(garage, 3);

        garage.addExit(kitchen, 2);

        livingRoom.addItem(new Item("Remote"));
        livingRoom.addItem(new Item("Couch"));
        livingRoom.addItem(new Item("Music Box"));

        bedroom.addItem(new Item("Key"));
        bedroom.addItem(new Item("Bed"));
        bedroom.addItem(new Tool());

        attic.addItem(new Item("Dusty Book"));

        kitchen.addItem(new Item("Peaches"));
        kitchen.addItem(new Item("Flashlight"));
        kitchen.addItem(new Item("Knife"));
        kitchen.addItem(new Item("Mysterious Object"));

        return livingRoom;
    }
}
