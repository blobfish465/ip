package blob.exception;

/**
 * Defines custom exceptions for Blob to handle various error scenarios
 * related to user input and task list operations.
 */
public class BlobExceptions {

    /**
     * Exception thrown when an input command lacks a required description.
     */
    public static class EmptyDescriptionException extends Exception {
        /**
         * Constructs an exception indicating that a description is missing from a command.
         */
        public EmptyDescriptionException() {
            super("Oh no! The description of a task cannot be empty.\n"
                    + "Add a space and a description after your command");
        }
    }

    /**
     * Exception thrown when the entered command is not recognized by the parser.
     */
    public static class UnknownCommandException extends Exception {
        /**
         * Constructs an exception indicating that the entered command is unknown.
         */
        public UnknownCommandException() {
            super("What are you saying? I don't know what you mean. Enter a valid command.");
        }
    }

    /**
     * Exception thrown when a task index provided is outside the valid range.
     */
    public static class WrongTaskIndexException extends Exception {
        /**
         * Constructs an exception indicating that the provided task index is out of bounds.
         */
        public WrongTaskIndexException() {
            super("The task number you provided is out of bounds!\n"
                    + "Use the list command to check your desired the task index.");
        }
    }

    /**
     * Exception thrown when an operation that expects tasks is performed on an empty task list.
     */
    public static class NoTaskException extends Exception {
        /**
         * Constructs an exception indicating that the task list is empty when it should not be.
         */
        public NoTaskException() {
            super("Your task list has no task! Add some tasks to it!");
        }
    }

    /**
     * Exception thrown when an input does not follow the expected format.
     */
    public static class IllegalFormatException extends Exception {
        /**
         * Constructs an exception indicating that the input does not conform to the expected format.
         * @param format A string describing the expected format.
         */
        public IllegalFormatException(String format) {
            super("Wrong format! Please follow this format:\n  " + format);
        }
    }
}

