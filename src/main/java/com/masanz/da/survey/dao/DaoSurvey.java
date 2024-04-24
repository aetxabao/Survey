package com.masanz.da.survey.dao;

import com.masanz.da.survey.database.ConnectionManager;
import com.masanz.da.survey.dto.Opinion;
import com.masanz.da.survey.dto.Recuento;
import com.masanz.da.survey.dto.Voto;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class DaoSurvey {

    public boolean autenticar(String usuario, String clave) {
        String sql = "SELECT * FROM login WHERE nombre = ? AND codigo = ? LIMIT 1";
        Object[] params = {usuario, clave};
        Object[][] resultado = ConnectionManager.ejecutarSelectSQL(sql, params);
        if (resultado != null && resultado.length == 1) {
            return true;
        }
        return false;
    }

    public Voto obtenerVoto(String usuario) {
        String sql = "SELECT nombre, voto, cuando FROM votos WHERE nombre = ? LIMIT 1";
        Object[] params = {usuario};
        Object[][] resultado = ConnectionManager.ejecutarSelectSQL(sql, params);
        if (resultado != null && resultado.length == 1) {
            Voto voto = new Voto();
            voto.setUsuario((String) resultado[0][0]);
            voto.setOpcion((String) resultado[0][1]);
            Timestamp ts = (Timestamp) resultado[0][2];
            voto.setTimestamp(ts.toLocalDateTime());
            return voto;
        }
        return null;
    }

    public List<Recuento> obtenerRecuentos() {
        String sql = "SELECT o.id, o.nombre, o.valor, count(v.cuando) AS cuantos " +
                "FROM opciones o LEFT JOIN votos v ON o.valor = v.voto " +
                "GROUP BY o.id, o.nombre, o.valor ORDER BY o.id ASC";
        Object[] params = {};
        Object[][] resultado = ConnectionManager.ejecutarSelectSQL(sql, params);
        List<Recuento> recuentos = new ArrayList<>();
        if (resultado != null) {
            for (Object[] fila : resultado) {
                Recuento recuento = new Recuento();
                recuento.setId((Integer) fila[0]);
                recuento.setNombre((String) fila[1]);
                recuento.setValor((String) fila[2]);
                String cuantos = String.valueOf(fila[3]);
                recuento.setCuantos(Integer.parseInt(cuantos));
                recuentos.add(recuento);
            }
        }
        return recuentos;
    }

    public List<Voto> obtenerVotos() {
        String sql = "SELECT nombre, voto FROM votos ORDER BY id ASC";
        Object[] params = {};
        Object[][] resultado = ConnectionManager.ejecutarSelectSQL(sql, params);
        List<Voto> votos = new ArrayList<>();
        if (resultado != null) {
            for (Object[] fila : resultado) {
                Voto voto = new Voto();
                voto.setUsuario((String) fila[0]);
                voto.setOpcion((String) fila[1]);
                votos.add(voto);
            }
        }
        return votos;
    }

    public boolean votar(String nombre, String voto) {
        Voto votoAnterior = obtenerVoto(nombre);
        if (votoAnterior != null) {
            return actualizarVoto(nombre, voto);
        } else {
            return insertarVoto(nombre, voto);
        }
    }

    private boolean actualizarVoto(String nombre, String voto) {
        String sql = "UPDATE votos SET voto = ? WHERE nombre = ?";
        Object[] params = {voto, nombre};
        ConnectionManager.ejecutarUpdateSQL(sql, params);
        return true;
    }

    private boolean insertarVoto(String nombre, String voto) {
        String sql = "INSERT INTO votos (nombre, voto) VALUES (?, ?)";
        Object[] params = {nombre, voto};
        ConnectionManager.ejecutarUpdateSQL(sql, params);
        return true;
    }

    public List<Opinion> obtenerOpiniones() {
        String sql =   "SELECT id, opinion, nombre, respuesta FROM opiniones " +
                        "ORDER BY id ASC";
        Object[] params = {};
        Object[][] resultado = ConnectionManager.ejecutarSelectSQL(sql, params);

        Map<Integer, Opinion> mapaOpiniones = new TreeMap<>();
        if (resultado != null) {
            for (Object[] fila : resultado) {
                Opinion opinion = new Opinion((Integer) fila[0], (String) fila[1], (String) fila[2]);
                int respuesta = (Integer) fila[3];
                if (respuesta == 0) {
                    mapaOpiniones.put(opinion.getNumero(), opinion);
                } else {
                    Opinion padre = mapaOpiniones.get(respuesta);
                    if (padre != null) {
                        padre.addRespuesta(opinion);
                    }
                }
            }
        }
        return new ArrayList<>(mapaOpiniones.values());
    }

    public List<Opinion> obtenerOpiniones2() {
        String sql1 =   "SELECT id, opinion, nombre FROM opiniones " +
                        "WHERE respuesta = 0 ORDER BY id ASC";
        Object[] params1 = {};
        Object[][] resultado1 = ConnectionManager.ejecutarSelectSQL(sql1, params1);
        List<Opinion> opiniones = new ArrayList<>();
        if (resultado1 != null) {
            for (Object[] fila : resultado1) {
                Opinion opinion = new Opinion((Integer) fila[0], (String) fila[1], (String) fila[2]);
                String sql2 =   "SELECT id, opinion, nombre FROM opiniones " +
                                "WHERE respuesta = ? ORDER BY id ASC";
                Object[] pars2 = {};
                Object[][] res2 = ConnectionManager.ejecutarSelectSQL(sql2, pars2);
                if (res2 != null) {
                    for (Object[] f : res2) {
                        Opinion respuesta = new Opinion((Integer) f[0], (String) f[1], (String) f[2]);
                        opinion.addRespuesta(respuesta);
                    }
                }
                opiniones.add(opinion);
            }
        }
        return opiniones;
    }

    public boolean opinar(String nombre, int numeroRespuesta, String opinion) {
        String sql = "INSERT INTO opiniones (nombre, respuesta, opinion) VALUES (?, ?, ?)";
        Object[] params = {nombre, numeroRespuesta, opinion};
        ConnectionManager.ejecutarUpdateSQL(sql, params);
        return true;
    }

}
