package com.masanz.da.survey.service;

import com.masanz.da.survey.dto.Opinion;
import com.masanz.da.survey.dto.Recuento;
import com.masanz.da.survey.dto.Voto;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Tareas {

    public boolean autenticar(String usuario, String clave) {
        return usuario.equals("Aitor") && clave.equals("KEY123");
    }

    public Voto obtenerVoto(String usuario) {
//        return null;
        return new Voto("Aitor", "txantrea");
    }

    public Map<String, Integer> obtenerVotos() {
        Map<String, Integer> votos = new TreeMap<>();
        votos.put("txantrea", 10);
        votos.put("sanjuan", 20);
        votos.put("hiruherri", 30);
        return votos;
    }

    public List<Recuento> obtenerRecuentos() {
        Recuento[] recuentos = new Recuento[] {
                new Recuento(1, "Txantrea", "txantrea", 10),
                new Recuento(2, "San Juan", "sanjuan", 20),
                new Recuento(3, "Hiru Herri", "hiruherri", 30),
        };
        return new ArrayList<>(List.of(recuentos));
    }

    public boolean votar(String nombre, String voto) {
        return true;
    }

    public List<Opinion> obtenerOpiniones() {
        Opinion[] opiniones = new Opinion[]{
                new Opinion(1, "Aupa Txantrea, la mejor!", "Aitor"),
                new Opinion(4, "Falta Beste Iruña", "Mikel"),
                new Opinion(5, "Ardoi es mejor", "Spyros"),
                new Opinion(6, "Ardoi txapeldun!", "Edu"),
        };
        opiniones[0].addRespuesta(new Opinion(2, "No tienes ni idea", "Alba"));
        opiniones[0].addRespuesta(new Opinion(3, "Ya lo veremos", "Aitor"));
        opiniones[1].addRespuesta(new Opinion(7, "Estoy de acuerdo", "Javi"));
        return new ArrayList<>(List.of(opiniones));
    }

    public boolean opinar(String nombre, int numeroRespuesta, String opinion) {
        return true;
    }

}
