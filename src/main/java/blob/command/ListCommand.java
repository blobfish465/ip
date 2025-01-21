package blob.command;

import blob.storage.Storage;
import blob.ui.Ui;
import blob.TaskList;

public class ListCommand implements Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showTasks(tasks);
    }

    @Override
    public boolean isExitCommand() {
        return false;
    }
}


