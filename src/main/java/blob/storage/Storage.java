package blob.storage;

import blob.model.Task;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Handles the storage operations for tasks, allowing tasks to be loaded from
 * and saved to a file.
 */
public class Storage {
    private final String filePath;

    /**
     * Constructs a new Storage object to manage file operations for tasks.
     *
     * @param filePath The file path where tasks are stored.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads tasks from the specified file path into an ArrayList.
     * If the file does not exist, it returns an empty ArrayList.
     *
     * @return An ArrayList of Task objects loaded from the file.
     * @throws IOException If an I/O error occurs while reading from the file.
     */
    public ArrayList<Task> load() throws IOException {
        File file = new File(filePath);
        ArrayList<Task> tasks = new ArrayList<>();
        if (!file.exists()) {
            return tasks;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Task task = Task.parse(line);
                if (task != null) {
                    tasks.add(task);
                }
            }
        }
        return tasks;
    }

    /**
     * Saves the list of tasks to the file at the specified file path.
     * Each task is converted to a formatted string suitable for file storage.
     *
     * @param tasks The ArrayList of Task objects to be saved.
     * @throws IOException If an I/O error occurs while writing to the file.
     */
    public void save(ArrayList<Task> tasks) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Task task : tasks) {
                String taskData = task.toFileFormat();
                assert taskData != null && !taskData.isEmpty()
                        : "Task data to be saved should not be null or empty";
                writer.write(taskData + "\n");
            }
        }
    }
}
