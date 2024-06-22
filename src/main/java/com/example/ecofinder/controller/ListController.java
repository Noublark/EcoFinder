package com.example.ecofinder.controller;

import com.example.ecofinder.models.ListaPilha;
import com.example.ecofinder.models.ListaRemedio;
import com.example.ecofinder.services.ServicosUsuarios;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;

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
    private ImageView imageViewVoltar;
    @FXML
    private Label labelNumPilha;
    @FXML
    private Label labelNumRemedio;
    @FXML
    private Label labelRegistrarPilha;
    @FXML
    private Label labelRegistrarRemedio;

    @FXML
    public void initialize() {
        btnRegistrarPilha.setOnAction(event -> {
            handleRegistrarPilha();
        });

        for (String local : new String[]{"Salvador Shopping", "Atacadão - Salvador Bonocô" }) {
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

        for (String local : new String[]{"Drogasil - Salvador Shopping", "Drogaria São Paulo - Pituba" }) {
            MenuItem menuItem2 = new MenuItem(local);
            menuItem2.setOnAction(event -> menuBtnLocalRemedio.setText(menuItem2.getText()));
            menuBtnLocalRemedio.getItems().add(menuItem2);
        }
        menuBtnLocalRemedio.setText("Local");

        imageViewVoltar.setOnMouseClicked(mouseEvent -> {
            handleVoltar();
        });

        btnRegistrarPilha.setOnMouseEntered(event -> btnRegistrarPilha.setCursor(Cursor.HAND));
        btnRegistrarPilha.setOnMouseExited(event -> btnRegistrarPilha.setCursor(Cursor.DEFAULT));

        btnRegistrarRemedio.setOnMouseEntered(event -> btnRegistrarRemedio.setCursor(Cursor.HAND));
        btnRegistrarRemedio.setOnMouseExited(event -> btnRegistrarRemedio.setCursor(Cursor.DEFAULT));

        datePickerDataPilha.setOnMouseEntered(event -> datePickerDataPilha.setCursor(Cursor.HAND));
        datePickerDataPilha.setOnMouseExited(event -> datePickerDataPilha.setCursor(Cursor.DEFAULT));

        datePickerDataRemedio.setOnMouseEntered(event -> datePickerDataRemedio.setCursor(Cursor.HAND));
        datePickerDataRemedio.setOnMouseExited(event -> datePickerDataRemedio.setCursor(Cursor.DEFAULT));

        imageViewVoltar.setOnMouseEntered(event -> imageViewVoltar.setCursor(Cursor.HAND));
        imageViewVoltar.setOnMouseExited(event -> imageViewVoltar.setCursor(Cursor.DEFAULT));

    }

    @FXML
    private void handleRegistrarPilha() {
        try {
            if (menuBtnLocalPilha.getText().equals("Local")) {
                Alert alertErroLocalPilha = new Alert(Alert.AlertType.ERROR);
                Image icon = new Image(getClass().getResourceAsStream("/com/example/ecofinder/static/images/EcoFinderIcon.png"));
                alertErroLocalPilha.setTitle("Erro");
                alertErroLocalPilha.setHeaderText(null);
                alertErroLocalPilha.setContentText("Local não selecionado!");
                Stage stageErroLocalPilha = (Stage) alertErroLocalPilha.getDialogPane().getScene().getWindow();
                stageErroLocalPilha.getIcons().add(new Image(getClass().getResourceAsStream("/com/example/ecofinder/static/images/EcoFinderIcon.png")));
                alertErroLocalPilha.showAndWait();
                throw new NullPointerException("Local não selecionado!");
            }
            if (txtQntPilha.getText().isEmpty()) {
                labelNumPilha.setText("Digite um número válido!");
                throw new NullPointerException("Quantidade não preenchida");
            }
            if (datePickerDataPilha.getValue() == null) {
                Alert alertErroDataPilha = new Alert(Alert.AlertType.ERROR);
                Image icon = new Image(getClass().getResourceAsStream("/com/example/ecofinder/static/images/EcoFinderIcon.png"));
                alertErroDataPilha.setTitle("Erro");
                alertErroDataPilha.setHeaderText(null);
                alertErroDataPilha.setContentText("Data não selecionada!");
                Stage stageErroDataPilha = (Stage) alertErroDataPilha.getDialogPane().getScene().getWindow();
                stageErroDataPilha.getIcons().add(new Image(getClass().getResourceAsStream("/com/example/ecofinder/static/images/EcoFinderIcon.png")));
                alertErroDataPilha.showAndWait();
                throw new NullPointerException("Data não selecionada!");
            }

            labelNumPilha.setText("");

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
            labelRegistrarPilha.setText("Registrado!");

            Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(2), event -> {
                labelRegistrarPilha.setText("");
            }));
            timeline.setCycleCount(1);
            timeline.play();


        } catch (NumberFormatException e) {
            System.err.println("Erro.");
        } catch (NullPointerException e) {
            System.err.println("Erro: " + e.getMessage());
        }
    }

    @FXML
    private void handleListaPilha () throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/ecofinder/view/list-pilha-view.fxml"));
        Parent root = loader.load();
        ListPilhaController listPilhaController = loader.getController();
        Stage stageListaPilha = (Stage) linkListaPilha.getScene().getWindow();
        Scene sceneListaPilha = new Scene(root);
        stageListaPilha.setScene(sceneListaPilha);
        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        double centerX = screenBounds.getMinX() + (screenBounds.getWidth() / 2);
        double centerY = screenBounds.getMinY() + (screenBounds.getHeight() / 2);
        stageListaPilha.setX(centerX - (stageListaPilha.getWidth() / 2));
        stageListaPilha.setY(centerY - (stageListaPilha.getHeight() / 2));
        stageListaPilha.show();
    }

    @FXML
    private void handleRegistrarRemedio() {
        try {
            if (menuBtnLocalRemedio.getText().equals("Local")) {
                Alert alertErroLocalRemedio = new Alert(Alert.AlertType.ERROR);
                Image icon = new Image(getClass().getResourceAsStream("/com/example/ecofinder/static/images/EcoFinderIcon.png"));
                alertErroLocalRemedio.setTitle("Erro");
                alertErroLocalRemedio.setHeaderText(null);
                alertErroLocalRemedio.setContentText("Local não selecionado!");
                Stage stageErroLocalRemedio = (Stage) alertErroLocalRemedio.getDialogPane().getScene().getWindow();
                stageErroLocalRemedio.getIcons().add(new Image(getClass().getResourceAsStream("/com/example/ecofinder/static/images/EcoFinderIcon.png")));
                alertErroLocalRemedio.showAndWait();
                throw new NullPointerException("Local não selecionado");
            }
            if (txtQntRemedio.getText().isEmpty()) {
                labelNumRemedio.setText("Digite um número válido!");
                throw new NullPointerException("Quantidade não preenchida");
            }
            if (datePickerDataRemedio.getValue() == null) {
                Alert alertErroDataRemedio = new Alert(Alert.AlertType.ERROR);
                Image icon = new Image(getClass().getResourceAsStream("/com/example/ecofinder/static/images/EcoFinderIcon.png"));
                alertErroDataRemedio.setTitle("Erro");
                alertErroDataRemedio.setHeaderText(null);
                alertErroDataRemedio.setContentText("Data não selecionada!");
                Stage stageErroDataRemedio = (Stage) alertErroDataRemedio.getDialogPane().getScene().getWindow();
                stageErroDataRemedio.getIcons().add(new Image(getClass().getResourceAsStream("/com/example/ecofinder/static/images/EcoFinderIcon.png")));
                alertErroDataRemedio.showAndWait();
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
            labelRegistrarRemedio.setText("Registrado!");

            Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(2), event -> {
                labelRegistrarRemedio.setText("");
            }));
            timeline.setCycleCount(1);
            timeline.play();


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
        Stage stageListaRemedio = (Stage) linkListaRemedio.getScene().getWindow();
        Scene sceneListaRemedio = new Scene(root);
        stageListaRemedio.setScene(sceneListaRemedio);
        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        double centerX = screenBounds.getMinX() + (screenBounds.getWidth() / 2);
        double centerY = screenBounds.getMinY() + (screenBounds.getHeight() / 2);
        stageListaRemedio.setX(centerX - (stageListaRemedio.getWidth() / 2));
        stageListaRemedio.setY(centerY - (stageListaRemedio.getHeight() / 2));
        stageListaRemedio.show();
    }

    @FXML
    private void handleVoltar() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/ecofinder/view/map-view.fxml"));
            Parent root = loader.load();
            MapController mapController = loader.getController();
            Stage stageMapa = (Stage) imageViewVoltar.getScene().getWindow();
            Scene sceneMapa = new Scene(root);
            stageMapa.setScene(sceneMapa);
            /*Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
            double centerX = screenBounds.getMinX() + (screenBounds.getWidth() / 2);
            double centerY = screenBounds.getMinY() + (screenBounds.getHeight() / 2);
            stageMapa.setX(centerX - (stageMapa.getWidth() / 2));
            stageMapa.setY(centerY - (stageMapa.getHeight() / 2));*/
            stageMapa.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
