public class BlobExceptions {

    public static class EmptyDescriptionException extends Exception {
        public EmptyDescriptionException() {
            super("Oh no! The description of a task cannot be empty.\n"
                    + "Add a space and a description after your command");
        }
    }

    public static class UnknownCommandException extends Exception {
        public UnknownCommandException() {
            super("What are you saying? i don't know what you mean. Enter a valid command.");
        }
    }

    public static class WrongTaskIndexException extends Exception {
        public WrongTaskIndexException() {
            super("The task number you provided is out of bounds!\n"
            + "Use the list command to check your desired the task index.");
        }
    }

    public static class NoTaskException extends Exception {
        public NoTaskException() {
            super("Your task list has no task! Add some tasks to it!");
        }
    }

    public static class IllegalFormatException extends Exception {
        public IllegalFormatException(String format) {
            super("Wrong format! Please follow this format:\n  " + format);
        }
    }
}

