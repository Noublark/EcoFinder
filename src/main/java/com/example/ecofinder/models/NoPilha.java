package com.example.ecofinder.models;

import java.util.Date;

public class NoPilha {
    private String localPilha;
    private int quantidadePilha;
    private Date dataPilha;
    private NoPilha proximoPilha = null;

    public NoPilha(String localPilha, int quantidadePilha, Date dataPilha) {
        this.localPilha = localPilha;
        this.quantidadePilha = quantidadePilha;
        this.dataPilha = dataPilha;
    }

    public String getLocalPilha() {
        return localPilha;
    }

    public void setLocalPilha(String local) {
        this.localPilha = local;
    }

    public int getQuantidadePilha() {
        return quantidadePilha;
    }

    public void setQuantidadePilha(int quantidadePilha) {
        this.quantidadePilha = quantidadePilha;
    }

    public Date getDataPilha() {
        return dataPilha;
    }

    public void setDataPilha(Date dataPilha) {
        this.dataPilha = dataPilha;
    }

    public NoPilha getProximoPilha() {
        return proximoPilha;
    }

    public void setProximoPilha(NoPilha proximoPilha) {
        this.proximoPilha = proximoPilha;
    }

}
