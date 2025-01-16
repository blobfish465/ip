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

        while (true) {
            String input = scanner.nextLine();

            if (input.equals("bye")) {
                // Print farewell message
                System.out.println(farewell);
                break;
            }

            // Echo the user input
            System.out.println(input);
        }

        // Close the scanner
        scanner.close();
    }
}