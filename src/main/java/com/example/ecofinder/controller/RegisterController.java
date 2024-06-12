package com.example.ecofinder.controller;

import com.example.ecofinder.models.Usuario;
import com.example.ecofinder.services.ServicosUsuarios;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
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
    public void initialize() {
        btnCadastrar.setOnAction(event -> {
            handleCadastrar();
        });
    }

    @FXML
    private void handleCadastrar() {
        String loginCadastro = txtLoginCadastro.getText();
        objUsuario.setLogin(loginCadastro);
        if (servicos.verificarLogin(loginCadastro)) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            Image icon = new Image(getClass().getResourceAsStream("/com/example/ecofinder/static/images/EcoFinderIcon.png"));
            alert.setTitle("Erro");
            alert.setHeaderText(null);
            alert.setContentText("Nome de usuário já está em uso!");
            Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
            stage.getIcons().add(new Image(getClass().getResourceAsStream("/com/example/ecofinder/static/images/EcoFinderIcon.png")));
            alert.showAndWait();
            return;
        }
        String senhaCadastro = txtSenhaCadastro.getText();
        String senhaCadastro2 = txtSenhaCadastro2.getText();
        if (senhaCadastro.equals((senhaCadastro2))) {
            objUsuario.setSenha(senhaCadastro);
            servicos.cadastrarUsuario(objUsuario);
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/ecofinder/view/register-confirm-view.fxml"));
                Parent root = loader.load();
                RegisterConfirmController registerConfirmController = loader.getController();
                Stage stage = (Stage) btnCadastrar.getScene().getWindow();
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
