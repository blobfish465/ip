package blob.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a task with a specific deadline.
 * This class extends the generic Task class by adding support for deadlines.
 */
public class Deadline extends Task {
    protected LocalDateTime dueDateTime;

    /**
     * Constructs a Deadline object with a description and a deadline time.
     *
     * @param description The description of the deadline task.
     * @param byDateTime The deadline time in "yyyy-MM-dd HHmm" format.
     */
    public Deadline(String description, String byDateTime) {
        super(description);
        setBy(byDateTime);
    }

    /**
     * Sets the deadline date and time by parsing the input string.
     * If the parsing fails, the dueDateTime is set to null and an error message is printed.
     *
     * @param byDateTime The string representing the deadline date and time in "yyyy-MM-dd HHmm" format.
     */
    public void setBy(String byDateTime) {
        try {
            this.dueDateTime = LocalDateTime.parse(byDateTime,
                    DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
        } catch (DateTimeParseException e) {
            System.out.println("Error parsing datetime: " + byDateTime
                    + ". Use yyyy-MM-dd HHmm format.");
            this.dueDateTime = null;
        }
    }

    /**
     * Formats the dueDateTime to a readable string.
     * If the dueDateTime is null, it returns "Invalid date".
     *
     * @param dateTime The LocalDateTime object to format.
     * @return A formatted date string or "Invalid date" if dateTime is null.
     */
    private String formatDateTime(LocalDateTime dateTime) {
        return (dateTime != null)
                ? dateTime.format(DateTimeFormatter.ofPattern("MMM d yyyy, h:mm a"))
                : "Invalid date";
    }

    /**
     * Returns a string representation of the Deadline task including its status, description, and deadline.
     *
     * @return Formatted string indicating the deadline's status, description, and due date.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + formatDateTime(dueDateTime) + ")";
    }

    /**
     * Returns a formatted string suitable for file storage.
     * Includes the type of task, its completion status, description, and formatted due date.
     *
     * @return A string formatted for file storage.
     */
    @Override
    public String toFileFormat() {
        return "D | " + (isDone ? 1 : 0) + " | " + description + " | "
                + (dueDateTime != null
                    ? dueDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"))
                    : "undefined");
    }
}

