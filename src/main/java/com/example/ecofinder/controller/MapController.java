package com.example.ecofinder.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;

public class MapController {

    @FXML
    private Button btnLista;
    @FXML
    private WebView webViewMap;
    @FXML
    private ImageView imageViewSair;

    @FXML
    public void initialize() {
        loadMap();

        btnLista.setOnAction(event -> {
            handleLista();
        });


        imageViewSair.setOnMouseClicked(mouseEvent -> {
            handleSair();
        });

        btnLista.setOnMouseEntered(event -> btnLista.setCursor(Cursor.HAND));
        btnLista.setOnMouseExited(event -> btnLista.setCursor(Cursor.DEFAULT));

        imageViewSair.setOnMouseEntered(event -> imageViewSair.setCursor(Cursor.HAND));
        imageViewSair.setOnMouseExited(event -> imageViewSair.setCursor(Cursor.DEFAULT));
    }

    @FXML
    private void handleLista() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/ecofinder/view/list-view.fxml"));
            Parent root = loader.load();
            loader.getController();
            Stage stageLista = (Stage) btnLista.getScene().getWindow();
            Scene sceneLista = new Scene(root);
            stageLista.setScene(sceneLista);
            stageLista.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadMap() { // carrega um mapa em um webview a partir de um arquivo html
        WebEngine webEngine = webViewMap.getEngine();
        URL url = getClass().getResource("/com/example/ecofinder/view/map.html");
        if (url != null) {
            ((WebEngine) webEngine).load(((URL) url).toExternalForm());
        } else {
            System.out.println("Arquivo HTML não encontrado.");
        }
    }

    @FXML
    private void handleSair() {
        try {
            Alert confirmarSaida = new Alert(Alert.AlertType.CONFIRMATION); // exibe janela de confirmacao
            confirmarSaida.setTitle("Sair");
            confirmarSaida.setHeaderText(null);
            confirmarSaida.setContentText("Tem certeza que deseja sair?");
            confirmarSaida.setGraphic(null);
            Stage stageConfirmarSaida = (Stage) confirmarSaida.getDialogPane().getScene().getWindow();
            stageConfirmarSaida.getIcons().add(new Image(getClass().getResourceAsStream("/com/example/ecofinder/static/images/EcoFinderIcon.png")));
            Optional<ButtonType> escolha = confirmarSaida.showAndWait();
            if (escolha.isPresent() && escolha.get() == ButtonType.OK ){
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/ecofinder/view/login-view.fxml"));
                Parent root = loader.load();
                loader.getController();
                Stage stageLogin = (Stage) imageViewSair.getScene().getWindow();
                Scene sceneLogin = new Scene(root);
                stageLogin.setScene(sceneLogin);
                 //codigo abaixo centraliza janela
                Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
                double centerX = screenBounds.getMinX() + (screenBounds.getWidth() / 2);
                double centerY = screenBounds.getMinY() + (screenBounds.getHeight() / 2);
                stageLogin.setX(centerX - (stageLogin.getWidth() / 2));
                stageLogin.setY(centerY - (stageLogin.getHeight() / 2));
                stageLogin.show();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
