package com.example.ecofinder.models;

import java.util.Date;

public class NoRemedio {
    private String localRemedio;
    private int quantidadeRemedio;
    private Date dataRemedio;
    private NoRemedio proximoRemedio = null;

    public NoRemedio(String localRemedio, int quantidadeRemedio, Date dataRemedio) {
        this.localRemedio = localRemedio;
        this.quantidadeRemedio = quantidadeRemedio;
        this.dataRemedio = dataRemedio;
    } // Construtor da classe NoRemedio, inicializa os atributos com os valores fornecidos

    public String getLocalRemedio() {
        return localRemedio;
    }

    public void setLocalRemedio(String local) {
        this.localRemedio = local;
    }

    public int getQuantidadeRemedio() {
        return quantidadeRemedio;
    }

    public void setQuantidadeRemedio(int quantidadeRemedio) {
        this.quantidadeRemedio = quantidadeRemedio;
    }

    public Date getDataRemedio() {
        return dataRemedio;
    }

    public void setDataRemedio(Date dataRemedio) {
        this.dataRemedio = dataRemedio;
    }

    public NoRemedio getProximoRemedio() {
        return proximoRemedio;
    }

    public void setProximoRemedio(NoRemedio proximoRemedio) {
        this.proximoRemedio = proximoRemedio;
    }



}
