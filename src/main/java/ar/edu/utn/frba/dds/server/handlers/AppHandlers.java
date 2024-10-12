package ar.edu.utn.frba.dds.server.handlers;

import io.javalin.Javalin;
import java.util.Arrays;

public class AppHandlers {
    private final IHandler[] handlers = new IHandler[]{
            new AccesoDenegadoHandler(),
            new UsuarioNoAutenticadoHandler(),
            new RecursoInexistenteHandler(),
            new ClaveHandler(),
            new NoTieneDireccionHandler(),
            new CodigoInvalidoHandler(),
            new FormularioNoCompletadoHandler(),
            new RegistroFailedHandler(),
            new FormIncompletoHandler()
            , new MailDuplicadoHandler()
            , new DniDuplicadoHandler()
    };

    public static void applyHandlers(Javalin app) {
        Arrays.stream(new AppHandlers().handlers).toList().forEach(handler -> handler.setHandle(app));
    }
}
