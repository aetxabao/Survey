package com.masanz.da.survey;

import com.masanz.da.survey.service.Tareas;
import com.masanz.da.survey.service.TareasDao;
import freemarker.template.Configuration;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.template.freemarker.FreeMarkerEngine;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;

public class Main {
    private static final Logger logger = LogManager.getLogger(Main.class);

    public static void main(String[] args) {

        logger.info("ARRANCANDO APLICACION");

        staticFileLocation("com/masanz/da/survey/public");
        port(8080);

        FreeMarkerEngine freeMarker = new FreeMarkerEngine();
        Configuration configuration = new Configuration(Configuration.VERSION_2_3_0);
        configuration.setClassForTemplateLoading(Main.class, "templates");
        freeMarker.setConfiguration(configuration);

//        Tareas tareas = new Tareas();
        Tareas tareas = new TareasDao();

        get("/index.html", (request, response) -> {
            String fichero = "index";
            Map model = new HashMap();
            model.put("nombre", "NOMBRE");
            model.put("codigo", "CODIGO");
            return new ModelAndView(model, fichero + ".ftl");
        }, freeMarker);

        post("/votar.html", (request, response) -> {
            String fichero = "votar";
            String nombre = request.queryParams("nombre");
            String codigo = request.queryParams("codigo");
            String voto = request.queryParams("voto");
            logger.info(String.format("\nnombre=%s\ncodigo=%s\nvoto=%s", nombre, codigo, voto));
            if (!tareas.autenticar(nombre, codigo)) {
                logger.info(String.format("\nERROR %s autenticar\nnombre=%s\ncodigo=%s", fichero, nombre, codigo));
                response.redirect("/error.html");
                return null;
            }
            if (voto != null && !tareas.votar(nombre, voto)) {
                logger.info(String.format("\nERROR %s votar\nnombre=%s\nvoto=%s", fichero, nombre, voto));
                response.redirect("/error.html");
                return null;
            }
            Map model = new HashMap();
            model.put("nombre", nombre);
            model.put("codigo", codigo);
            model.put("voto", tareas.obtenerVoto(nombre));
            model.put("votos", tareas.obtenerVotos());
            model.put("recuentos", tareas.obtenerRecuentos());
            return new ModelAndView(model, fichero + ".ftl");
        }, freeMarker);

        get("/error.html", (request, response) -> {
            String fichero = "error";
            Map<String, Object> model = new HashMap<>();
            model.put("error", "Usuario no autenticado correctamente");
            return new ModelAndView(model, fichero + ".ftl");
        }, freeMarker);

        get("/opinar.html", (request, response) -> {
            return getpostOpinar(tareas, request, response);
        }, freeMarker);

        post("/opinar.html", (request, response) -> {
            return getpostOpinar(tareas, request, response);
        }, freeMarker);

    }

    private static ModelAndView getpostOpinar(Tareas tareas, Request request, Response response) {
        String fichero = "opinar";
        String nombre = request.queryParams("nombre");
        String codigo = request.queryParams("codigo");
        String numero = request.queryParamOrDefault("numero", "0");
        String opinion = request.queryParamOrDefault("opinion", "");
        logger.info(String.format("\nnombre=%s\ncodigo=%s\nnumero=%s\nopinion=%s",
                                     nombre, codigo, numero, opinion));
        if (!tareas.autenticar(nombre, codigo)) {
            logger.info(String.format("\nERROR %s autenticar\nnombre=%s\ncodigo=%s", fichero, nombre, codigo));
            response.redirect("/error.html");
            return null;
        }
        if (!opinion.isBlank() && !tareas.opinar(nombre, Integer.parseInt(numero), opinion)) {
            logger.info(String.format("\nERROR %s opinar\nnombre=%s\nnumero=%s\nopinion=%s", fichero, nombre, codigo, opinion));
            response.redirect("/error.html");
            return null;
        }
        Map model = new HashMap();
        model.put("nombre", nombre);
        model.put("codigo", codigo);
        model.put("opiniones", tareas.obtenerOpiniones());
        return new ModelAndView(model, fichero + ".ftl");
    }

}
