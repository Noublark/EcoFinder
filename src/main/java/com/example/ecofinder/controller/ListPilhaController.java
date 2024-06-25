package com.example.ecofinder.controller;

import com.example.ecofinder.services.ServicosUsuarios;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

import java.io.IOException;

public class ListPilhaController {
    @FXML
    private TextFlow txtFlowPilha;
    @FXML
    private ImageView imageViewVoltar;

    public void initialize() {
        adicionarTexto();

        imageViewVoltar.setOnMouseClicked(mouseEvent -> handleVoltar());

        imageViewVoltar.setOnMouseEntered(event -> imageViewVoltar.setCursor(Cursor.HAND));
        imageViewVoltar.setOnMouseExited(event -> imageViewVoltar.setCursor(Cursor.DEFAULT));

    }

    public void adicionarTexto () {

        String login = LoginController.usuarioLogado;
        String texto = new ServicosUsuarios().imprimirListaPilha(login);

        txtFlowPilha.getChildren().clear();
        txtFlowPilha.getChildren().add(new javafx.scene.text.Text(texto)); // adiciona o texto ao TextFlow

    }

    @FXML
    public void handleVoltar() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/ecofinder/view/list-view.fxml"));
            Parent root = loader.load();
            loader.getController();
            Stage stageLista = (Stage) imageViewVoltar.getScene().getWindow();
            Scene sceneLista = new Scene(root);
            stageLista.setScene(sceneLista);
            stageLista.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
