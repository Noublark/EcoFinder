package com.example.ecofinder.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class MapController {

    @FXML
    private Button btnLista;
    @FXML
    private WebView webViewMap;

    @FXML
    public void initialize() {
        loadMap();

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

    private void loadMap() {
        WebEngine webEngine = webViewMap.getEngine();
        URL url = getClass().getResource("/com/example/ecofinder/view/map.html");
        if (url != null) {
            ((WebEngine) webEngine).load(((URL) url).toExternalForm());
        } else {
            System.out.println("Arquivo HTML não encontrado.");
        }
    }
}
