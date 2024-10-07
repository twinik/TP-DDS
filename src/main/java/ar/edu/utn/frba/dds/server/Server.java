package ar.edu.utn.frba.dds.server;

import ar.edu.utn.frba.dds.middleware.AppMiddlewares;
import ar.edu.utn.frba.dds.middleware.AuthMiddleware;
import ar.edu.utn.frba.dds.server.handlers.AppHandlers;
import ar.edu.utn.frba.dds.utils.Initializer;
import ar.edu.utn.frba.dds.utils.JavalinRenderer;
import ar.edu.utn.frba.dds.utils.PrettyProperties;
import com.github.jknack.handlebars.Handlebars;
import com.github.jknack.handlebars.Template;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
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

      config.fileRenderer(new JavalinRenderer().register("hbs", (path, model, context) -> {
        Handlebars handlebars = new Handlebars();
        Template template = null;
        try {
          template = handlebars.compile(
              "templates/" + path.replace(".hbs", ""));
          model.put("username", context.sessionAttribute("username"));
          model.put("email", context.sessionAttribute("email"));
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
