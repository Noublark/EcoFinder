package com.example.ecofinder.controller;

import com.example.ecofinder.models.Usuario;
import com.example.ecofinder.services.ServicosUsuarios;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
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
    public void initialize() {
        btnRedefinir.setOnAction(event -> {
            handleRedefinir();
        });
    }

    @FXML
    private void handleRedefinir() {
        String loginRedefinir = txtLoginRedefinir.getText();
        objUsuario.setLoginRedefinir(loginRedefinir);
        String senhaRedefinir = txtSenhaRedefinir.getText();
        String senhaRedefinir2 = txtSenhaRedefinir2.getText();
        if (senhaRedefinir.equals((senhaRedefinir2))) {
            objUsuario.setSenhaRedefinir(senhaRedefinir);
            servicos.redefinirSenha(objUsuario);
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/ecofinder/view/reset-password-confirm-view.fxml"));
                Parent root = loader.load();
                ResetPasswordConfirmController resetPasswordConfirmController = loader.getController();
                Stage stage = (Stage) btnRedefinir.getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            Image icon = new Image(getClass().getResourceAsStream("/com/example/ecofinder/static/images/EcoFinderIcon.png"));
            alert.setTitle("Erro");
            alert.setHeaderText(null);
            alert.setContentText("As senhas não são iguais!");
            Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
            stage.getIcons().add(new Image(getClass().getResourceAsStream("/com/example/ecofinder/static/images/EcoFinderIcon.png")));
            alert.showAndWait();
        }
    }

}