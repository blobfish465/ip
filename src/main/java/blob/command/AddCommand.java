package blob.command;

import blob.model.Task;
import blob.storage.Storage;
import blob.ui.Ui;
import blob.TaskList;

public class AddCommand implements Command {
    private Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.addTask(task);
        ui.showTaskAdded(task, tasks.getSize());
    }

    @Override
    public boolean isExitCommand() {
        return false;
    }
}

