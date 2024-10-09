package ar.edu.utn.frba.dds.controllers;

import ar.edu.utn.frba.dds.dtos.heladeras.HeladeraDto;
import ar.edu.utn.frba.dds.dtos.incidentes.FallaTecnicaDto;
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
  FileUploadService fileUploadService;

  @Override
  public void index(Context context) {
    // TODO: hacer view para que un admin pueda ver fallas tecnicas
  }

  @Override
  public void show(Context context) {

  }

  @Override
  public void create(Context context) {
    String heladeraId = context.pathParam("id");

    HeladeraDto h = ServiceLocator.get(HeladerasService.class).getHeladeraDto(heladeraId);

    Map<String, Object> model = new HashMap<>();

    model.put("heladera", h);
    context.render("/app/heladeras/reportar-falla.hbs", model);
  }

  @Override
  public void save(Context context) {
    FallaTecnicaDto falla = FallaTecnicaDto.of(context);
    UploadedFile uploadedFile = context.uploadedFile("file");
    try {
      if (uploadedFile != null) {
        String result = this.fileUploadService.uploadFile(uploadedFile);
        falla.setUrlFoto(result);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    service.crear(falla);
    Map<String, String> model = new HashMap<>();
    model.put("message", "La falla tecnica fue registrada con exito!");
    context.status(201);
    context.render("/app/success.hbs", model);
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
