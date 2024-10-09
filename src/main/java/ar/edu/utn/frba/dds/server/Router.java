package ar.edu.utn.frba.dds.server;

import ar.edu.utn.frba.dds.controllers.*;
import ar.edu.utn.frba.dds.serviceLocator.ServiceLocator;
import ar.edu.utn.frba.dds.services.FileUploadService;
import ar.edu.utn.frba.dds.services.HeladerasService;
import io.javalin.Javalin;
import io.javalin.http.UploadedFile;
import java.io.IOException;

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
    app.get("/registro/{tipo-persona}", ServiceLocator.get(RegistroController.class)::show);
    // app.get("/registro/persona-humana", ctx ->
    // ctx.render("/auth/registro/registro-humano.hbs"));
    app.post("/registro/persona-humana", ServiceLocator.get(RegistroController.class)::handleRegistroHumano);
    // app.get("/registro/persona-juridica", ctx ->
    // ctx.render("/auth/registro/registro-juridico.hbs"));
    app.post("/registro/persona-juridica", ServiceLocator.get(RegistroController.class)::handleRegistroJuridico);

    // ALTA
    app.get("/admin/tecnicos/nuevo", ServiceLocator.get(TecnicosController.class)::create);
    app.post("/admin/tecnicos/nuevo", ServiceLocator.get(TecnicosController.class)::save);
    app.get("/admin/formularios/nuevo", ServiceLocator.get(FormulariosController.class)::create);
    app.post("/admin/formularios/nuevo", ServiceLocator.get(FormulariosController.class)::save);
    app.get("/admin/modelos-heladeras/nuevo", ServiceLocator.get(ModelosHeladeraController.class)::create);
    app.post("/admin/modelos-heladeras/nuevo", ServiceLocator.get(ModelosHeladeraController.class)::save);

    app.get("/responder-formulario/{idFormulario}/colaborador/{idColaborador}", ServiceLocator.get(RespuestaFormularioController.class)::create);
    app.post("/responder-formulario/{idFormulario}/colaborador/{idColaborador}", ServiceLocator.get(RespuestaFormularioController.class)::save);

    // COLABORACIONES
    app.get("/colaborar", ctx -> ctx.render("/app/colaboraciones/colaborar.hbs"));

    app.get("/colaborar/donar-dinero", ServiceLocator.get(DonacionDineroController.class)::create);
    app.post("/colaborar/donar-dinero", ServiceLocator.get(DonacionDineroController.class)::save);

    app.get("/colaborar/registrar-persona-vulnerable", ServiceLocator.get(AltaPersonaVulnerableController.class)::create);
    app.post("/colaborar/registrar-persona-vulnerable", ServiceLocator.get(AltaPersonaVulnerableController.class)::save);
    app.get("/colaborar/registrar-persona-vulnerable/registrar-tutorados", ServiceLocator.get(AltaPersonaVulnerableController.class)::createTutorados);
    app.post("/colaborar/registrar-persona-vulnerable/registrar-tutorados", ServiceLocator.get(AltaPersonaVulnerableController.class)::saveTutorados);

    app.get("/colaborar/donar-vianda", ctx -> ctx.render("/app/colaboraciones/donacion-vianda.hbs"));

    app.get("/colaborar/distribuir-viandas", ctx -> ctx.render("/app/colaboraciones/distribucion-vianda.hbs"));

    app.get("/colaborar/colocar-heladera", ServiceLocator.get(ColocacionHeladerasController.class)::create);
    app.post("/colaborar/colocar-heladera", ServiceLocator.get(ColocacionHeladerasController.class)::save);

    app.get("/colaborar/ofrecer-producto", ServiceLocator.get(OfertasProductoController.class)::create);

    // HELADERAS
    app.get("/heladeras", ctx -> ctx.render("/app/heladeras/heladeras.hbs"));
    app.get("/heladeras/mapa", ctx -> ctx.json(ServiceLocator.get(HeladerasService.class).getHeladerasParaMapa()));
    app.get("/heladeras/puntos-donacion", ctx -> ctx.render("/app/heladeras/puntosdonacion.hbs"));
    app.get("/heladeras/{id}/suscribirse", ServiceLocator.get(SuscripcionesController.class)::create);
    app.get("/heladeras/{id}/reportar-falla-tecnica", ServiceLocator.get(FallasTecnicasController.class)::create);
    app.post("/heladeras/{id}/reportar-falla-tecnica", ServiceLocator.get(FallasTecnicasController.class)::save);
    app.get("/heladeras/alertas", ServiceLocator.get(AlertasController.class)::index);

    // REPORTES
    app.get("/reportes", ctx -> ctx.render("/app/reportes/reportes.hbs"));

    // CARGA MASIVA
    app.get("/carga-masiva-colaboraciones", ctx -> ctx.render("/app/carga-masiva/carga-masiva.hbs"));

    // PRODUCTOS
    app.get("/productos", ServiceLocator.get(OfertasProductoController.class)::index);
    app.post("/productos", ServiceLocator.get(OfertasProductoController.class)::save);

    app.get("/", ctx -> {
      ctx.redirect("/quienes-somos");
    });

    app.get("/quienes-somos", ctx -> ctx.render("/app/quienes-somos.hbs"));

    app.post("/upload-carga-masiva", ctx -> {
      UploadedFile uploadedFile = ctx.uploadedFile("file");
      try {
        FileUploadService fileUploadService = new FileUploadService();
        String result = fileUploadService.uploadFile(uploadedFile);
        ctx.result(result);
      } catch (IOException e) {
        e.printStackTrace();
        ctx.result("Error al subir el archivo: " + e.getMessage());
      }
    });
  }
}
