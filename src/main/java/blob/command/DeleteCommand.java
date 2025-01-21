package blob.command;

import blob.ui.Ui;
import blob.TaskList;
import blob.storage.Storage;
import blob.model.Task;

public class DeleteCommand implements Command {
    private int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task removedTask = tasks.deleteTask(index);
        ui.showTaskDeleted(removedTask, tasks.getSize());
    }

    @Override
    public boolean isExitCommand() {
        return false;
    }
}

