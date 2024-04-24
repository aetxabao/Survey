package com.masanz.da.survey.dto;

import java.util.ArrayList;
import java.util.List;

public class Opinion {
    private int numero;
    private String texto;
    private String autor;
    private List<Opinion> respuestas;

    public Opinion() {
        this(0, "", "");
    }

    public Opinion(int numero, String texto, String autor) {
        this.numero = numero;
        this.texto = texto;
        this.autor = autor;
        this.respuestas = new ArrayList<>();
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public List<Opinion> getRespuestas() {
        return respuestas;
    }

    public void addRespuesta(Opinion respuesta) {
        this.respuestas.add(respuesta);
    }

    public int getNumRespuestas() {
        return this.respuestas.size();
    }

    @Override
    public String toString() {
        return "{" +
                "numero=" + numero +
                ", texto='" + texto + '\'' +
                ", autor='" + autor + '\'' +
                ", respuestas=" + respuestas +
                '}';
    }

}
