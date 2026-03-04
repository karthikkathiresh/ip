package nimbus.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {

    protected String from;
    protected String to;
    protected LocalDate fromDate;
    protected LocalDate toDate;

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

    public String toSaveFormat() {
        int doneNumber = (isDone) ? 1 : 0;
        return "E | " + doneNumber + " | " + description + " | " + from + "-" + to;
    }
}