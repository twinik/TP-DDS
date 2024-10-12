package ar.edu.utn.frba.dds.server;

import ar.edu.utn.frba.dds.controllers.*;
import ar.edu.utn.frba.dds.serviceLocator.ServiceLocator;
import ar.edu.utn.frba.dds.services.HeladerasService;
import io.javalin.Javalin;
import io.javalin.http.NotFoundResponse;

/**
 * EL router es el encargado de las RUTAS.
 */

public class Router {
    /**
     * Inicio de App.
     */
    public static void init(Javalin app) {

        // LOGIN
        app.get("/login", ServiceLocator.get(LoginController.class)::index);
        app.post("/login", ServiceLocator.get(LoginController.class)::handleLogin);
        app.get("/logout", ServiceLocator.get(LogoutController.class)::handleLogout);

        // REGISTRO
        app.get("/registro", ServiceLocator.get(RegistroController.class)::index);
        app.get("/registro/{tipo-persona}", ServiceLocator.get(RegistroController.class)::create);
        app.post("/registro/persona-humana", ServiceLocator.get(RegistroController.class)::handleRegistroHumano);
        app.post("/registro/persona-juridica", ServiceLocator.get(RegistroController.class)::handleRegistroJuridico);

        // ALTA ADMIN
        app.get("/admin/tecnicos/nuevo", ServiceLocator.get(TecnicosController.class)::create);
        app.post("/admin/tecnicos/nuevo", ServiceLocator.get(TecnicosController.class)::save);
        app.get("/admin/formularios/nuevo", ServiceLocator.get(FormulariosController.class)::create);
        app.post("/admin/formularios/nuevo", ServiceLocator.get(FormulariosController.class)::save);
        app.get("/admin/modelos-heladeras/nuevo", ServiceLocator.get(ModelosHeladeraController.class)::create);
        app.post("/admin/modelos-heladeras/nuevo", ServiceLocator.get(ModelosHeladeraController.class)::save);
        app.get("/admin/tarjetas/nuevo", ServiceLocator.get(PosiblesCodigosTarjetasController.class)::create);
        app.post("/admin/tarjetas/nuevo", ServiceLocator.get(PosiblesCodigosTarjetasController.class)::save);

        app.get("/responder-formulario/colaborador/{idColaborador}", ServiceLocator.get(RespuestaFormularioController.class)::obtenerFormulario);
        app.get("/responder-formulario/{idFormulario}/colaborador/{idColaborador}", ServiceLocator.get(RespuestaFormularioController.class)::create);
        app.post("/responder-formulario/{idFormulario}/colaborador/{idColaborador}", ServiceLocator.get(RespuestaFormularioController.class)::save);

        // COLABORACIONES
        app.get("/colaborar", ctx -> ctx.render("/app/colaboraciones/colaborar.hbs"));

        app.get("/colaborar/donar-dinero", ServiceLocator.get(DonacionDineroController.class)::create);
        app.post("/colaborar/donar-dinero", ServiceLocator.get(DonacionDineroController.class)::save);

        app.get("/colaborar/registrar-persona-vulnerable", ServiceLocator.get(AltaPersonaVulnerableController.class)::create);
        app.post("/colaborar/registrar-persona-vulnerable", ServiceLocator.get(AltaPersonaVulnerableController.class)::save);
        app.get("/colaborar/registrar-persona-vulnerable/{id}/registrar-tutorados", ServiceLocator.get(AltaPersonaVulnerableController.class)::createTutorados);
        app.post("/colaborar/registrar-persona-vulnerable/{id}/registrar-tutorados", ServiceLocator.get(AltaPersonaVulnerableController.class)::saveTutorados);

        app.get("/colaborar/donar-vianda", ServiceLocator.get(ViandasController.class)::create);
        app.post("/colaborar/donar-vianda", ServiceLocator.get(ViandasController.class)::save);

        app.get("/colaborar/distribuir-viandas", ctx -> ctx.render("/app/colaboraciones/distribucion-vianda.hbs"));

        app.get("/colaborar/colocar-heladera", ServiceLocator.get(ColocacionHeladerasController.class)::create);
        app.post("/colaborar/colocar-heladera", ServiceLocator.get(ColocacionHeladerasController.class)::save);
        app.get("/colaborar/recibir-puntos-colocacion", ServiceLocator.get(RecomendacionesController.class)::getPuntosColocacionParaMapa);

        app.get("/colaborar/ofrecer-producto", ServiceLocator.get(OfertasProductoController.class)::create);

        // HELADERAS
        app.get("/heladeras", ctx -> ctx.render("/app/heladeras/heladeras.hbs"));
        app.get("/heladeras/mapa", ctx -> ctx.json(ServiceLocator.get(HeladerasService.class).getHeladerasParaMapa()));
        app.get("/heladeras/donar",ctx -> ctx.json(ServiceLocator.get(HeladerasService.class).getHeladerasParaDonar()));

        app.get("/heladeras/puntos-donacion", ctx -> ctx.render("/app/heladeras/puntosdonacion.hbs"));
        app.get("/heladeras/recibir-puntos", ServiceLocator.get(RecomendacionesController.class)::getDonacionesParaMapa);

        app.get("/heladeras/{id}/suscribirse", ServiceLocator.get(SuscripcionesController.class)::create);
        app.post("/heladeras/{id}/suscribirse", ServiceLocator.get(SuscripcionesController.class)::save);

        app.get("/heladeras/{id}/reportar-falla-tecnica", ServiceLocator.get(FallasTecnicasController.class)::create);
        app.post("/heladeras/{id}/reportar-falla-tecnica", ServiceLocator.get(FallasTecnicasController.class)::save);

        app.get("/heladeras/alertas", ServiceLocator.get(AlertasController.class)::index);

        // REPORTES
        app.get("/reportes", ServiceLocator.get(ReportesController.class)::index);
        app.get("/reportes/descargar", ServiceLocator.get(ReportesController.class)::show);

        // CARGA MASIVA
        app.get("/carga-masiva-colaboraciones", ServiceLocator.get(CargaMasivaController.class)::create);
        app.post("/carga-masiva-colaboraciones", ServiceLocator.get(CargaMasivaController.class)::save);

        // PRODUCTOS
        app.get("/productos", ServiceLocator.get(OfertasProductoController.class)::index);
        app.post("/productos", ServiceLocator.get(OfertasProductoController.class)::save);

        app.get("/quienes-somos", ctx -> ctx.render("/app/quienes-somos.hbs"));
        app.get("/", ctx -> ctx.redirect("/quienes-somos"));

        app.exception(Exception.class, (e, ctx) -> {
            e.printStackTrace();
            ctx.status(500);
            ctx.render("/app/500.hbs");
        });

        app.exception(NotFoundResponse.class, (e, ctx) -> {
            e.printStackTrace();
            ctx.status(404);
            ctx.render("/app/404.hbs");
        });
    }
}
