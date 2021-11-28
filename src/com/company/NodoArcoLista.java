package com.company;

public class NodoArcoLista {

    private NodoVertice origen;
    private NodoVertice destino;
    private int peso;

    public NodoArcoLista(NodoVertice origen, NodoVertice destino) {
        this.origen = origen;
        this.destino = destino;
    }

    public NodoArcoLista(NodoVertice origen, NodoVertice destino, int peso) {
        this.origen = origen;
        this.destino = destino;
        this.peso = peso;
    }

    public NodoVertice getOrigen() {
        return origen;
    }

    public void setOrigen(NodoVertice origen) {
        this.origen = origen;
    }

    public NodoVertice getDestino() {
        return destino;
    }

    public void setDestino(NodoVertice destino) {
        this.destino = destino;
    }

    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }
}
