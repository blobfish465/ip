package blob.command;

import blob.exception.BlobExceptions;
import blob.model.Task;
import blob.storage.Storage;
import blob.ui.Ui;
import blob.TaskList;

/**
 * Represents a command to unmark a task as done in the task list.
 * This command is used to change the status of a specific task to "not done."
 */
public class UnmarkCommand implements Command {
    private int index;

    /**
     * Constructs an UnmarkCommand with the specified task index.
     *
     * @param index The index of the task in the task list that is to be unmarked as done.
     *              The index is zero-based and must be within the range of the task list size.
     */
    public UnmarkCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the unmark command which involves changing a specified task's status to not done.
     * The task to be unmarked is retrieved using its index, unmarked as done, and the change is shown to the user via the UI.
     *
     * @param tasks The task list where the task to be unmarked is stored.
     * @param ui The UI to interact with the user and show feedback.
     * @param storage The storage component, not directly used by this command but required by the interface.
     * @throws BlobExceptions.WrongTaskIndexException If the command includes an index that is not a valid integer or out of bounds.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws BlobExceptions.WrongTaskIndexException {
        try {
            Task task = tasks.getTask(index);
            task.unmarkDone();
            ui.showTaskUnmarked(task);
        } catch (IndexOutOfBoundsException e) {
            throw new BlobExceptions.WrongTaskIndexException();
        }

    }

    /**
     * Indicates whether this command is an "exit" command.
     *
     * @return false as this command does not terminate the application.
     */
    @Override
    public boolean isExitCommand() {
        return false;
    }
}
