package blob.command;

import blob.storage.Storage;
import blob.ui.Ui;
import blob.TaskList;

import java.io.IOException;
public class ExitCommand implements Command {
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

    @Override
    public boolean isExitCommand() {
        return true;
    }
}

