package com.company;


public class NodoVertice {


    /**
     * La etiqueta del vértice
     */
    private String label;

    public NodoVertice(String etiqueta) {
        this.label = etiqueta;
    }

    public NodoVertice(int pos, String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

}
