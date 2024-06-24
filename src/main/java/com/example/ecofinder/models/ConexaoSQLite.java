package com.example.ecofinder.models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class ConexaoSQLite {

    public Connection conexao () {
        Connection conn = null;

        try {
            String url = "jdbc:sqlite:src/main/resources/com/example/ecofinder/db/bancoecofinder.db";
            conn = DriverManager.getConnection(url);

        } catch (SQLException erro) {
            System.err.println("Conexao" + erro.getMessage());
        }
        return conn;
    }

}
