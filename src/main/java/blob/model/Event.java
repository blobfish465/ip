package blob.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a task that occurs over a specified time period, with both start and end times.
 * This class extends the generic Task class by adding support for start and end times, making it suitable for events.
 */
public class Event extends Task {
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;

    /**
     * Constructs an Event object with a description and specific start and end times.
     *
     * @param description The description of the event.
     * @param start The start time of the event in "yyyy-MM-dd HHmm" format.
     * @param end The end time of the event in "yyyy-MM-dd HHmm" format.
     */
    public Event(String description, String start, String end) {
        super(description);
        setTimes(start, end);
    }

    /**
     * Sets the start and end times for the event by parsing the input strings.
     * If the parsing fails for either time, both startDateTime and endDateTime are set to null,
     * and an error message is printed.
     *
     * @param start The start time string to parse.
     * @param end The end time string to parse.
     */
    private void setTimes(String start, String end) {
        try {
            this.startDateTime = LocalDateTime.parse(start,
                    DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
            this.endDateTime = LocalDateTime.parse(end,
                    DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
        } catch (DateTimeParseException e) {
            System.out.println("Error parsing start or end time: " + e.getMessage());
            this.startDateTime = null;
            this.endDateTime = null;
        }
    }

    /**
     * Formats a LocalDateTime object into a more readable string.
     * If dateTime is null, "Invalid time" is returned.
     *
     * @param dateTime The LocalDateTime object to format.
     * @return A formatted string representing the date and time, or "Invalid time" if dateTime is null.
     */
    private String formatDateTime(LocalDateTime dateTime) {
        return (dateTime != null)
                ? dateTime.format(DateTimeFormatter.ofPattern("MMM d yyyy, h:mm a"))
                : "Invalid time";
    }

    /**
     * Provides a string representation of this event including its description and formatted start and end times.
     *
     * @return A string describing the event, including its status, description, and start/end times.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: "
                + formatDateTime(startDateTime)
                + " to: "
                + formatDateTime(endDateTime) + ")";
    }

    /**
     * Returns a string formatted for file storage, including the event type, status, description,
     * and formatted start and end times.
     *
     * @return A string formatted for saving to a file.
     */
    @Override
    public String toFileFormat() {
        return "E | " + (isDone ? 1 : 0) + " | " + description + " | "
                + (startDateTime != null
                    ? startDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"))
                    : "undefined")
                + " | "
                + (endDateTime != null
                    ? endDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"))
                    : "undefined");
    }
}
