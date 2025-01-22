package blob.model;

/**
 * Represents a "to-do" task, a basic type of task without any associated time constraints.
 * This class provides specific string representations for displaying and storing to-do tasks.
 */
public class ToDo extends Task {
    /**
     * Constructs a new ToDo object with the given description.
     *
     * @param description The description of the to-do task.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Returns a string representation of the to-do task, prefixed with "[T]" to indicate
     * it is a to-do task, followed by the base task representation which includes the status icon and description.
     *
     * @return A formatted string representing the to-do task with its completion status and description.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Converts the to-do task into a string format suitable for file storage.
     * This format includes the task type "T", its completion status (1 for done, 0 for not done),
     * and the task description.
     *
     * @return A string formatted for saving the task to a file, which includes the type, status, and description.
     */
    @Override
    public String toFileFormat() {
        return "T | " + (isDone ? 1 : 0) + " | " + description;
    }
}
