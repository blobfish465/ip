package blob.command;

import blob.exception.BlobExceptions;
import blob.storage.Storage;
import blob.ui.Ui;
import blob.TaskList;

import java.io.IOException;

/**
 * Represents a command that can be executed in the Blob application.
 * Each command performs an action on the provided {@link TaskList}, updates the user interface
 * through {@link Ui}, and may interact with {@link Storage} to persist changes.
 */
public interface Command {

    /**
     * Executes the command using the provided {@link TaskList}, {@link Ui}, and {@link Storage}.
     *
     * @param tasks   The task list to operate on.
     * @param ui      The UI object used to display messages to the user.
     * @param storage The storage object used to save or load data.
     * @throws IOException                              If an I/O error occurs during storage operations.
     * @throws BlobExceptions.EmptyDescriptionException If the command's description is empty.
     * @throws BlobExceptions.UnknownCommandException   If the command is unrecognized.
     * @throws BlobExceptions.IllegalFormatException    If the command is improperly formatted.
     * @throws BlobExceptions.WrongTaskIndexException   If the specified task index is invalid.
     * @throws BlobExceptions.NoTaskException           If there are no tasks in the task list to operate on.
     */
    void execute(TaskList tasks, Ui ui, Storage storage) throws IOException, BlobExceptions.EmptyDescriptionException,
            BlobExceptions.UnknownCommandException, BlobExceptions.IllegalFormatException,
            BlobExceptions.WrongTaskIndexException, BlobExceptions.NoTaskException;

    /**
     * Checks if the command is an exit command.
     *
     * @return {@code true} if this command signals the application to terminate, {@code false} otherwise.
     */
    boolean isExitCommand();
}
