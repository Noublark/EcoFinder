package com.example.ecofinder.models;

import java.util.Date;

public class ListaPilha {

    private NoPilha head;

    public ListaPilha() {
        this.head = null;
    }

    public NoPilha getHead() {
        return head;
    }

    public void add(String localPilha, int quantidadePilha, Date dataPilha) {
        NoPilha noPilha = new NoPilha(localPilha, quantidadePilha, dataPilha);
        if (head == null) {
            head = noPilha;
        } else {
            NoPilha atual = head;
            while (atual.getProximoPilha() != null) { // Percorre a lista até encontrar o último nó
                atual = atual.getProximoPilha();
            }
            atual.setProximoPilha(noPilha); // Adiciona o novo nó como próximo nó ao final da lista
        }
    }
}
