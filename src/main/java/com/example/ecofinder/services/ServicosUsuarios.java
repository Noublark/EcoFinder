package com.example.ecofinder.services;

import com.example.ecofinder.models.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class ServicosUsuarios {

    Connection conn ;
    PreparedStatement pstm; // usado para criar um objeto que representa a instrução SQL que será executada

    public void cadastrarUsuario (Usuario usuario) {
        String sql = "insert into usuario (login, senha) values (?, ?)";

        conn = new ConexaoSQLite().conexao();

        try {

            pstm = conn.prepareStatement(sql);
            pstm.setString(1, usuario.getLogin());
            pstm.setString(2, usuario.getSenha());

            pstm.execute();
            pstm.close();

        } catch (SQLException erro) {
            System.err.println("ServicosUsuario" + erro);
        }
    }

    public ResultSet autenticador (Usuario objUsuario) {
        conn = new ConexaoSQLite().conexao();

        try {
            String sql = "select * from usuario where login = ? and senha = ?";
            pstm = conn .prepareStatement(sql);
            pstm.setString(1, objUsuario.getLoginAutenticador());
            pstm.setString(2, objUsuario.getSenhaAutenticador());

            ResultSet rs = (ResultSet) pstm.executeQuery();
            return rs;

        } catch (SQLException erro) {
            System.err.println("ServicosUsuario: " + erro);
            return null;
        }
    }

    public boolean verificarLogin(String login) {
        conn = new ConexaoSQLite().conexao();

        try {
            String sql = "select count(*) as total from usuario where login = ?";
            pstm = conn.prepareStatement(sql);
            pstm.setString(1, login);

            ResultSet rs = pstm.executeQuery();
            if (rs.next()) {
                int total = rs.getInt("total");
                return total > 0;
            } else {
                return false;
            }

        } catch (SQLException erro) {
            System.err.println("Erro ao verificar login existente: " + erro);
            return false;
        } finally {
            fecharRecursos();
        }
    }

    public void redefinirSenha(Usuario objUsuario) {
        conn = new ConexaoSQLite().conexao();

        try {
            String sql = "update usuario set senha = ? where login = ?";
            pstm = conn.prepareStatement(sql);
            pstm.setString(1, objUsuario.getSenhaRedefinir());
            pstm.setString(2, objUsuario.getLoginRedefinir());

            pstm.executeUpdate();
        } catch (SQLException erro) {
            System.err.println("Erro ao redefinir senha: " + erro);
        } finally {
            fecharRecursos();
        }
    }

    public void adicionarListaPilha (String login,ListaPilha listaPilha) {
        conn = new ConexaoSQLite().conexao();

        try {
            String sql = "INSERT INTO pilha (login,localPilha, quantidadePilha, dataPilha) VALUES (?, ?, ?, ?)";
            pstm = conn.prepareStatement(sql);
            NoPilha atual = listaPilha.getHead();
            while (atual != null) {
                pstm.setString(1, login);
                pstm.setString(2, atual.getLocalPilha());
                pstm.setInt(3, atual.getQuantidadePilha());
                pstm.setDate(4, new java.sql.Date(atual.getDataPilha().getTime()));
                pstm.executeUpdate();
                atual = atual.getProximoPilha();
            }
            System.out.println("Item adicionado com sucesso ao banco de dados.");

        } catch (SQLException erro) {
            System.err.println("Erro ao adicionar a lista pilha: " + erro);
        } finally {
            fecharRecursos();
        }

    }

    public String imprimirListaPilha (String login) {
        conn = new ConexaoSQLite().conexao();
        ResultSet rs;
        StringBuilder lista = new StringBuilder();

        try {
            String sql = "SELECT localPilha, quantidadePilha, dataPilha FROM pilha WHERE login = ?"; // consulta os itens da lista com base no login
            pstm = conn.prepareStatement(sql);
            pstm.setString(1, login);
            rs = pstm.executeQuery();

            while (rs.next()) {
                String localPilha = rs.getString("localPilha");
                int quantidadePilha = rs.getInt("quantidadePilha");
                Date dataPilha = rs.getDate("dataPilha");

                lista.append("Local: ").append(localPilha).append(", Quantidade: ").append(quantidadePilha)
                        .append(", Data: ").append(dataPilha).append("\n");
            }
        } catch (SQLException erro) {
            System.err.println("Erro ao imprimir lista pilha: " + erro);
        } finally {
            fecharRecursos();
        }

        return lista.toString();
    }

    public void adicionarListaRemedio (String login, ListaRemedio listaRemedio) {
        conn = new ConexaoSQLite().conexao();

        try {
            String sql = "INSERT INTO remedio (login,localRemedio, quantidadeRemedio, dataRemedio) VALUES (?, ?, ?, ?)";
            pstm = conn.prepareStatement(sql);
            NoRemedio atual = listaRemedio.getHead();
            while (atual != null) {
                pstm.setString(1, login);
                pstm.setString(2, atual.getLocalRemedio());
                pstm.setInt(3, atual.getQuantidadeRemedio());
                pstm.setDate(4, new java.sql.Date(atual.getDataRemedio().getTime()));
                pstm.executeUpdate();
                atual = atual.getProximoRemedio();
            }
            System.out.println("Item adicionado com sucesso ao banco de dados.");

        } catch (SQLException erro) {
            System.err.println("Erro ao adicionar a lista pilha: " + erro);
        } finally {
            fecharRecursos();
        }

    }

    public String imprimirListaRemedio (String login) {
        conn = new ConexaoSQLite().conexao();
        ResultSet rs;
        StringBuilder lista = new StringBuilder();

        try {
            String sql = "SELECT localRemedio, quantidadeRemedio, dataRemedio FROM remedio WHERE login = ?";
            pstm = conn.prepareStatement(sql);
            pstm.setString(1, login);
            rs = pstm.executeQuery();

            while (rs.next()) {
                String localRemedio = rs.getString("localRemedio");
                int quantidadeRemedio = rs.getInt("quantidadeRemedio");
                Date dataRemedio = rs.getDate("dataRemedio");

                lista.append("Local: ").append(localRemedio).append(", Quantidade: ").append(quantidadeRemedio)
                        .append(", Data: ").append(dataRemedio).append("\n");
            }
        } catch (SQLException erro) {
            System.err.println("Erro ao imprimir lista remedio: " + erro);
        } finally {
            fecharRecursos();
        }

        return lista.toString();
    }

    public void fecharRecursos() { // serve para fechar o pstm
        try {
            if (pstm != null) {
                pstm.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
