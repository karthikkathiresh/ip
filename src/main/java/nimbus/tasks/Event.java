package nimbus.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a task that occurs within a specific time frame.
 */
public class Event extends Task {

    protected String from;
    protected String to;
    protected LocalDate fromDate;
    protected LocalDate toDate;

    /**
     * Constructs an Event task.
     * Attempts to parse the provided start and end times into LocalDate objects for formatting.
     *
     * @param description The details of the event.
     * @param from The string representation of the start time.
     * @param to The string representation of the end time.
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;

        try {
            this.fromDate = LocalDate.parse(from);
        } catch (DateTimeParseException e) {
            this.fromDate = null;
        }

        try {
            this.toDate = LocalDate.parse(to);
        } catch (DateTimeParseException e) {
            this.toDate = null;
        }
    }

    public void setFrom(String from) {
        this.from = from;
        try {
            this.fromDate = LocalDate.parse(from);
        } catch (DateTimeParseException e) {
            this.fromDate = null;
        }
    }

    public void setTo(String to) {
        this.to = to;
        try {
            this.toDate = LocalDate.parse(to);
        } catch (DateTimeParseException e) {
            this.toDate = null;
        }
    }

    public String getFrom() {
        return this.from;
    }

    public String getTo() {
        return this.to;
    }

    @Override
    public String toString() {
        String displayFrom = this.from;
        String displayTo = this.to;
        DateTimeFormatter format = DateTimeFormatter.ofPattern("MMM d yyyy");

        if (this.fromDate != null) {
            displayFrom = this.fromDate.format(format);
        }

        if (this.toDate != null) {
            displayTo = this.toDate.format(format);
        }
        return "[E]" + super.toString() + " (from: " + displayFrom + " to: " + displayTo + ")";
    }

    @Override
    public String toSaveFormat() {
        int doneNumber = (isDone) ? 1 : 0;
        return "E | " + doneNumber + " | " + description + " | " + from + "-" + to;
    }
}