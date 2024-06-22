package com.example.ecofinder.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class RegisterConfirmController {

    @FXML
    private Button btnOK;

    @FXML
    public void initialize() {
        btnOK.setOnAction(event -> {
            handleOK();
        });

        btnOK.setOnMouseEntered(event -> btnOK.setCursor(Cursor.HAND));
        btnOK.setOnMouseExited(event -> btnOK.setCursor(Cursor.DEFAULT));

    }

    @FXML
    private void handleOK() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/ecofinder/view/login-view.fxml"));
            Parent root = loader.load();
            LoginController loginController = loader.getController();
            Stage stageLogin = (Stage) btnOK.getScene().getWindow();
            Scene sceneLogin = new Scene(root);
            stageLogin.setScene(sceneLogin);
            stageLogin.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
