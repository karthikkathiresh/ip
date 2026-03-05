package nimbus.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a task that needs to be completed by a specific deadline.
 */
public class Deadline extends Task {

    protected String by;
    protected LocalDate byDate;

    /**
     * Constructs a Deadline task.
     * Attempts to parse the provided deadline string into a LocalDate object for formatting.
     *
     * @param description The details of the task.
     * @param by The string representation of the deadline (e.g., "2026-08-09")
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        try {
            this.byDate = LocalDate.parse(by);
        } catch (DateTimeParseException e) {
            this.byDate = null;
        }
    }

    @Override
    public String toString() {
        String displayBy = this.by;

        if (this.byDate != null) {
            displayBy = this.byDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        }
        return "[D]" + super.toString() + " (by: " + displayBy + ")";
    }

    @Override
    public String toSaveFormat() {
        int doneNumber = (isDone) ? 1 : 0;
        return "D | " + doneNumber + " | " + description + " | " + by;
    }
}