package com.example.ecofinder.controller;

import com.example.ecofinder.services.ServicosUsuarios;
import javafx.fxml.FXML;
import javafx.scene.text.TextFlow;

public class ListPilhaController {
    @FXML
    private TextFlow txtFlowPilha;

    public void initialize() {
        adicionarTexto();
    }

    public void adicionarTexto () {

        String login = LoginController.usuarioLogado;
        String texto = new ServicosUsuarios().imprimirListaPilha(login);

        txtFlowPilha.getChildren().clear();
        txtFlowPilha.getChildren().add(new javafx.scene.text.Text(texto));

    }




}
