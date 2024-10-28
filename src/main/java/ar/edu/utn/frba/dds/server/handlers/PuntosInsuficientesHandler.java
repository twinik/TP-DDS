package ar.edu.utn.frba.dds.server.handlers;

import ar.edu.utn.frba.dds.models.domain.colaboraciones.utils.PuntosInsuficientesException;
import io.javalin.Javalin;

public class PuntosInsuficientesHandler implements IHandler {

    @Override
    public void setHandle(Javalin app) {
        app.exception(PuntosInsuficientesException.class, (e, context) -> {
            e.printStackTrace();
            context.redirect(context.path() + "?message=" + e.getMessage());
        });
    }
}
