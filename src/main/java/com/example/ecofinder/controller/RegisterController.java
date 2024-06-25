package com.example.ecofinder.controller;

import com.example.ecofinder.models.Usuario;
import com.example.ecofinder.services.ServicosUsuarios;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import java.io.IOException;


public class RegisterController {

    Usuario objUsuario = new Usuario();
    ServicosUsuarios servicos = new ServicosUsuarios();

    @FXML
    private TextField txtLoginCadastro;
    @FXML
    private TextField txtSenhaCadastro;
    @FXML
    private TextField txtSenhaCadastro2;
    @FXML
    private Button btnCadastrar;
    @FXML
    private ImageView imageViewVoltar;

    @FXML
    public void initialize() {
        btnCadastrar.setOnAction(event -> {
            handleCadastrar();
        });

        imageViewVoltar.setOnMouseClicked(mouseEvent -> {
            handleVoltar();
        });

        btnCadastrar.setOnMouseEntered(event -> btnCadastrar.setCursor(Cursor.HAND));
        btnCadastrar.setOnMouseExited(event -> btnCadastrar.setCursor(Cursor.DEFAULT));

        imageViewVoltar.setOnMouseEntered(event -> imageViewVoltar.setCursor(Cursor.HAND));
        imageViewVoltar.setOnMouseExited(event -> imageViewVoltar.setCursor(Cursor.DEFAULT));

    }

    @FXML
    private void handleCadastrar() {
        String loginCadastro = txtLoginCadastro.getText();
        objUsuario.setLogin(loginCadastro);
        if (servicos.verificarLogin(loginCadastro)) {
            Alert alertUsuarioEmUso = new Alert(Alert.AlertType.ERROR); // exibe alerta dizendo que o nome de usuario ja esta sendo utilizado
            alertUsuarioEmUso.setTitle("Erro");
            alertUsuarioEmUso.setHeaderText(null);
            alertUsuarioEmUso.setContentText("Nome de usuário já está em uso!");
            Stage stageUsuarioEmUso = (Stage) alertUsuarioEmUso.getDialogPane().getScene().getWindow();
            stageUsuarioEmUso.getIcons().add(new Image(getClass().getResourceAsStream("/com/example/ecofinder/static/images/EcoFinderIcon.png")));
            alertUsuarioEmUso.showAndWait();
            return;
        }
        String senhaCadastro = txtSenhaCadastro.getText();
        String senhaCadastro2 = txtSenhaCadastro2.getText();
        if (senhaCadastro.equals((senhaCadastro2))) { // verifica se as duas senhas sao iguais, caso seja, setta senha e cadastra usuario
            objUsuario.setSenha(senhaCadastro);
            servicos.cadastrarUsuario(objUsuario);
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/ecofinder/view/register-confirm-view.fxml"));
                Parent root = loader.load();
                RegisterConfirmController registerConfirmController = loader.getController();
                Stage stageCadastroConfirmado = (Stage) btnCadastrar.getScene().getWindow();
                Scene sceneCadastroConfirmado = new Scene(root);
                stageCadastroConfirmado.setScene(sceneCadastroConfirmado);
                stageCadastroConfirmado.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            Alert alertSenhasDiferentes = new Alert(Alert.AlertType.ERROR);
            alertSenhasDiferentes.setTitle("Erro");
            alertSenhasDiferentes.setHeaderText(null);
            alertSenhasDiferentes.setContentText("As senhas não são iguais!");
            Stage stageSenhasDiferentes = (Stage) alertSenhasDiferentes.getDialogPane().getScene().getWindow();
            stageSenhasDiferentes.getIcons().add(new Image(getClass().getResourceAsStream("/com/example/ecofinder/static/images/EcoFinderIcon.png")));
            alertSenhasDiferentes.showAndWait();
        }

    }

    @FXML
    public void handleVoltar() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/ecofinder/view/login-view.fxml"));
            Parent root = loader.load();
            loader.getController();
            Stage stageLogin = (Stage) imageViewVoltar.getScene().getWindow();
            Scene sceneLogin = new Scene(root);
            stageLogin.setScene(sceneLogin);
            stageLogin.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
