package com.example.ecofinder.controller;

import com.example.ecofinder.models.Usuario;
import com.example.ecofinder.services.ServicosUsuarios;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;

public class ResetPasswordController {

    Usuario objUsuario = new Usuario();
    ServicosUsuarios servicos = new ServicosUsuarios();

    @FXML
    private TextField txtLoginRedefinir;
    @FXML
    private TextField txtSenhaRedefinir;
    @FXML
    private TextField txtSenhaRedefinir2;
    @FXML
    private Button btnRedefinir;
    @FXML
    private ImageView imageViewVoltar;

    @FXML
    public void initialize() {
        btnRedefinir.setOnAction(event -> {
            handleRedefinir();
        });

        imageViewVoltar.setOnMouseClicked(mouseEvent -> {
            handleVoltar();
        });

        btnRedefinir.setOnMouseEntered(event -> btnRedefinir.setCursor(Cursor.HAND));
        btnRedefinir.setOnMouseExited(event -> btnRedefinir.setCursor(Cursor.DEFAULT));

        imageViewVoltar.setOnMouseEntered(event -> imageViewVoltar.setCursor(Cursor.HAND));
        imageViewVoltar.setOnMouseExited(event -> imageViewVoltar.setCursor(Cursor.DEFAULT));

    }

    @FXML
    private void handleRedefinir() {
        String loginRedefinir = txtLoginRedefinir.getText();
        objUsuario.setLoginRedefinir(loginRedefinir);
        String senhaRedefinir = txtSenhaRedefinir.getText();
        String senhaRedefinir2 = txtSenhaRedefinir2.getText();
        if (senhaRedefinir.equals((senhaRedefinir2))) { // verifica se as duas senhas sao iguais, caso seja, redefine a senha
            objUsuario.setSenhaRedefinir(senhaRedefinir);
            servicos.redefinirSenha(objUsuario);
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/ecofinder/view/reset-password-confirm-view.fxml"));
                Parent root = loader.load();
                ResetPasswordConfirmController resetPasswordConfirmController = loader.getController();
                Stage stageMudarSenhaConfirmado = (Stage) btnRedefinir.getScene().getWindow();
                Scene sceneMudarSenhaConfirmado = new Scene(root);
                stageMudarSenhaConfirmado.setScene(sceneMudarSenhaConfirmado);
                stageMudarSenhaConfirmado.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            Alert alertSenhasDiferentes = new Alert(Alert.AlertType.ERROR);
            Image icon = new Image(getClass().getResourceAsStream("/com/example/ecofinder/static/images/EcoFinderIcon.png"));
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
            LoginController loginController = loader.getController();
            Stage stageLogin = (Stage) imageViewVoltar.getScene().getWindow();
            Scene sceneLogin = new Scene(root);
            stageLogin.setScene(sceneLogin);
            stageLogin.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
