import java.io.IOException;

public class Blob {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;
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
            } catch (BlobExceptions.EmptyDescriptionException | BlobExceptions.UnknownCommandException | BlobExceptions.IllegalFormatException | BlobExceptions.WrongTaskIndexException | BlobExceptions.NoTaskException e) {
                ui.showError(e.getMessage());
            } catch (IOException e) {
                ui.showError("File I/O error: " + e.getMessage());
            } catch (Exception e) {
                ui.showError("An unexpected error occurred: " + e.getMessage());
            }
        }
        ui.closeScanner();
    }

    public static void main(String[] args) {
        new Blob("data/Blob.txt").run();
    }
}

