package com.masanz.da.survey.service;

import com.masanz.da.survey.dao.DaoSurvey;
import com.masanz.da.survey.database.ConnectionManager;
import com.masanz.da.survey.dto.Opinion;
import com.masanz.da.survey.dto.Recuento;
import com.masanz.da.survey.dto.Voto;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class TareasDao extends Tareas {

    private DaoSurvey dao;

    public TareasDao() {
        ConnectionManager.conectar("survey_db", "proy", "password");
        dao = new DaoSurvey();
    }

    @Override
    public boolean autenticar(String usuario, String clave) {
//        return usuario.equals("Aitor") && clave.equals("KEY123");
        return dao.autenticar(usuario, clave);
    }

    @Override
    public Voto obtenerVoto(String usuario) {
//        return null;
//        return new Voto("Aitor", "txantrea");
        return dao.obtenerVoto(usuario);
    }

    @Override
    public Map<String, Integer> obtenerVotos() {
        List<Voto> lstVotos = dao.obtenerVotos();
        Map<String, Integer> mapVotos = new TreeMap<>();
        for (Voto voto : lstVotos) {
            String opcion = voto.getOpcion();
            if (mapVotos.containsKey(opcion)) {
                mapVotos.put(opcion, mapVotos.get(opcion) + 1);
            } else {
                mapVotos.put(opcion, 1);
            }
        }
        return mapVotos;
    }

    @Override
    public List<Recuento> obtenerRecuentos() {
        return dao.obtenerRecuentos();
    }

    @Override
    public boolean votar(String nombre, String voto) {
        return dao.votar(nombre, voto);
    }

    @Override
    public List<Opinion> obtenerOpiniones() {
        return dao.obtenerOpiniones();
    }

    @Override
    public boolean opinar(String nombre, int numeroRespuesta, String opinion) {
        return dao.opinar(nombre, numeroRespuesta, opinion);
    }

}
