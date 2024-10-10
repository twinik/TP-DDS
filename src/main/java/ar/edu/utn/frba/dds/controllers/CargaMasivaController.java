package ar.edu.utn.frba.dds.controllers;

import ar.edu.utn.frba.dds.exceptions.ArchivoNoCargadoException;
import ar.edu.utn.frba.dds.services.FileUploadService;
import ar.edu.utn.frba.dds.utils.ICrudViewsHandler;
import io.javalin.http.Context;
import io.javalin.http.UploadedFile;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CargaMasivaController implements ICrudViewsHandler {
  private FileUploadService service;

  @Override
  public void index(Context context) {

  }

  @Override
  public void show(Context context) {

  }

  @Override
  public void create(Context context) {
    context.render("/app/carga-masiva/carga-masiva.hbs");
  }

  @Override
  public void save(Context context) {
    Map<String, Object> model = new HashMap<>();
    UploadedFile uploadedFile = context.uploadedFile("file");

    if (uploadedFile == null) throw new ArchivoNoCargadoException("No se ha cargado ningún archivo");

    try {
      String result = this.service.uploadFile(uploadedFile);
      model.put("message", "Archivo subido correctamente");
      context.render("/app/success.hbs", model);
    } catch (IOException e) {
      e.printStackTrace();
      model.put("message", "Error al subir el archivo: " + e.getMessage());
      context.render("/app/error.hbs", model);
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
