package blob.model;

/**
 * Represents an abstract base class for different types of tasks.
 * This class provides common functionality for tasks such as marking them as done or not done,
 * and defining how they are represented as strings.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a new Task with the given description.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Marks this task as done.
     */
    public void markDone() {
        isDone = true;
    }

    /**
     * Marks this task as not done.
     */
    public void unmarkDone() {
        isDone = false;
    }

    /**
     * Returns the status icon indicating whether the task is done.
     *
     * @return A string representing the task's completion status ("[X] " for done, "[ ] " for not done).
     */
    public String getStatusIcon() {
        return (isDone ? "[X] " : "[ ] "); // mark done task with X
    }

    /**
     * Returns a string representation of the task, including its status icon and description.
     * This method must be overridden in subclasses to provide specific details about the task type.
     *
     * @return A string representing the task, including its status and description.
     */
    public String toString() {
        return getStatusIcon() + " " + description;
    }

    /**
     * Converts the task into a string format suitable for file storage.
     * This method must be implemented by subclasses to adapt to their specific storage format.
     *
     * @return A string formatted for file storage.
     */
    public abstract String toFileFormat();

    /**
     * Parses a formatted string to create a specific Task object.
     * The string format should be appropriate to the type of task being created.
     *
     * @param line A string representing the task in a file storage format.
     * @return A Task object corresponding to the provided string, or null if the string is invalid.
     */
    public static Task parse(String line) {
        String[] parts = line.split(" \\| ");
        if (parts.length < 3) {
            System.out.println("Skipping invalid or incomplete line: " + line);
            return null;
        }
        // Ensure there is 3 parts: Type, isDone Description
        String type = parts[0].trim();
        boolean isDone = parts[1].trim().equals("1");
        String description = parts[2].trim();
        Task task = null;

        switch (type) {
            case "T":
                task = new ToDo(description);
                break;
            case "D":
                String time = parts[3].trim();
                task = new Deadline(description, time);
                break;
            case "E":
                if (parts.length < 5) return null;
                String startTime = parts[3].trim();
                String endTime = parts[4].trim();
                task = new Event(description, startTime, endTime);
                break;
        }

        if (task != null && isDone) {
            task.markDone();
        }

        return task;
    }
}
