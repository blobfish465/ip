package blob.command;

import blob.storage.Storage;
import blob.ui.Ui;
import blob.TaskList;

/**
 * Represents a command to list all tasks in the task list.
 * This command is used to display all the tasks currently stored in the task list.
 */
public class ListCommand implements Command {

    /**
     * Executes the list command which involves displaying all tasks currently in the task list.
     * The tasks are displayed to the user via the UI component.
     *
     * @param tasks The task list whose contents are to be displayed.
     * @param ui The UI to interact with the user.
     * @param storage The storage component, not directly used by this command but required by the interface.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showTasks(tasks);
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


