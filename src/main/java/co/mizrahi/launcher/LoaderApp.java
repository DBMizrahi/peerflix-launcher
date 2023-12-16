package co.mizrahi.launcher;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class LoaderApp extends Application {

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(LoaderApp.class.getResource("loader-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 640, 100);
        stage.setTitle("Peerflix launcher");
        stage.setScene(scene);
        stage.show();
    }
}
