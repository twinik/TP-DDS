package ar.edu.utn.frba.dds.controllers;

import ar.edu.utn.frba.dds.dtos.heladeras.HeladeraDto;
import ar.edu.utn.frba.dds.serviceLocator.ServiceLocator;
import ar.edu.utn.frba.dds.services.FallasTecnicasService;
import ar.edu.utn.frba.dds.services.FileUploadService;
import ar.edu.utn.frba.dds.services.HeladerasService;
import ar.edu.utn.frba.dds.utils.ICrudViewsHandler;
import io.javalin.http.Context;
import io.javalin.http.UploadedFile;
import lombok.AllArgsConstructor;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
public class FallasTecnicasController implements ICrudViewsHandler {

  FallasTecnicasService service;

  @Override
  public void index(Context context) {

  }

  @Override
  public void show(Context context) {

  }

  @Override
  public void create(Context context) {
    String heladeraId = context.pathParam("id");

    HeladeraDto h = ServiceLocator.get(HeladerasService.class).getHeladeraDto(heladeraId);

    if (h == null) {
      context.status(404);
      context.render("/app/404.hbs");
      return;
    }

    Map<String, Object> model = new HashMap<>();

    model.put("heladera", h);
    context.render("/app/heladeras/reportar-falla.hbs", model);
  }

  @Override
  public void save(Context context) {
    UploadedFile uploadedFile = context.uploadedFile("file");
    try {
      FileUploadService fileUploadService = new FileUploadService();
      String result = fileUploadService.uploadFile(uploadedFile, "src/main/resources/templates/app/heladeras/");
      context.result(result);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void edit(Context context) {

  }

  @Override
  public void update(Context context) {

  }

  @Override
  public void delete(Context context) {

  }
}
