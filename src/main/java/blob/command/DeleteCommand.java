package blob.command;

import blob.TaskList;
import blob.exception.BlobExceptions;
import blob.model.Task;
import blob.storage.Storage;
import blob.ui.Ui;

/**
 * Represents a command to delete a task from the task list.
 * This command handles the removal of a task based on an index provided by the user
 * and updates the user interface accordingly.
 */
public class DeleteCommand implements Command {
    private int index;

    /**
     * Constructs a DeleteCommand with the specified index.
     *
     * @param index The index of the task in the task list that should be deleted.
     *              The index is expected to be zero-based and valid.
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the delete command which involves removing a task from the task list
     * and updating the UI to show the task has been deleted.
     *
     * @param tasks The task list from which the task will be deleted.
     * @param ui The UI to interact with the user.
     * @param storage The storage used to save the updated task list.
     *                Note that the storage may not be directly used in this method
     *                but is included to adhere to the command interface.
     * @throws BlobExceptions.WrongTaskIndexException If the command includes an index that is not a valid integer or out of bounds.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws BlobExceptions.WrongTaskIndexException {
        try {
            Task removedTask = tasks.deleteTask(index);
            ui.showTaskDeleted(removedTask, tasks.getSize());
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

