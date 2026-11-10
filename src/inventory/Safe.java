
package inventory;

public class Safe extends Item {

    private Room room;
    private boolean opened = false;
    private boolean notebookCreated = false;

    public Safe(Room room) {
        super("Safe");
        this.room = room;
    }

    @Override
    public void open() {

        if (!opened) {

            opened = true;
            System.out.println("You opened the safe.");

            if (!notebookCreated) {

                Notebook notebook = new Notebook();

                room.addItem(notebook);

                notebookCreated = true;

                System.out.println("You discovered a notebook inside the safe!");

            }

        } else {
            System.out.println("The safe is already open.");
        }
    }

    @Override
    public void close() {

        if (opened) {

            opened = false;
            System.out.println("You closed the safe.");

        } else {
            System.out.println("The safe is already closed.");
        }
    }
}
