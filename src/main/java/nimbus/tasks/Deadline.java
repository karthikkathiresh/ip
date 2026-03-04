package nimbus.tasks;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {

    protected String by;
    protected LocalDate byDate;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        try {
            this.byDate = LocalDate.parse(by);
        } catch (DateTimeParseException e) {
            this.byDate = null;
        }
    }

    public void setBy (String by) {
        this.by = by;
        try {
            this.byDate = LocalDate.parse(by);
        } catch (DateTimeParseException e) {
            this.byDate = null;
        }
    }

    public String getBy() {
        return this.by;
    }

    @Override
    public String toString() {
        String displayBy = this.by;

        if (this.byDate != null) {
            displayBy = this.byDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        }
        return "[D]" + super.toString() + " (by: " + displayBy + ")";
    }

    public String toSaveFormat() {
        int doneNumber = (isDone) ? 1 : 0;
        return "D | " + doneNumber + " | " + description + " | " + by;
    }
}