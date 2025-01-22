package blob.model;

public abstract class Task {
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
        return (isDone ? "[X] " : "[ ] ");
    }

    public String toString() {
        return getStatusIcon() + " " + description;
    }

    public abstract String toFileFormat();

    public static Task parse(String line) {
        String[] parts = line.split(" \\| ");
        if (parts.length < 3) {
            System.out.println("Skipping invalid or incomplete line: " + line);
            return null;
        }

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
        default:
            System.out.println("Unknown task type: " + type);
        }

        if (task != null && isDone) {
            task.markDone();
        }

        return task;
    }
}
