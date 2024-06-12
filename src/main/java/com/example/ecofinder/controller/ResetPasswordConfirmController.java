package com.example.ecofinder.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class ResetPasswordConfirmController {

    @FXML
    private Button btnOKRedefinir;

    @FXML
    public void initialize() {
        btnOKRedefinir.setOnAction(event -> {
            handleOKRedefinir();
        });
    }

    @FXML
    private void handleOKRedefinir() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/ecofinder/view/login-view.fxml"));
            Parent root = loader.load();
            LoginController loginController = loader.getController();
            Stage stage = (Stage) btnOKRedefinir.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}