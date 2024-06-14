package com.example.ecofinder.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class MapController {

    @FXML
    private Button btnLista;

    @FXML
    public void initialize() {
        btnLista.setOnAction(event -> {
            handleLista();
        });
    }

    @FXML
    private void handleLista() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/ecofinder/view/list-view.fxml"));
            Parent root = loader.load();
            ListController listController = loader.getController();
            Stage stageLista = (Stage) btnLista.getScene().getWindow();
            Scene sceneLista = new Scene(root);
            stageLista.setScene(sceneLista);
            stageLista.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
