package com.masanz.da.survey;

import com.masanz.da.survey.service.Tareas;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

public class Demo {
    private static final Logger logger = LogManager.getLogger(Demo.class);
    private static Configuration cfg;
    private static Tareas tareas;


    public static void main(String[] args) throws Exception {
        logger.info("INICIO DEMO");

        setCfg();

        tareas = new Tareas();

        cocinaIndex("Aitor", "KEY123");

        cocinaVotar();

        cocinaOpinar();

        cocinaError();

        logger.info("FIN DEMO");
    }

    private static void setCfg() {
        cfg = new Configuration(Configuration.VERSION_2_3_32);

        // Respecto de la clase Main dónde están las plantillas en la estructura de paquetes
        cfg.setClassForTemplateLoading(Demo.class, "./templates");

        // Recommended settings for new projects:
        cfg.setDefaultEncoding("UTF-8");
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        cfg.setLogTemplateExceptions(false);
        cfg.setWrapUncheckedExceptions(true);
        cfg.setFallbackOnNullLoopVariable(false);
        cfg.setSQLDateAndTimeTimeZone(TimeZone.getDefault());
    }

    private static void cocinaIndex(String nombre, String codigo) throws Exception {
        String fichero = "index";

        Map model = new HashMap();
        model.put("nombre", nombre);
        model.put("codigo", codigo);

        cocina(fichero, model);
    }

    private static void cocinaVotar() throws Exception {
        String fichero = "votar";

        Map model = new HashMap();
        model.put("nombre", "Aitor");
        model.put("codigo", "KEY123");
        model.put("voto", tareas.obtenerVoto("Aitor"));
        model.put("votos", tareas.obtenerVotos());
        model.put("recuentos", tareas.obtenerRecuentos());

        cocina(fichero, model);
    }

    private static void cocinaOpinar() throws Exception {
        String fichero = "opinar";

        Map model = new HashMap();
        model.put("nombre", "Aitor");
        model.put("codigo", "KEY123");
        model.put("opiniones", tareas.obtenerOpiniones());

        cocina(fichero, model);
    }

    private static void cocinaError() throws Exception {
        String fichero = "error";

        Map model = new HashMap();
        model.put("error", "Usuario no autenticado correctamente");

        cocina(fichero, model);
    }

    private static void cocina(String fichero, Map model) throws Exception {
        Template temp = cfg.getTemplate(fichero + ".ftl");

        logger.info(String.format("\nsalida/%s.html\n%s", fichero, desc(model)));

        File file = new File("salida/" + fichero + ".html");
        Writer out = new OutputStreamWriter(new FileOutputStream(file));
        temp.process(model, out);
   }

    private static String desc(Map model) {
        StringBuilder sb = new StringBuilder();
        for (Object clave : model.keySet()) {
            sb.append(clave).append(" = ");
            sb.append(model.get(clave)).append("\n");
        }
        return sb.toString();
    }

}
