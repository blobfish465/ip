import java.util.Scanner;
import java.util.ArrayList;

public class Blob {
    public static void main(String[] args) {
        String greeting = "Hello! I'm Blob\n"
                + "What can I do for you?\n";

        String farewell = "Bye. Hope to see you again soon!";

        // Print greeting message
        System.out.println(greeting);

        // Create a new Scanner for user input
        Scanner scanner = new Scanner(System.in);

        // Using ArrayList to store tasks
        ArrayList<Task> tasks = new ArrayList<>();

        while (true) {
            String input = scanner.nextLine().trim();
            if (input.isEmpty()) continue;

            // Split input into command and argument
            String[] segment = input.split(" ", 2);
            String command = segment[0];

            try {
                switch (command) {
                    case "bye":
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
                            System.out.println("Nice! I've marked this task as done:\n  " + task);
                        } else {
                            Task task = tasks.get(ind);
                            task.unmarkDone();
                            System.out.println("OK, I've marked this task as not done yet:\n  " + task);
                        }
                        break;

                    case "todo":
                        if (segment.length < 2 || segment[1].isEmpty())
                            throw new BlobExceptions.EmptyDescriptionException();
                        tasks.add(new ToDo(segment[1]));
                        // Print message to console
                        System.out.println("Got it. I've added this task:\n  "
                                + tasks.get(tasks.size() - 1) + "\nNow you have " + tasks.size() + " tasks in the list.");
                        break;

                    case "deadline":
                        if (segment.length < 2 || !segment[1].contains(" /by "))
                            throw new BlobExceptions.IllegalFormatException("deadline <description> /by <time>");
                        // Split the remaining input into [deadline description, by when]
                        String[] deadlineSegments = segment[1].split(" /by ", 2);
                        Deadline deadline = new Deadline(deadlineSegments[0], deadlineSegments[1]);

                        tasks.add(deadline);
                        // Print message to console
                        System.out.println("Got it. I've added this task:\n  "
                                + deadline + "\nNow you have " + tasks.size() + " tasks in the list.");
                        break;

                    case "event":
                        if (segment.length < 2 || !segment[1].contains(" /from ") || !segment[1].contains(" /to "))
                            throw new BlobExceptions.IllegalFormatException("event <description> /from <start time> /to <end time>");
                        // Split the remaining input into [event description, from, to]
                        String[] eventSegments = segment[1].split(" /from | /to ", 3);
                        Event event = new Event(eventSegments[0], eventSegments[1], eventSegments[2]);

                        // Add event into tasks array list
                        tasks.add(event);
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