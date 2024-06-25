package com.example.ecofinder.controller;

import com.example.ecofinder.models.Usuario;
import com.example.ecofinder.services.ServicosUsuarios;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Cursor;
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
    public static String usuarioLogado; // variavel global que armazena o login do usuário autenticado

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
        btnCadastro.setOnAction(event -> handleCadastro());
        linkSenha.setOnAction(event -> handleEsqueceu());

        btnLogin.setOnMouseEntered(event -> btnLogin.setCursor(Cursor.HAND)); // evento para mudar o cursor qnd o mouse passar por cima
        btnLogin.setOnMouseExited(event -> btnLogin.setCursor(Cursor.DEFAULT)); // evento para voltar o cursor pro padrao qnd retirar o mouse

        btnCadastro.setOnMouseEntered(event -> btnCadastro.setCursor(Cursor.HAND));
        btnCadastro.setOnMouseExited(event -> btnCadastro.setCursor(Cursor.DEFAULT));

    }

    @FXML
    private void handleLogin() throws SQLException {
        String login = txtLogin.getText();
        objUsuario.setLoginAutenticador(login);
        String senha = txtSenha.getText();
        objUsuario.setSenhaAutenticador(senha);

        ResultSet rsusuario = (ResultSet) servicos.autenticador(objUsuario);

        if (rsusuario.next()) { // verifica se há pelo menos uma linha no ResulSet, retorna true ou false
            try {
                usuarioLogado = rsusuario.getString("login");
                System.out.println("Usuário logado: " + usuarioLogado);
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/ecofinder/view/map-view.fxml"));
                Parent root = loader.load();
                loader.getController();
                Stage stageMapa = (Stage) btnLogin.getScene().getWindow();
                Scene sceneMapa = new Scene(root);
                stageMapa.setScene(sceneMapa);
                Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds(); // centralizar a janela
                double centerX = screenBounds.getMinX() + (screenBounds.getWidth() / 2); // centralizar a janela
                double centerY = screenBounds.getMinY() + (screenBounds.getHeight() / 2); // centralizar a janela
                stageMapa.setX(centerX - (stageMapa.getWidth() / 2)); // centralizar a janela
                stageMapa.setY(centerY - (stageMapa.getHeight() / 2)); // centralizar a janela
                stageMapa.show();
            } catch (IOException e) {
                e.printStackTrace();
            }  finally {
                servicos.fecharRecursos();
            }
        } else {
            Alert alertErroAutenticacao = new Alert(Alert.AlertType.ERROR); // exibe uma janela de erro
            alertErroAutenticacao.setTitle("Erro");
            alertErroAutenticacao.setHeaderText(null);
            alertErroAutenticacao.setContentText("Nome de usuário ou senha inválidos!");
            Stage stageErroAutenticacao = (Stage) alertErroAutenticacao.getDialogPane().getScene().getWindow();
            stageErroAutenticacao.getIcons().add(new Image(getClass().getResourceAsStream("/com/example/ecofinder/static/images/EcoFinderIcon.png")));
            alertErroAutenticacao.showAndWait();
            servicos.fecharRecursos();
        }

    }

    @FXML
    public void handleCadastro() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/ecofinder/view/register-view.fxml"));
            Parent root = loader.load();
            loader.getController();
            Stage stageCadastro = (Stage) btnCadastro.getScene().getWindow();
            Scene sceneCadastro = new Scene(root);
            stageCadastro.setScene(sceneCadastro);
            stageCadastro.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void handleEsqueceu() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/ecofinder/view/reset-password-view.fxml"));
            Parent root = loader.load();
            loader.getController();
            Stage stageEsqueceuSenha = (Stage) linkSenha.getScene().getWindow();
            Scene sceneEsqueceuSenha = new Scene(root);
            stageEsqueceuSenha.setScene(sceneEsqueceuSenha);
            stageEsqueceuSenha.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}


