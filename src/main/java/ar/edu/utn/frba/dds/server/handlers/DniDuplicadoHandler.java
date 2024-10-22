package ar.edu.utn.frba.dds.server.handlers;

import ar.edu.utn.frba.dds.exceptions.DniDuplicadoException;
import io.javalin.Javalin;

public class DniDuplicadoHandler implements IHandler {

    @Override
    public void setHandle(Javalin app) {
        app.exception(DniDuplicadoException.class, (e, context) -> {
            e.printStackTrace();
            context.sessionAttribute("formDto", e.getFormDto());
            context.redirect(context.path() + "?message=" + e.getMessage());
        });
    }
}
