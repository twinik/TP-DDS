package ar.edu.utn.frba.dds.server.handlers;

import ar.edu.utn.frba.dds.exceptions.FormularioNoCompletadoException;
import io.javalin.Javalin;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class FormularioNoCompletadoHandler implements IHandler {
    @Override
    public void setHandle(Javalin app) {
        app.exception(FormularioNoCompletadoException.class, (e, context) -> {
            e.printStackTrace();
            context.redirect("/responder-formulario/colaborador/" + e.getIdColaborador());
        });
    }
}
