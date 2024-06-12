package com.example.ecofinder.controller;

import com.example.ecofinder.models.Usuario;
import com.example.ecofinder.services.ServicosUsuarios;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginController {

    Usuario objUsuario = new Usuario();
    ServicosUsuarios servicos = new ServicosUsuarios();
    public static String usuarioLogado;

    @FXML
    private TextField txtLogin;
    @FXML
    private TextField txtSenha;
    @FXML
    private Button btnLogin;
    @FXML
    private Button btnCadastro;
    @FXML
    private Hyperlink linkSenha;

    @FXML
    public void initialize() {
        btnLogin.setOnAction(event -> {
            try {
                handleLogin();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
        btnCadastro.setOnAction(event -> {
            handleCadastro();
        });
        linkSenha.setOnAction(event -> {
            handleEsqueceu();
        });
    }

    @FXML
    private void handleLogin() throws SQLException {
        String login = txtLogin.getText();
        objUsuario.setLoginAutenticador(login);
        String senha = txtSenha.getText();
        objUsuario.setSenhaAutenticador(senha);

        ResultSet rsusuario = (ResultSet) servicos.autenticador(objUsuario);

        if (rsusuario.next()) {
            try {
                usuarioLogado = rsusuario.getString("login");
                System.out.println(usuarioLogado);
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/ecofinder/view/map-view.fxml"));
                Parent root = loader.load();
                MapController mapController = loader.getController();
                Stage stage = (Stage) btnLogin.getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
                double centerX = screenBounds.getMinX() + (screenBounds.getWidth() / 2);
                double centerY = screenBounds.getMinY() + (screenBounds.getHeight() / 2);
                stage.setX(centerX - (stage.getWidth() / 2));
                stage.setY(centerY - (stage.getHeight() / 2));
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            Image icon = new Image(getClass().getResourceAsStream("/com/example/ecofinder/static/images/EcoFinderIcon.png"));
            alert.setTitle("Erro");
            alert.setHeaderText(null);
            alert.setContentText("Nome de usuário ou senha inválidos!");
            Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
            stage.getIcons().add(new Image(getClass().getResourceAsStream("/com/example/ecofinder/static/images/EcoFinderIcon.png")));
            alert.showAndWait();
        }

    }

    @FXML
    public void handleCadastro() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/ecofinder/view/register-view.fxml"));
            Parent root = loader.load();
            RegisterController registerController = loader.getController();
            Stage stage = (Stage) btnCadastro.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void handleEsqueceu() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/ecofinder/view/reset-password-view.fxml"));
            Parent root = loader.load();
            ResetPasswordController resetPasswordController = loader.getController();
            Stage stage = (Stage) linkSenha.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}


