package com.example.ecofinder.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import java.io.IOException;

public class App extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("login-view.fxml"));
        Image icon = new Image(getClass().getResourceAsStream("/com/example/ecofinder/static/images/EcoFinderIcon.png"));
        Scene sceneLogin = new Scene(fxmlLoader.load(), 700, 500);
        primaryStage.getIcons().add(icon);
        primaryStage.setTitle("EcoFinder");
        primaryStage.setScene(sceneLogin);
        primaryStage.show();
    }
}

