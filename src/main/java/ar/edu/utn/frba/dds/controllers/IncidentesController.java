package ar.edu.utn.frba.dds.controllers;

import ar.edu.utn.frba.dds.dtos.heladeras.HeladeraDto;
import ar.edu.utn.frba.dds.dtos.incidentes.AlertaDto;
import ar.edu.utn.frba.dds.dtos.incidentes.FallaTecnicaDto;
import ar.edu.utn.frba.dds.models.repositories.IHeladerasRepository;
import ar.edu.utn.frba.dds.serviceLocator.ServiceLocator;
import ar.edu.utn.frba.dds.services.AlertasService;
import ar.edu.utn.frba.dds.services.FallasTecnicasService;
import ar.edu.utn.frba.dds.services.HeladerasService;
import ar.edu.utn.frba.dds.utils.ICrudViewsHandler;
import io.javalin.http.Context;
import lombok.AllArgsConstructor;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
public class IncidentesController implements ICrudViewsHandler {

  private AlertasService alertasService;
  private FallasTecnicasService fallasTecnicasService;

  public void showAlertas(Context context) {

    // TODO: agregar paginacion y filtros
    List<AlertaDto> alertas = alertasService.obtenerTodos();

    Map<String, Object> model = new HashMap<>();

    model.put("alertas", alertas);

    context.render("/app/heladeras/listado-alertas.hbs", model);

  }

  public void createFallaTecnica(Context ctx) {
    String heladeraId = ctx.pathParam("id");

    HeladeraDto h = ServiceLocator.get(HeladerasService.class).getHeladeraDto(heladeraId);

    if (h == null) {
      ctx.status(404);
      ctx.render("/app/404.hbs");
      return;
    }

    Map<String, Object> model = new HashMap<>();

    model.put("heladera", h);
    ctx.render("/app/heladeras/reportar-falla.hbs", model);
  }

  @Override
  public void index(Context context) {

  }

  @Override
  public void show(Context context) {

  }

  @Override
  public void create(Context context) {

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
