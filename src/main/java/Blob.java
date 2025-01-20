import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

public class Blob {

    private static final String FILE_PATH = "./data/Blob.txt";

    private static ArrayList<Task> loadTasks() {
        ArrayList<Task> tasks = new ArrayList<>();
        File file = new File(FILE_PATH);
        // Returns empty array list if file does not exist
        if (!file.exists()) {
            // System.out.println("File does not exist");
            return tasks;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Call the parse method in Task class
                Task task = Task.parse(line);
                tasks.add(task);
            }
        } catch (IOException e) {
            System.out.println("Error loading tasks: " + e.getMessage());
        }
        return tasks;
    }

    private static void saveTasks(ArrayList<Task> tasks) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (Task task : tasks) {
                // Call the toFileFormat method in Task class, which
                // Converts the task into a string format for file storage
                // Format e.g E | 0 | project meeting | Aug 6th 2-4pm
                writer.write(task.toFileFormat() + "\n");
            }
        } catch (IOException e) {
            System.out.println("Error saving tasks: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        String greeting = "Hello! I'm Blob\n"
                + "What can I do for you?\n";

        String farewell = "Bye. Hope to see you again soon!";

        // Print greeting message
        System.out.println(greeting);

        // Create a new Scanner for user input
        Scanner scanner = new Scanner(System.in);

        // Using ArrayList to store tasks, Load Tasks from data file
        ArrayList<Task> tasks = loadTasks();

        while (true) {
            String input = scanner.nextLine().trim();
            if (input.isEmpty()) continue;

            // Split input into command and argument
            String[] segment = input.split(" ", 2);
            String command = segment[0];

            try {
                switch (command) {
                    case "bye":
                        // Save tasks to blob.txt file
                        saveTasks(tasks);
                        // Print farewell message
                        System.out.println(farewell);
                        scanner.close();
                        return;

                    case "list":
                        // List command lists all tasks of the user
                        if (tasks.isEmpty()) throw new BlobExceptions.NoTaskException();
                        System.out.println("Here are the tasks in your list:");
                        for (int i = 0; i < tasks.size(); i++) {
                            System.out.println((i + 1) + ". " + tasks.get(i));
                        }
                        break;

                    case "mark":
                    case "unmark":
                        if (segment.length < 2)
                            throw new BlobExceptions.IllegalFormatException("mark/unmark <task number>");
                        // Convert the argument(task number) into an integer
                        int taskNumInt = Integer.parseInt(segment[1]);
                        // Get the index corresponding to the task number
                        int ind = taskNumInt - 1;
                        if (ind < 0 || ind >= tasks.size()) throw new BlobExceptions.WrongTaskIndexException();
                        if (command.equals("mark")) {
                            Task task = tasks.get(ind);
                            task.markDone();
                            // Save changes to blob.txt file
                            saveTasks(tasks);
                            System.out.println("Nice! I've marked this task as done:\n  " + task);
                        } else {
                            Task task = tasks.get(ind);
                            task.unmarkDone();
                            // Save changes to file
                            saveTasks(tasks);
                            System.out.println("OK, I've marked this task as not done yet:\n  " + task);
                        }
                        break;

                    case "todo":
                        if (segment.length < 2 || segment[1].isEmpty())
                            throw new BlobExceptions.EmptyDescriptionException();
                        tasks.add(new ToDo(segment[1]));
                        // Save changes to blob.txt file
                        saveTasks(tasks);
                        // Print message to console
                        System.out.println("Got it. I've added this task:\n  "
                                + tasks.get(tasks.size() - 1) + "\nNow you have " + tasks.size() + " tasks in the list.");
                        break;

                    case "deadline":
                        if (segment.length < 2 || !segment[1].contains(" /by "))
                            throw new BlobExceptions.IllegalFormatException("deadline <description> /by <yyyy-MM-dd> HHmm");
                        // Split the remaining input into [deadline description, by when]
                        String[] deadlineSegments = segment[1].split(" /by ", 2);
                        if (deadlineSegments.length < 2) {
                            throw new BlobExceptions.IllegalFormatException("deadline <description> /by <yyyy-MM-dd HHmm>");
                        }
                        Deadline deadline = new Deadline(deadlineSegments[0].trim(), deadlineSegments[1].trim());

                        tasks.add(deadline);
                        // Save changes to blob.txt file
                        saveTasks(tasks);
                        // Print message to console
                        System.out.println("Got it. I've added this task:\n  "
                                + deadline + "\nNow you have " + tasks.size() + " tasks in the list.");
                        break;

                    case "event":
                        // Input format: event team meeting /from 2023-03-01 1000 /to 2023-03-01 1200
                        if (segment.length < 2 || !segment[1].contains(" /from ") || !segment[1].contains(" /to "))
                            throw new BlobExceptions.IllegalFormatException("event <description> /from <yyyy-MM-dd HHmm> /to <yyyy-MM-dd HHmm>");
                        // Split the remaining input into [event description, from, to]
                        String[] eventSegments = segment[1].split(" /from | /to ", 3);
                        if (eventSegments.length < 3) {
                            throw new BlobExceptions.IllegalFormatException("event <description> /from <yyyy-MM-dd HHmm> /to <yyyy-MM-dd HHmm>");
                        }
                        Event event = new Event(eventSegments[0].trim(), eventSegments[1].trim(), eventSegments[2].trim());

                        // Add event into tasks array list
                        tasks.add(event);
                        // Save changes to blob.txt file
                        saveTasks(tasks);
                        // Print message to console
                        System.out.println("Got it. I've added this task:\n  "
                                + event + "\nNow you have " + tasks.size() + " tasks in the list.");
                        break;

                    case "delete":
                        if (segment.length < 2)
                            throw new BlobExceptions.IllegalFormatException("delete <task number>");
                        int deleteIndex = Integer.parseInt(segment[1]) - 1;
                        if (deleteIndex < 0 || deleteIndex >= tasks.size()) throw new BlobExceptions.WrongTaskIndexException();
                        Task removedTask = tasks.remove(deleteIndex);
                        // Save changes to blob.txt file
                        saveTasks(tasks);
                        System.out.println("Noted. I've removed this task:\n  " + removedTask);
                        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                        break;

                    default:
                        throw new BlobExceptions.UnknownCommandException();
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            System.out.println();
        }
    }
}