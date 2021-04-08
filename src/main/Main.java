package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import util.Language;

public class Main extends Application {

    @Override
    public void init() {
        Language.init();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/login.fxml"));
        loader.setController(new controller.Login());
        Parent root = loader.load();
        primaryStage.setTitle(Language.getField("Stage Title"));
        primaryStage.setMinWidth(260);
        primaryStage.setMinHeight(260);
        Scene scene = new Scene(root, 300, 275);
        scene.getStylesheets().add("styles/styles.css");
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
