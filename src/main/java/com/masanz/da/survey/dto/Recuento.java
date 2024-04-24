package com.masanz.da.survey.dto;

public class Recuento {
    private int id;
    private String nombre;
    private String valor;
    private int cuantos;

    public Recuento() {
        this(0, "", "", 0);
    }

    public Recuento(int id, String nombre, String valor, int cuantos) {
        this.id = id;
        this.nombre = nombre;
        this.valor = valor;
        this.cuantos = cuantos;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public int getCuantos() {
        return cuantos;
    }

    public void setCuantos(int cuantos) {
        this.cuantos = cuantos;
    }

    @Override
    public String toString() {
        return "{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", valor='" + valor + '\'' +
                ", cuantos=" + cuantos +
                '}';
    }
}
