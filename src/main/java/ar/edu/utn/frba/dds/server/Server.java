package ar.edu.utn.frba.dds.server;

import ar.edu.utn.frba.dds.brokers.AperturaHeladeraBroker;
import ar.edu.utn.frba.dds.middleware.AppMiddlewares;
import ar.edu.utn.frba.dds.server.handlers.AppHandlers;
import ar.edu.utn.frba.dds.utils.Initializer;
import ar.edu.utn.frba.dds.utils.JavalinRenderer;
import ar.edu.utn.frba.dds.utils.PrettyProperties;
import com.github.jknack.handlebars.Handlebars;
import com.github.jknack.handlebars.Template;
import io.javalin.Javalin;
import io.javalin.config.JavalinConfig;
import io.javalin.http.HttpStatus;
import io.javalin.http.staticfiles.Location;
import io.javalin.json.JavalinGson;
import java.io.IOException;
import java.util.function.Consumer;

public class Server {
  private static Javalin app = null;

  public static Javalin app() {
    if (app == null)
      throw new RuntimeException("App no inicializada");
    return app;
  }

  public static void init() {
    if (app == null) {
      // TODO: Revisar properties
      Integer port = Integer.parseInt(PrettyProperties.getInstance().propertyFromName("server_port"));
      app = Javalin.create(config()).start(port);
      AppMiddlewares.applyMiddlewares(app);
      AppHandlers.applyHandlers(app);
      Router.init(app);
      // TODO: esto va aca ???
      try {
        AperturaHeladeraBroker.suscribirseAAperturasHeladeras();
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
      if (Boolean.parseBoolean(PrettyProperties.getInstance().propertyFromName("dev_mode"))) {
        Initializer.init();
      }
    }
  }

  private static Consumer<JavalinConfig> config() {
    return config -> {
      config.staticFiles.add(staticFiles -> {
        staticFiles.hostedPath = "/";
        staticFiles.directory = "/public";
      });

      config.staticFiles.add(staticFiles -> {
        staticFiles.hostedPath = "/uploads";
        staticFiles.directory = "uploads";
        staticFiles.location = Location.EXTERNAL;
      });

      config.staticFiles.add(staticFiles -> {
        staticFiles.hostedPath = "/reportes";
        staticFiles.directory = "reportes";
        staticFiles.location = Location.EXTERNAL;
      });

      config.fileRenderer(new JavalinRenderer().register("hbs", (path, model, context) -> {
        Handlebars handlebars = new Handlebars();

        handlebars.registerHelper("switch", (value, options) -> {
          options.context.data("switchValue", value);
          return options.fn();
        });

        handlebars.registerHelper("case", (value, options) -> {
          Object switchValue = options.context.data("switchValue");

          if (switchValue != null && switchValue.equals(value)) {
            return options.fn();
          }

          return options.inverse();
        });

        Template template = null;
        try {
          template = handlebars.compile(
              "templates/" + path.replace(".hbs", ""));
          model.put("username", context.sessionAttribute("username"));
          model.put("email", context.sessionAttribute("email"));
          model.put("permisoTecnico", context.sessionAttribute("permisoTecnico"));
          model.put("permisoFormulario", context.sessionAttribute("permisoFormulario"));
          model.put("permisoModeloHeladera", context.sessionAttribute("permisoModeloHeladera"));
          model.put("permisoTarjeta", context.sessionAttribute("permisoTarjeta"));
          model.put("admin", context.sessionAttribute("admin"));
          return template.apply(model);
        } catch (IOException e) {
          e.printStackTrace();
          context.status(HttpStatus.NOT_FOUND);
          return "No se encuentra la página indicada...";
        }
      }));

      config.jsonMapper(new JavalinGson());
    };
  }
}
