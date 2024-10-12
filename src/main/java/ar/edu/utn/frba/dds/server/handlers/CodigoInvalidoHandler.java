package ar.edu.utn.frba.dds.server.handlers;

import ar.edu.utn.frba.dds.models.domain.excepciones.CodigoInvalidoException;
import io.javalin.Javalin;

public class CodigoInvalidoHandler implements IHandler {


    @Override
    public void setHandle(Javalin app) {
        app.exception(CodigoInvalidoException.class, (e, context) -> {
            e.printStackTrace();
            context.redirect(context.path() + "?message=" + e.getMessage());
        });
    }
}
