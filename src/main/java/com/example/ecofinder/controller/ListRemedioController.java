package com.example.ecofinder.controller;

import com.example.ecofinder.services.ServicosUsuarios;
import javafx.fxml.FXML;
import javafx.scene.text.TextFlow;

public class ListRemedioController {

    @FXML
    private TextFlow txtFlowRemedio;

    public void initialize() {
        adicionarTexto();
    }

    public void adicionarTexto () {

        String login = LoginController.usuarioLogado;
        String texto = new ServicosUsuarios().imprimirListaRemedio(login);
        txtFlowRemedio.getChildren().clear();
        txtFlowRemedio.getChildren().add(new javafx.scene.text.Text(texto));

    }

}
