module com.example.ecofinder {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;
    requires java.sql;
    requires java.desktop;

    opens com.example.ecofinder.controller to javafx.fxml;
    exports com.example.ecofinder.controller;
    exports com.example.ecofinder.view;
    opens com.example.ecofinder.view to javafx.fxml;
}