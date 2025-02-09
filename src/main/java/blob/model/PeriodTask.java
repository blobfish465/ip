package blob.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a task that needs to be completed within a specified period.
 * This class extends the generic Task class by adding support for a period defined by start and end dates.
 */
public class PeriodTask extends Task {
    private static final String DATE_FORMAT = "yyyy-MM-dd";
    private LocalDate startDate;
    private LocalDate endDate;

    /**
     * Constructs a PeriodTask object with a description and specific start and end dates.
     *
     * @param description The description of the task.
     * @param start The start date of the task period in "yyyy-MM-dd" format.
     * @param end The end date of the task period in "yyyy-MM-dd" format.
     */
    public PeriodTask(String description, String start, String end) {
        super(description);
        this.startDate = parseDate(start);
        this.endDate = parseDate(end);
        assert (startDate != null && endDate != null)
                || (startDate == null && endDate == null)
                : "Both startDate and endDate should be set or null";
    }

    /**
     * Parses the given date string into a LocalDate object.
     * This method takes a string representation of a date, which should conform to the
     * format "yyyy-MM-dd". It attempts to parse the string into a LocalDate object.
     * If the parsing fails due to a formatting error, the method returns {@code null}.
     *
     * @param date The date string to parse, expected to be in the format "yyyy-MM-dd".
     * @return A LocalDate object representing the specified date, or {@code null}
     *         if the string cannot be parsed due to formatting issues.
     */
    private LocalDate parseDate(String date) {
        try {
            return LocalDate.parse(date, DateTimeFormatter.ofPattern(DATE_FORMAT));
        } catch (DateTimeParseException e) {
            return null;
        }
    }

    /**
     * Formats a LocalDate object into a more readable string.
     * If date is null, "Invalid date" is returned.
     *
     * @param date The LocalDate object to format.
     * @return A formatted string representing the date, or "Invalid date" if date is null.
     */
    private String formatDate(LocalDate date) {
        return (date != null)
                ? date.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                : "Invalid date";
    }

    /**
     * Provides a string representation of this period task including its description and formatted start and end dates.
     *
     * @return A string describing the task, including its status, description, and period start/end dates.
     */
    @Override
    public String toString() {
        return "[P]" + super.toString() + " (between " + formatDate(startDate)
                + " and " + formatDate(endDate) + ")";
    }

    /**
     * Returns a string formatted for file storage, including the task type, status, description,
     * and formatted start and end dates.
     *
     * @return A string formatted for saving to a file.
     */
    @Override
    public String toFileFormat() {
        return "P | " + (isDone ? 1 : 0) + " | " + description + " | "
                + startDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
                + " | "
                + endDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }
}

