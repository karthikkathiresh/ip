package nimbus.tasks;

public class Deadline extends Task {

    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    public void setBy (String by) {
        this.by = by;
    }

    public String getBy() {
        return this.by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.by + ")";
    }

    public String toSaveFormat() {
        int doneNumber = (isDone) ? 1 : 0;
        return "D | " + doneNumber + " | " + description + " | " + by;
    }
}