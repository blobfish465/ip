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
            String input = scanner.nextLine();
            // Split input into command and argument
            String[] segment = input.split(" ", 2);
            String command = segment[0];

            if (command.equals("bye")) {
                // Print farewell message
                System.out.println(farewell);
                break;
            }

            // List command lists all tasks of the user
            if (command.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < numOfTask; i++) {
                    System.out.println((i + 1) + ". " + tasks[i]);
                }
            } else if (command.equals("mark") && segment.length > 1) {
                // Convert the argument(task number) into an integer
                int taskNumInt = Integer.parseInt(segment[1]);
                // Get the index corresponding to the task number
                int ind = taskNumInt - 1;

                if (ind >= 0 && ind < numOfTask) {
                    tasks[ind].markDone();
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println("  " + tasks[ind]);
                }
            } else if (command.equals("unmark") && segment.length > 1){
                // Convert the argument(task number) into an integer
                int taskNumInt = Integer.parseInt(segment[1]);
                // Get the index corresponding to the task number
                int ind = taskNumInt - 1;
                if (ind >= 0 && ind < numOfTask) {
                    tasks[ind].unmarkDone();
                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println("  " + tasks[ind]);
                }

            } else if (command.startsWith("todo")) {
                // Get the description
                ToDo todo = new ToDo(segment[1]);

                // Add into tasks array
                tasks[numOfTask] = todo;

                // Increment number of tasks
                numOfTask++;
                // Print message to console
                System.out.println("Got it. I've added this task:\n  "
                        + todo + "\nNow you have " + numOfTask + " tasks in the list.");
            } else if (command.startsWith("deadline")) {
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
            } else if (command.startsWith("event")) {
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
            }
        }

        // Close the scanner
        scanner.close();
    }
}