package blob;

import blob.command.Command;
import blob.exception.BlobExceptions;
import blob.parser.Parser;
import blob.storage.Storage;
import blob.ui.Ui;

import java.io.IOException;

/**
 * Main class of the Blob application.
 * Initializes and coordinates the various components of the application such as UI, storage, and command parsing.
 */
public class Blob {
    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;
    private final Parser parser;

    /**
     * Constructs the main application object, setting up storage, UI, and parser components.
     *
     * @param filePath The path to the file where tasks are stored and loaded from.
     */
    public Blob(String filePath) {
        ui = new Ui();
        parser = new Parser();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (IOException e) {
            ui.showError("Error loading tasks: " + e.getMessage());
            tasks = new TaskList();
        }
    }

    /**
     * Runs the main loop of the application, processing user commands until the exit command is received.
     */
    public void run() {
        ui.showGreeting();
        boolean isRunning = true;
        while (isRunning) {
            try {
                String input = ui.readCommand();
                Command command = parser.parse(input);
                command.execute(tasks, ui, storage);
                if (command.isExitCommand()) {
                    isRunning = false;
                }
            } catch (BlobExceptions.EmptyDescriptionException | BlobExceptions.UnknownCommandException |
                     BlobExceptions.IllegalFormatException | BlobExceptions.WrongTaskIndexException |
                     BlobExceptions.NoTaskException e) {
                ui.showError(e.getMessage());
            } catch (IOException e) {
                ui.showError("File I/O error: " + e.getMessage());
            } catch (Exception e) {
                ui.showError("An unexpected error occurred: " + e.getMessage());
            }

        }
        ui.closeScanner();
    }

    /**
     * The entry point of the application.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        new Blob("data/Blob.txt").run();
    }
}

