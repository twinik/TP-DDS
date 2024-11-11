package ar.edu.utn.frba.dds.controllers;

import ar.edu.utn.frba.dds.dtos.heladeras.HeladeraInputDto;
import ar.edu.utn.frba.dds.serviceLocator.ServiceLocator;
import ar.edu.utn.frba.dds.services.ColocacionHeladerasService;
import ar.edu.utn.frba.dds.services.ModelosService;
import ar.edu.utn.frba.dds.utils.ICrudViewsHandler;
import io.javalin.http.Context;
import io.micrometer.core.instrument.step.StepMeterRegistry;
import lombok.AllArgsConstructor;
import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
public class ColocacionHeladerasController implements ICrudViewsHandler {
  private ColocacionHeladerasService colocacionHeladerasService;
  private ModelosService modelosService;

  @Override
  public void index(Context context) {

  }

  @Override
  public void show(Context context) {

  }

  @Override
  public void create(Context context) {
    Map<String, Object> model = new HashMap<>();
    model.put("modelos", this.modelosService.obtenerModelosDisponibles());
    model.put("message", context.queryParam("message"));
    context.status(201);
    context.render("app/colaboraciones/cargo-heladera.hbs", model);
  }

  @Override
  public void save(Context context) {
    HeladeraInputDto dto = HeladeraInputDto.of(context);

    this.colocacionHeladerasService.crearColocacionHeladera(dto);

    context.status(201);
    ServiceLocator.get(StepMeterRegistry.class).counter("Heladeras_colocadas").increment();
    context.redirect("/heladeras");
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
