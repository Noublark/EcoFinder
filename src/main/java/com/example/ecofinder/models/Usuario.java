package com.example.ecofinder.models;

public class Usuario {
    private String login, senha, loginAutenticador, senhaAutenticador, loginRedefinir, senhaRedefinir;

    public String getLogin() {
        return login; // getter que retorna o valor da String login
    }

    public void setLogin(String login) {
        this.login = login; // setter que atribui o valor da String login
    }

    public String getSenha() {
        return senha; // getter que retorna o valor da String senha
    }

    public void setSenha(String senha) {
        this.senha = senha; // setter que atribui o valor da String senha
    }

    public String getLoginAutenticador() {
        return  loginAutenticador;
    }

    public void setLoginAutenticador(String loginAutenticador) {
        this.loginAutenticador = loginAutenticador;
    }

    public String getSenhaAutenticador() {
        return  senhaAutenticador;
    }

    public void setSenhaAutenticador(String senhaAutenticador) {
        this.senhaAutenticador = senhaAutenticador;
    }

    public String getLoginRedefinir() {
        return  loginRedefinir;
    }

    public void setLoginRedefinir(String loginRedefinir) {
        this.loginRedefinir = loginRedefinir;
    }

    public String getSenhaRedefinir() {
        return  senhaRedefinir;
    }

    public void setSenhaRedefinir(String senhaRedefinir) {
        this.senhaRedefinir = senhaRedefinir;
    }

}
