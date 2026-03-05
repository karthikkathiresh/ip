package nimbus.tasks;

/**
 * Represents a basic task without any date or time constraints.
 */
public class ToDo extends Task {

    /**
     * Constructs a ToDo task with the given description.
     *
     * @param description The details of the todo task.
     */
    public ToDo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    public String toSaveFormat() {
        int doneNumber = (isDone) ? 1 : 0;
        return "T | " + doneNumber + " | " + description;
    }
}