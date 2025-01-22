package blob.parser;

import blob.command.AddCommand;
import blob.command.Command;
import blob.command.DeleteCommand;
import blob.command.ExitCommand;
import blob.command.ListCommand;
import blob.command.MarkCommand;
import blob.command.UnmarkCommand;
import blob.exception.BlobExceptions;
import blob.model.Deadline;
import blob.model.Event;
import blob.model.ToDo;

/**
 * Parses user input into specific command objects that can be executed by the application.
 * This class translates string input into actionable commands by identifying keywords
 * and necessary parameters to construct command instances.
 */
public class Parser {

    /**
     * Parses the input string to determine which type of command to create and execute.
     *
     * @param input The full string input by the user.
     * @return Command The specific command object that corresponds to the user input.
     * @throws BlobExceptions.UnknownCommandException If the command type is not recognized.
     * @throws BlobExceptions.EmptyDescriptionException If the command requires a description which is not provided.
     * @throws BlobExceptions.IllegalFormatException If the input does not match the expected format for the command.
     * @throws BlobExceptions.WrongTaskIndexException If the command includes an index that is not a valid integer or out of bounds.
     */
    public Command parse(String input) throws BlobExceptions.UnknownCommandException, BlobExceptions.EmptyDescriptionException, BlobExceptions.IllegalFormatException, BlobExceptions.WrongTaskIndexException {
        String[] segments = input.split(" ", 2);
        String commandType = segments[0];
        String arguments = segments.length > 1 ? segments[1] : "";

        switch (commandType) {
            case "bye":
                return new ExitCommand();
            case "list":
                if (!arguments.isEmpty()) {
                    throw new BlobExceptions.IllegalFormatException("The 'list' command does not take any arguments.");
                }
                return new ListCommand();
            case "mark":
                if (arguments.isEmpty()) {
                    throw new BlobExceptions.IllegalFormatException("Usage: mark <task number>");
                }
                try {
                    int index = Integer.parseInt(arguments);
                    return new MarkCommand(index);
                } catch (NumberFormatException e) {
                    throw new BlobExceptions.WrongTaskIndexException();
                }
            case "unmark":
                if (arguments.isEmpty()) {
                    throw new BlobExceptions.IllegalFormatException("Usage: unmark <task number>");
                }
                try {
                    int index = Integer.parseInt(arguments);
                    return new UnmarkCommand(index);
                } catch (NumberFormatException e) {
                    throw new BlobExceptions.WrongTaskIndexException();
                }
            case "todo":
                if (arguments.isEmpty()) {
                    throw new BlobExceptions.EmptyDescriptionException();
                }
                return new AddCommand(new ToDo(arguments));
            case "deadline":
                if (!arguments.contains("/by")) {
                    throw new BlobExceptions.IllegalFormatException("Usage: deadline <description> /by <yyyy-MM-dd HHmm>");
                }
                String[] deadlineParts = arguments.split(" /by ");
                if (deadlineParts.length < 2) {
                    throw new BlobExceptions.IllegalFormatException("deadline <description> /by <yyyy-MM-dd HHmm>");
                }
                return new AddCommand(new Deadline(deadlineParts[0], deadlineParts[1]));
            case "event":
                String[] eventParts = arguments.split(" /from | /to ");
                if (eventParts.length < 3) {
                    throw new BlobExceptions.IllegalFormatException("event <description> /from <yyyy-MM-dd HHmm> /to <yyyy-MM-dd HHmm>");
                }
                return new AddCommand(new Event(eventParts[0], eventParts[1], eventParts[2]));
            case "delete":
                if (arguments.isEmpty()) {
                    throw new BlobExceptions.IllegalFormatException("Usage: delete <task number>");
                }
                try {
                    int delIndex = Integer.parseInt(arguments);
                    return new DeleteCommand(delIndex);
                } catch (NumberFormatException e) {
                    throw new BlobExceptions.WrongTaskIndexException();
                }
            default:
                throw new BlobExceptions.UnknownCommandException();
        }
    }
}

