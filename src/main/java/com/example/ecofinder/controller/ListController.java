package com.example.ecofinder.controller;

import com.example.ecofinder.models.ListaPilha;
import com.example.ecofinder.models.ListaRemedio;
import com.example.ecofinder.services.ServicosUsuarios;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class ListController {

    ListaPilha listaPilha = new ListaPilha();
    ListaRemedio listaRemedio = new ListaRemedio();

    @FXML
    private MenuButton menuBtnLocalPilha;
    @FXML
    private TextField txtQntPilha;
    @FXML
    private DatePicker datePickerDataPilha;
    @FXML
    private Button btnRegistrarPilha;
    @FXML
    private Hyperlink linkListaPilha;
    @FXML
    private MenuButton menuBtnLocalRemedio;
    @FXML
    private TextField txtQntRemedio;
    @FXML
    private DatePicker datePickerDataRemedio;
    @FXML
    private Button btnRegistrarRemedio;
    @FXML
    private Hyperlink linkListaRemedio;

    @FXML
    public void initialize() {
        btnRegistrarPilha.setOnAction(event -> {
            handleRegistrarPilha();
        });

        for (String local : new String[]{"Salvador Shopping", "Pernambruxa" }) {
            MenuItem menuItem = new MenuItem(local);
            menuItem.setOnAction(event -> menuBtnLocalPilha.setText(menuItem.getText()));
            menuBtnLocalPilha.getItems().add(menuItem);
        }
        menuBtnLocalPilha.setText("Local");

        linkListaPilha.setOnAction(event -> {
            try {
                handleListaPilha();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        btnRegistrarRemedio.setOnAction(event -> {
            handleRegistrarRemedio();
        });

        for (String local : new String[]{"Unijorge", "Casa do cabrunco" }) {
            MenuItem menuItem2 = new MenuItem(local);
            menuItem2.setOnAction(event -> menuBtnLocalRemedio.setText(menuItem2.getText()));
            menuBtnLocalRemedio.getItems().add(menuItem2);
        }
        menuBtnLocalRemedio.setText("Local");
    }

    @FXML
    private void handleRegistrarPilha() {
        try {
            if (menuBtnLocalPilha.getText().equals("Local")) {
                throw new NullPointerException("Local não selecionado");
            }
            if (txtQntPilha.getText().isEmpty()) {
                throw new NullPointerException("Quantidade não preenchida");
            }
            if (datePickerDataPilha.getValue() == null) {
                throw new NullPointerException("Data não selecionada");
            }

            String localPilha = menuBtnLocalPilha.getText();
            int quantidadePilha = Integer.parseInt(txtQntPilha.getText());
            LocalDate localDatePilha = datePickerDataPilha.getValue();
            Date dataPilha = Date.from(localDatePilha.atStartOfDay(ZoneId.systemDefault()).toInstant());

            listaPilha.add(localPilha, quantidadePilha, dataPilha);
            String login = LoginController.usuarioLogado;
            new ServicosUsuarios().adicionarListaPilha(login, listaPilha);
            menuBtnLocalPilha.setText("Local");
            txtQntPilha.clear();
            datePickerDataPilha.setValue(null);

            System.out.println("Registro adicionado: Local - " + localPilha + ", Quantidade - " + quantidadePilha + ", Data - " + dataPilha);

        } catch (NumberFormatException e) {
            System.err.println("Erro: Quantidade deve ser um número inteiro.");
        } catch (NullPointerException e) {
            System.err.println("Erro: " + e.getMessage());
        }
    }

    @FXML
    private void handleListaPilha () throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/ecofinder/view/list-pilha-view.fxml"));
        Parent root = loader.load();
        ListPilhaController listPilhaController = loader.getController();
        Stage stage = (Stage) linkListaPilha.getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        double centerX = screenBounds.getMinX() + (screenBounds.getWidth() / 2);
        double centerY = screenBounds.getMinY() + (screenBounds.getHeight() / 2);
        stage.setX(centerX - (stage.getWidth() / 2));
        stage.setY(centerY - (stage.getHeight() / 2));
        stage.show();
    }

    @FXML
    private void handleRegistrarRemedio() {
        try {
            if (menuBtnLocalRemedio.getText().equals("Local")) {
                throw new NullPointerException("Local não selecionado");
            }
            if (txtQntRemedio.getText().isEmpty()) {
                throw new NullPointerException("Quantidade não preenchida");
            }
            if (datePickerDataRemedio.getValue() == null) {
                throw new NullPointerException("Data não selecionada");
            }

            String localRemedio = menuBtnLocalRemedio.getText();
            int quantidadeRemedio = Integer.parseInt(txtQntRemedio.getText());
            LocalDate localDateRemedio = datePickerDataRemedio.getValue();
            Date dataRemedio = Date.from(localDateRemedio.atStartOfDay(ZoneId.systemDefault()).toInstant());

            listaRemedio.add(localRemedio, quantidadeRemedio, dataRemedio);
            String login = LoginController.usuarioLogado;
            new ServicosUsuarios().adicionarListaRemedio(login, listaRemedio);
            menuBtnLocalRemedio.setText("Local");
            txtQntPilha.clear();
            datePickerDataPilha.setValue(null);

            System.out.println("Registro adicionado: Local - " + localRemedio + ", Quantidade - " + quantidadeRemedio + ", Data - " + dataRemedio);

        } catch (NumberFormatException e) {
            System.err.println("Erro: Quantidade deve ser um número inteiro.");
        } catch (NullPointerException e) {
            System.err.println("Erro: " + e.getMessage());
        }
    }

    @FXML
    private void handleListaRemedio () throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/ecofinder/view/list-remedio-view.fxml"));
        Parent root = loader.load();
        ListRemedioController listRemedioController = loader.getController();
        Stage stage = (Stage) linkListaRemedio.getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        double centerX = screenBounds.getMinX() + (screenBounds.getWidth() / 2);
        double centerY = screenBounds.getMinY() + (screenBounds.getHeight() / 2);
        stage.setX(centerX - (stage.getWidth() / 2));
        stage.setY(centerY - (stage.getHeight() / 2));
        stage.show();
    }
}
