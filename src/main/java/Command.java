import java.io.IOException;

public interface Command {
    void execute(TaskList tasks, Ui ui, Storage storage) throws IOException, BlobExceptions.EmptyDescriptionException, BlobExceptions.UnknownCommandException, BlobExceptions.IllegalFormatException, BlobExceptions.WrongTaskIndexException, BlobExceptions.NoTaskException;
    boolean isExitCommand();
}
