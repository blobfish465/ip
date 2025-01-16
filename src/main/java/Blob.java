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

            } else {
                Task task = new Task(input);

                // Add task into tasks array
                tasks[numOfTask] = task;

                // Increment number of tasks
                numOfTask++;
                // Print message to console
                System.out.println("added: " + input);
            }
        }

        // Close the scanner
        scanner.close();
    }
}