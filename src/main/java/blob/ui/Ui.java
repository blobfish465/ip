package blob.ui;

import java.util.Scanner;
import java.util.List;
import blob.model.Task;
import blob.TaskList;

public class Ui {
    private Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public String readCommand() {
        System.out.print("Enter command: ");
        return scanner.nextLine().trim();
    }

    public void showGreeting() {
        System.out.println("Hello! I'm blob.Blob\nWhat can I do for you?");
    }

    public void showFarewell() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public void showError(String message) {
        System.out.println("Error: " + message);
    }

    public void showTasks(TaskList tasks) {
        System.out.println("Here are the tasks in your list:");
        List<Task> list = tasks.getTasks();
        for (int i = 0; i < list.size(); i++) {
            System.out.println((i + 1) + ". " + list.get(i));
        }
    }

    public void showTaskAdded(Task task, int totalTasks) {
        System.out.println("Got it. I've added this task:\n  " + task);
        System.out.println("Now you have " + totalTasks + " tasks in the list.");
    }

    public void showTaskDeleted(Task task, int totalTasks) {
        System.out.println("Noted. I've removed this task:\n  " + task);
        System.out.println("Now you have " + totalTasks + " tasks in the list.");
    }

    public void showTaskMarked(Task task) {
        System.out.println("Nice! I've marked this task as done:\n  " + task);
    }

    public void showTaskUnmarked(Task task) {
        System.out.println("OK, I've marked this task as not done yet:\n  " + task);
    }

    public void closeScanner() {
        scanner.close();
    }
}

