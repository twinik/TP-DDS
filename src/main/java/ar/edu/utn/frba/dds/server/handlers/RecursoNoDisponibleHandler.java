package ar.edu.utn.frba.dds.server.handlers;

import ar.edu.utn.frba.dds.exceptions.RecursoNoDisponibleException;
import io.javalin.Javalin;
import java.util.HashMap;
import java.util.Map;

public class RecursoNoDisponibleHandler implements IHandler {
    @Override
    public void setHandle(Javalin app) {
        app.exception(RecursoNoDisponibleException.class, (e, context) -> {
            e.printStackTrace();
            Map<String, String> model = new HashMap<>();
            model.put("message", e.getMessage());
            context.render("app/error.hbs", model);
        });
    }
}
