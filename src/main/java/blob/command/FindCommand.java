package blob.command;

import blob.storage.Storage;
import blob.ui.Ui;
import blob.TaskList;

import java.util.List;

/**
 * Represents a command to find tasks containing a specified word.
 */
public class FindCommand implements Command {
    private final String word;

    /**
     * Constructs a FindCommand with the specified word.
     *
     * @param word The keyword to search for in task descriptions.
     */
    public FindCommand(String word) {
        this.word = word;
    }

    /**
     * Executes the find command by searching for tasks containing the word
     * and displaying them to the user.
     *
     * @param tasks   The TaskList containing all tasks.
     * @param ui      The UI instance for displaying messages to the user.
     * @param storage The Storage instance for file operations (not used here).
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        List<String> matchingTasks = tasks.findTasks(word);
        ui.showMatchingTasks(matchingTasks);
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
