package blob;

/**
 * The entry point for launching the Blob application.
 *
 * This class serves as a workaround for JavaFX's classloading requirements.
 * It delegates execution to the {@link Main} class, which starts the JavaFX application.
 */
public class Launcher {

    /**
     * Main method to launch the Blob application.
     * Calls {@code Main.main(args)} to initialize and start the JavaFX application.
     * This separate launcher class is necessary because JavaFX applications require
     * the main application class to extend {@code javafx.application.Application},
     * and having a direct main method in such a class can cause issues with certain Java runtimes.
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        Main.main(args);
    }
}
