package blob.controller;

import java.io.IOException;
import java.util.Collections;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

/**
 * Represents a dialog box consisting of an {@link ImageView} to represent the speaker's face
 * and a {@link Label} containing text from the speaker.
 * This class is used in the GUI to visually display interactions between the user and the chatbot.
 */
public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    /**
     * Constructs a {@code DialogBox} with the given text and image.
     * Loads the corresponding FXML layout file to initialize the GUI components.
     *
     * @param text The text message to be displayed in the dialog box.
     * @param img  The image representing the speaker.
     */
    private DialogBox(String text, Image img) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        dialog.setText(text);
        displayPicture.setImage(img);
    }

    /**
     * Flips the dialog box such that the {@code ImageView} is positioned on the left
     * and the text {@code Label} is on the right. This is used to differentiate between
     * user messages and chatbot blob responses.
     */
    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_LEFT);
    }

    /**
     * Creates a {@code DialogBox} for the user.
     * This is used to display the user's input messages in the chat.
     *
     * @param text The user's input message.
     * @param img  The image representing the user.
     * @return A new {@code DialogBox} displaying the user's message.
     */
    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox(text, img);
    }

    /**
     * Creates a {@code DialogBox} for the chatbot blob.
     * The dialog box is flipped to position the image on the left and the text on the right.
     *
     * @param text The chatbot's response message.
     * @param img  The image representing the chatbot blob.
     * @return A new {@code DialogBox} displaying the blob's message.
     */
    public static DialogBox getBlobDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.flip();
        return db;
    }
}