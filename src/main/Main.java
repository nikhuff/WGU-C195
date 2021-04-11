package main;

import database.DBConnection;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import util.Language;
import util.SceneChange;

public class Main extends Application {

    @Override
    public void init() {
        Language.init();
        DBConnection.startConnection();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setMinWidth(260);
        primaryStage.setMinHeight(260);
        SceneChange sc = new SceneChange(primaryStage, "../view/login.fxml");
        sc.changeScene(Language.getField("Stage Title"), 300, 300);
    }


    public static void main(String[] args) {
        launch(args);
        DBConnection.closeConnection();
    }
}
