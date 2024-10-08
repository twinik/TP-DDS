package ar.edu.utn.frba.dds.middleware;

import io.javalin.Javalin;
import java.util.Arrays;

public class AppMiddlewares {

    private final IMiddleware[] middlewares = new IMiddleware[]{new AuthMiddleware()};

    public static void applyMiddlewares(Javalin app) {
        Arrays.stream(new AppMiddlewares().middlewares).toList().forEach(middleware -> middleware.apply(app));
    }
}
