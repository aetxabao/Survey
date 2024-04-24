package com.masanz.da.survey.dto;

import java.time.LocalDateTime;

public class Voto {
    private String usuario;
    private String opcion;

    private LocalDateTime timestamp;

    public Voto() {
        this("", "");
    }

    public Voto(String usuario, String opcion) {
        this.usuario = usuario;
        this.opcion = opcion;
        this.timestamp = LocalDateTime.now();
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getOpcion() {
        return opcion;
    }

    public void setOpcion(String opcion) {
        this.opcion = opcion;
    }

    public String getTimestamp() {
        return timestamp.toString();
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getFecha() {
        return timestamp.toString().substring(0, 10);
    }

    public String getHora() {
        return timestamp.toString().substring(11, 11+8);
    }

    @Override
    public String toString() {
        return "{" +
                "usuario='" + usuario + '\'' +
                ", opcion='" + opcion + '\'' +
                ", timestamp='" + timestamp +
                "'}";
    }

}
