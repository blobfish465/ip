package blob.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    protected LocalDateTime dueDateTime;

    public Deadline(String description, String byDateTime) {
        super(description);
        setBy(byDateTime);
    }

    public void setBy(String byDateTime) {
        try {
            this.dueDateTime = LocalDateTime.parse(byDateTime, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
        } catch (DateTimeParseException e) {
            System.out.println("Error parsing datetime: " + byDateTime + ". Use yyyy-MM-dd HHmm format.");
            this.dueDateTime = null;
        }
    }

    private String formatDateTime(LocalDateTime dateTime) {
        return (dateTime != null) ? dateTime.format(DateTimeFormatter.ofPattern("MMM d yyyy, h:mm a")) : "Invalid date";
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + formatDateTime(dueDateTime) + ")";
    }

    @Override
    public String toFileFormat() {
        return "D | " + (isDone ? 1 : 0) + " | " + description + " | " +
                (dueDateTime != null ? dueDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm")) : "undefined");
    }
}

