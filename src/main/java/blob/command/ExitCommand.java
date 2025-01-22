package blob.command;

import blob.storage.Storage;
import blob.ui.Ui;
import blob.TaskList;

import java.io.IOException;

<<<<<<< HEAD
/**
 * Represents a command to terminate the application.
 * This command handles the process of exiting the program after saving all tasks
 * to the designated storage.
 */
=======
>>>>>>> branch-A-CodingStandard
public class ExitCommand implements Command {

    /**
     * Executes the exit command which involves saving the current state of the task list
     * to storage and then signaling the user interface to display a farewell message.
     * This command causes the program loop to terminate, closing the application.
     *
     * @param tasks The task list whose state is to be saved.
     * @param ui The UI to interact with the user.
     * @param storage The storage used to save the current state of tasks.
     * @throws IOException If an I/O error occurs during saving of tasks.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        try {
            storage.save(tasks.getTasks());
        } catch (IOException e) {
            System.err.println("Error saving tasks: " + e.getMessage());
            throw e;
        }
        ui.showFarewell();
    }

    /**
     * Indicates whether this command is an "exit" command.
     *
     * @return true as this command terminates the application.
     */
    @Override
    public boolean isExitCommand() {
        return true;
    }
}

