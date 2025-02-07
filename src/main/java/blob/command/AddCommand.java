package blob.command;

import blob.TaskList;
import blob.model.Task;
import blob.storage.Storage;
import blob.ui.Ui;

/**
 * Represents a command to add a task to the task list.
 * This command is responsible for adding a specific task
 * and updating the user interface accordingly.
 */
public class AddCommand implements Command {
    private Task task;

    /**
     * Constructs an AddCommand with the specified task.
     *
     * @param task The task to be added to the task list.
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    /**
     * Executes the add command which involves adding a task to the task list
     * and updating the UI to show the added task.
     *
     * @param tasks The task list to which the task will be added.
     * @param ui The UI to interact with the user.
     * @param storage The storage used to save the updated task list.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.addTask(task);
        ui.showTaskAdded(task, tasks.getSize());
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

