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
        String[] tasks = new String[200];

        // Initialize number of tasks to be 0 at first
        int numOfTask = 0;

        while (true) {
            String input = scanner.nextLine();

            if (input.equals("bye")) {
                // Print farewell message
                System.out.println(farewell);
                break;
            }

            // List command lists all tasks of the user
            if (input.equals("list")) {
                for (int i = 0; i < numOfTask; i++) {
                    System.out.println((i + 1) + ". " + tasks[i]);
                }
            } else {
                // Add task into tasks array
                tasks[numOfTask] = input;

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