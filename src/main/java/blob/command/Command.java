package blob.command;

import blob.exception.BlobExceptions;
import blob.storage.Storage;
import blob.ui.Ui;
import blob.TaskList;
import java.io.IOException;

public interface Command {
    void execute(TaskList tasks, Ui ui, Storage storage) throws IOException, BlobExceptions.EmptyDescriptionException, BlobExceptions.UnknownCommandException, BlobExceptions.IllegalFormatException, BlobExceptions.WrongTaskIndexException, BlobExceptions.NoTaskException;
    boolean isExitCommand();
}
