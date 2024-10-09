package ar.edu.utn.frba.dds.controllers;

import ar.edu.utn.frba.dds.dtos.heladeras.HeladeraDto;
import ar.edu.utn.frba.dds.serviceLocator.ServiceLocator;
import ar.edu.utn.frba.dds.services.HeladerasService;
import ar.edu.utn.frba.dds.utils.ICrudViewsHandler;
import io.javalin.http.Context;
import java.util.HashMap;
import java.util.Map;

public class SuscripcionesController implements ICrudViewsHandler {

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

    Map<String, Object> model = new HashMap<>();

    model.put("heladera", h);
    context.render("/app/heladeras/suscripcion.hbs", model);
  }

  @Override
  public void save(Context context) {

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
