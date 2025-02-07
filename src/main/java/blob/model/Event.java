package blob.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a task that occurs over a specified time period, with both start and end times.
 * This class extends the generic Task class by adding support for start and end times, making it suitable for events.
 */
public class Event extends Task {

    private static final String DATE_TIME_FORMAT = "yyyy-MM-dd HHmm";
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
        this.startDateTime = parseDateTime(start);
        this.endDateTime = parseDateTime(end);
        assert (startDateTime != null && endDateTime != null)
                || (startDateTime == null && endDateTime == null)
                : "Both startDateTime and endDateTime should be set or null";
    }

    /**
     * Parses the given date and time string into a LocalDateTime object.
     *
     * This method takes a string representation of a date and time, which should conform to the
     * format "yyyy-MM-dd HHmm". It attempts to parse the string into a LocalDateTime object.
     * If the parsing fails due to a formatting error, the method returns {@code null}.
     *
     * @param dateTime The date and time string to parse, expected to be in the format "yyyy-MM-dd HHmm".
     * @return A LocalDateTime object representing the specified date and time, or {@code null}
     *         if the string cannot be parsed due to formatting issues.
     * @throws DateTimeParseException if the dateTime string does not conform to the expected format,
     *         this exception is caught within the method and results in a {@code null} return.
     */
    private LocalDateTime parseDateTime(String dateTime) {
        try {
            return LocalDateTime.parse(dateTime, DateTimeFormatter.ofPattern(DATE_TIME_FORMAT));
        } catch (DateTimeParseException e) {
            return null;
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
