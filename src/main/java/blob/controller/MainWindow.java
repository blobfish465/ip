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
     * Handles user input when the send button is clicked or when the user presses Enter.
     *
     * <p>Creates two dialog boxes:
     * <ul>
     *     <li>One displaying the user's input.</li>
     *     <li>One displaying the chatbot's response.</li>
     * </ul>
     * </p>
     *
     * <p>After processing, the user's input field is cleared.</p>
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = blob.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getBlobDialog(response, blobImage)
        );
        userInput.clear();
    }
}
