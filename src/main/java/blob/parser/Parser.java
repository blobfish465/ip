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

public class Parser {
    public Command parse(String input) throws BlobExceptions.UnknownCommandException, BlobExceptions.EmptyDescriptionException, BlobExceptions.IllegalFormatException, BlobExceptions.WrongTaskIndexException {
        String[] segments = input.split(" ", 2);
        String commandType = segments[0];
        String arguments = segments.length > 1 ? segments[1] : "";

        switch (commandType) {
            case "bye":
                return new ExitCommand();
            case "list":
                if (!arguments.isEmpty()) {
                    throw new BlobExceptions.IllegalFormatException("The 'list' blob.command does not take any arguments.");
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

