package blob.command;

import blob.model.Task;
import blob.storage.Storage;
import blob.ui.Ui;
import blob.TaskList;

public class MarkCommand implements Command {
    private int index;

    public MarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task task = tasks.getTask(index);
        task.markDone();
        ui.showTaskMarked(task);
    }

    @Override
    public boolean isExitCommand() {
        return false;
    }
}
