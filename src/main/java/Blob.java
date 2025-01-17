import java.util.Scanner;

public class Blob {
    public static void main(String[] args) {
        String greeting = "Hello! I'm Blob\n"
                + "What can I do for you?\n";

        String farewell = "Bye. Hope to see you again soon!";

        // Print greeting message
        System.out.println(greeting);

        // Create a new Scanner for user input
        Scanner scanner = new Scanner(System.in);

        // Create an Array to store up to 200 tasks
        Task[] tasks = new Task[200];

        // Initialize number of tasks to be 0 at first
        int numOfTask = 0;

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
                        if (numOfTask == 0) throw new BlobExceptions.NoTaskException();
                        System.out.println("Here are the tasks in your list:");
                        for (int i = 0; i < numOfTask; i++) {
                            System.out.println((i + 1) + ". " + tasks[i]);
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
                        if (ind < 0 || ind >= numOfTask) throw new BlobExceptions.WrongTaskIndexException();
                        if (command.equals("mark")) {
                            tasks[ind].markDone();
                            System.out.println("Nice! I've marked this task as done:\n  " + tasks[ind]);
                        } else {
                            tasks[ind].unmarkDone();
                            System.out.println("OK, I've marked this task as not done yet:\n  " + tasks[ind]);
                        }
                        break;

                    case "todo":
                        if (segment.length < 2 || segment[1].isEmpty())
                            throw new BlobExceptions.EmptyDescriptionException();
                        // Get the description
                        ToDo todo = new ToDo(segment[1]);
                        // Add into tasks array
                        tasks[numOfTask] = todo;

                        // Increment number of tasks
                        numOfTask++;
                        // Print message to console
                        System.out.println("Got it. I've added this task:\n  "
                                + todo + "\nNow you have " + numOfTask + " tasks in the list.");
                        break;

                    case "deadline":
                        if (segment.length < 2 || !segment[1].contains(" /by "))
                            throw new BlobExceptions.IllegalFormatException("deadline <description> /by <time>");
                        // Split the remaining input into [deadline description, by when]
                        String[] deadlineSegments = segment[1].split(" /by ", 2);
                        Deadline deadline = new Deadline(deadlineSegments[0], deadlineSegments[1]);

                        // Add deadline into tasks array
                        tasks[numOfTask] = deadline;
                        // Increment number of tasks
                        numOfTask++;
                        // Print message to console
                        System.out.println("Got it. I've added this task:\n  "
                                + deadline + "\nNow you have " + numOfTask + " tasks in the list.");
                        break;

                    case "event":
                        if (segment.length < 2 || !segment[1].contains(" /from ") || !segment[1].contains(" /to "))
                            throw new BlobExceptions.IllegalFormatException("event <description> /from <start time> /to <end time>");
                        // Split the remaining input into [event description, from, to]
                        String[] eventSegments = segment[1].split(" /from | /to ", 3);
                        Event event = new Event(eventSegments[0], eventSegments[1], eventSegments[2]);

                        // Add event into tasks array
                        tasks[numOfTask] = event;
                        // Increment number of tasks
                        numOfTask++;
                        // Print message to console
                        System.out.println("Got it. I've added this task:\n  "
                                + event + "\nNow you have " + numOfTask + " tasks in the list.");
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