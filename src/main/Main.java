package main;

import database.DBConnection;
import javafx.application.Application;
import javafx.stage.Stage;
import util.Language;
import util.SceneChange;
import util.Time;

public class Main extends Application {

    @Override
    public void init() {
        Language.init();
        Time.init();
        DBConnection.startConnection();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        SceneChange sc = new SceneChange(primaryStage, "../view/login.fxml");
        sc.changeScene(Language.getField("Stage Title"), 300, 300, 260, 260);
    }


    public static void main(String[] args) {
        launch(args);
        DBConnection.closeConnection();
    }
}
