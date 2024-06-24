package com.example.ecofinder.models;

import java.util.Date;

public class ListaRemedio {

    private NoRemedio head;

    public ListaRemedio() {
        this.head = null;
    }

    public NoRemedio getHead() {
        return head;
    }

    public void add(String localRemedio, int quantidadeRemedio, Date dataRemedio) {
        NoRemedio noRemedio = new NoRemedio(localRemedio, quantidadeRemedio, dataRemedio);
        if (head == null) {
            head = noRemedio;
        } else {
            NoRemedio atual = head;
            while (atual.getProximoRemedio() != null) { // Percorre a lista até encontrar o último nó
                atual = atual.getProximoRemedio();
            }
            atual.setProximoRemedio(noRemedio); // Adiciona o novo nó como próximo nó ao final da lista
        }
    }
}
