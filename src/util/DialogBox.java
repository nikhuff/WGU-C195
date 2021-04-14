package util;

import javafx.scene.control.Alert;

public class DialogBox {

    public static void improperInput(String content) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Improper Input");
        alert.setHeaderText("Input Error");
        alert.setContentText(content);
    }

    public static void deleteConfirmation(String content) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Deletion Confirmation");
        alert.setHeaderText("Are you sure?");
        alert.setContentText("You are attempting to delete the record. Are you sure?");
    }
}
