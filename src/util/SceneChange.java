package util;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class SceneChange {

    private String viewUrl;
    private Stage stage;

    public SceneChange(Stage stage, String url) {
        this.stage = stage;
        this.viewUrl = url;
    }

    public void changeScene(String title, int height, int width) {
        Parent root = null;
        try {
            root = new FXMLLoader(getClass().getResource(viewUrl)).load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene scene = new Scene(root, height, width);
        scene.getStylesheets().add("styles/styles.css");
        stage.setTitle(title);
        stage.setScene(scene);
        stage.show();
    }
}