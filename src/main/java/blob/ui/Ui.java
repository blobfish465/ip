package blob.ui;

import blob.model.Task;
import blob.TaskList;
import java.util.Scanner;
import java.util.List;

/**
 * Handles all user interface operations for the Blob application.
 * This class manages inputs and outputs, interacting directly with the user.
 */
public class Ui {
    private final Scanner scanner;
    private StringBuilder output;

    /**
     * Constructs a new UI object, initializing the scanner to read user input.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
        this.output = new StringBuilder();
    }

    /**
     * Adds a message to the output buffer.
     *
     * @param message The message to add.
     */
    private void appendMessage(String message) {
        if (output.length() > 0) {
            output.append("\n");
        }
        output.append(message);
    }

    /**
     * Retrieves and clears the current output buffer.
     *
     * @return The current output as a string.
     */
    public String getOutput() {
        String result = output.toString();
        output.setLength(0); // Clear the buffer
        return result;
    }

    /**
     * Reads a command from the user, displaying a prompt on the console.
     *
     * @return The trimmed command input from the user.
     */
    public String readCommand() {
        System.out.print("Enter command: ");
        return scanner.nextLine().trim();
    }

    /**
     * Displays a greeting message to the user when the application starts.
     */
    public void showGreeting() {
        appendMessage("Hello! I'm Blob\nWhat can I do for you?");
    }

    /**
     * Displays a farewell message to the user when the application terminates.
     */
    public void showFarewell() {
        appendMessage("Bye. Hope to see you again soon!");
    }

    /**
     * Displays an error message to the user.
     *
     * @param message The error message to be displayed.
     */
    public void showError(String message) {
        appendMessage("Error: " + message);
    }

    /**
     * Displays all tasks currently in the task list.
     *
     * @param tasks The TaskList containing the tasks to be displayed.
     */
    public void showTasks(TaskList tasks) {
        List<Task> list = tasks.getTasks();
        if (list.isEmpty()) {
            appendMessage("Your task list has no task! Add some tasks to it!");
        } else {
            appendMessage("Here are the tasks in your list:");
            for (int i = 0; i < list.size(); i++) {
                appendMessage((i + 1) + ". " + list.get(i));
            }
        }
    }

    /**
     * Displays a confirmation message when a task is added, showing the task and the new total number of tasks.
     *
     * @param task The task that was added.
     * @param totalTasks The total number of tasks in the list after adding the new task.
     */
    public void showTaskAdded(Task task, int totalTasks) {
        appendMessage("Got it. I've added this task:\n  " + task);
        appendMessage("Now you have " + totalTasks + " tasks in the list.");
    }

    /**
     * Displays a confirmation message when a task is deleted, showing the removed task and the new total number of tasks.
     *
     * @param task The task that was removed.
     * @param totalTasks The total number of tasks in the list after removing the task.
     */
    public void showTaskDeleted(Task task, int totalTasks) {
        appendMessage("Noted. I've removed this task:\n  " + task);
        appendMessage("Now you have " + totalTasks + " tasks in the list.");
    }

    /**
     * Displays a message when a task is marked as done.
     *
     * @param task The task that was marked as done.
     */
    public void showTaskMarked(Task task) {
        appendMessage("Nice! I've marked this task as done:\n  " + task);
    }

    /**
     * Displays a message when a task is marked as not done.
     *
     * @param task The task that was marked as not done.
     */
    public void showTaskUnmarked(Task task) {
        appendMessage("OK, I've marked this task as not done yet:\n  " + task);
    }


    /**
     * Displays tasks that match the searched word to the user.
     *
     * @param matchingTasks A list of matching tasks to display.
     */
    public void showMatchingTasks(List<String> matchingTasks) {
        if (matchingTasks.isEmpty()) {
            appendMessage("No matching tasks found.");
        } else {
            appendMessage("Here are the matching tasks in your list:");
            for (String task : matchingTasks) {
                appendMessage(task);
            }
        }
    }

    /**
     * Closes the scanner when the application is terminated, to free up resources.
     */
    public void closeScanner() {
        scanner.close();
    }
}

