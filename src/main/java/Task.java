public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void markDone() {
        isDone = true;
    }

    public void unmarkDone() {
        isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "[X] " : "[ ] "); // mark done task with X
    }

    // To be overridden by child classes
    public String toString() {
        return getStatusIcon() + " " + description;
    }

    // Converts the task into a string format for file storage
    public String toFileFormat() {
        return (this instanceof ToDo ? "T" :
                this instanceof Deadline ? "D" :
                        this instanceof Event ? "E" : "U") +
                " | " + (isDone ? 1 : 0) +
                " | " + description;
    }

    // Parses a string to create a specific Task object
    public static Task parse(String line) {
        String[] parts = line.split(" \\| ");
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
