package util;

import javafx.scene.control.TextField;

public class Validate {

    private static String invalid = "-fx-border-color: red;";

    public static int validateID(TextField field) throws InvalidInputException {
        int id;
        try {
            id = Integer.parseInt(field.getText());
        } catch (NumberFormatException e) {
            field.setStyle(invalid);
            throw new InvalidInputException("Invalid ID");
        }
        field.setStyle(null);
        return id;
    }
}
