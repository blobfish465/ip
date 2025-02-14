package blob;

import blob.controller.MainWindow;
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.AnchorPane;

/**
 * A JavaFX-based GUI for the Blob chatbot using FXML.
 * <p>
 * This class initializes the graphical user interface and sets up the main window.
 * It extends {@link Application}, which is required for JavaFX applications.
 * </p>
 */
public class Main extends Application {

    /**
     * Starts the JavaFX application by loading the FXML layout and displaying the main window.
     *
     * @param stage The primary stage for this JavaFX application.
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            fxmlLoader.<MainWindow>getController().setBlob(new Blob("data/Blob.txt"));
            Scene scene = new Scene(ap);
            stage.setTitle("Blob");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Launches the Blob chatbot GUI.
     * <p>
     * Calls {@code launch()} to start the JavaFX application.
     * </p>
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        launch(Main.class, args);
    }
}
