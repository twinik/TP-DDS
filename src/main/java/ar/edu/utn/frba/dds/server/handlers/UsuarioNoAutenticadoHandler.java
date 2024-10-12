package ar.edu.utn.frba.dds.server.handlers;

import ar.edu.utn.frba.dds.exceptions.NoAutorizadoException;
import io.javalin.Javalin;

public class UsuarioNoAutenticadoHandler implements IHandler {
    @Override
    public void setHandle(Javalin app) {
        app.exception(NoAutorizadoException.class, (e, context) -> {
            e.printStackTrace();
            context.status(401);
            context.render("/app/401.hbs");
        });
    }
}
