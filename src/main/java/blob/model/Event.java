package blob.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;

    public Event(String description, String start, String end) {
        super(description);
        setTimes(start, end);
    }

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

    private String formatDateTime(LocalDateTime dateTime) {
        return (dateTime != null)
                ? dateTime.format(DateTimeFormatter.ofPattern("MMM d yyyy, h:mm a"))
                : "Invalid time";
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: "
                + formatDateTime(startDateTime)
                + " to: "
                + formatDateTime(endDateTime) + ")";
    }

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
