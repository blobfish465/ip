package blob.controller;

import blob.Blob;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * Controller for the main graphical user interface (GUI) of the Blob chatbot application.
 * This class manages user interactions, input handling, and displaying chatbot responses.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Blob blob;

    // Images representing the user and the chatbot in the GUI.
    private Image userImage = new Image(getClass().getResourceAsStream("/images/DaUser.png"));
    private Image blobImage = new Image(getClass().getResourceAsStream("/images/blobfish.png"));


    /**
     * Initializes the GUI components.
     * This method ensures that the scroll pane automatically scrolls down
     * when new messages are added to the dialog container.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Sets the chatbot instance for this GUI controller.
     *
     * @param b The {@code Blob} instance representing the chatbot logic.
     */
    public void setBlob(Blob b) {
        blob = b;
    }

    /**
     * Creates and displays a user dialog box with the given input.
     * This method takes the user's input and displays it on the UI using a custom DialogBox.
     *
     * @param input The text input provided by the user which will be displayed in the dialog box.
     */
    private void createUserDialog(String input) {
        dialogContainer.getChildren().add(DialogBox.getUserDialog(input, userImage));
    }

    /**
     * Creates and displays a Blob dialog box with the given response.
     * This method takes the Blob's response to the user's input and displays it on the UI using a custom DialogBox.
     *
     * @param response The response from the Blob which will be displayed in the dialog box.
     */
    private void createBlobDialog(String response) {
        dialogContainer.getChildren().add(DialogBox.getBlobDialog(response, blobImage));
    }

    /**
     * Handles the process of capturing user input, processing it through the Blob, and displaying both the input
     * and response in the dialog boxes.
     * This method retrieves the user's input from a text field, sends it to the Blob for processing,
     * and then displays the user's input and Blob's response in the UI. It clears the input field after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = blob.getResponse(input);
        createUserDialog(input);
        createBlobDialog(response);
        userInput.clear();
    }
}
