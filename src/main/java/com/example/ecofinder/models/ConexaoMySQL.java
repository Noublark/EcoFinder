package com.example.ecofinder.models;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class ConexaoMySQL {

    public Connection conexao () {
        Connection conn = null;

        try {
            String url = "jdbc:mysql://localhost:3306/bancoecofinder?user=root&password=";
            conn = DriverManager.getConnection(url);

        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Conexao" + erro.getMessage());
        }
        return conn;
    }

}
