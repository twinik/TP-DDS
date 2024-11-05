package ar.edu.utn.frba.dds.controllers;

import ar.edu.utn.frba.dds.dtos.colaboraciones.RedistribucionViandaDto;
import ar.edu.utn.frba.dds.exceptions.FormIncompletoException;
import ar.edu.utn.frba.dds.exceptions.MismaHeladeraException;
import ar.edu.utn.frba.dds.models.messageFactory.MensajeFormIncompletoFactory;
import ar.edu.utn.frba.dds.models.messageFactory.MensajeMismaHeladeraFactory;
import ar.edu.utn.frba.dds.serviceLocator.ServiceLocator;
import ar.edu.utn.frba.dds.services.MotivoRedistribucionService;
import ar.edu.utn.frba.dds.services.RedistribucionViandaService;
import ar.edu.utn.frba.dds.utils.ICrudViewsHandler;
import io.javalin.http.Context;
import io.micrometer.core.instrument.step.StepMeterRegistry;
import lombok.AllArgsConstructor;
import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
public class RedistribucionViandaController implements ICrudViewsHandler {

  private RedistribucionViandaService redistribucionViandasService;
  private MotivoRedistribucionService motivoRedistribucionService;

  @Override
  public void index(Context context) {

  }

  @Override
  public void show(Context context) {

  }

  @Override
  public void create(Context context) {
    Map<String, Object> model = new HashMap<>();
    model.put("message", context.queryParam("message"));
    model.put("datosForm", context.consumeSessionAttribute("formDto"));
    model.put("motivos", this.motivoRedistribucionService.obtenerMotivos());
    context.render("/app/colaboraciones/distribucion-vianda.hbs", model);
  }

  @Override
  public void save(Context context) {
    RedistribucionViandaDto dto = RedistribucionViandaDto.of(context);
    if (!dto.estanCamposLlenos()) throw new FormIncompletoException(MensajeFormIncompletoFactory.generarMensaje());
    if (dto.getDestino().getId().equals(dto.getOrigen().getId()))
      throw new MismaHeladeraException(MensajeMismaHeladeraFactory.generarMensaje());
    this.redistribucionViandasService.solicitarRedistribucion(dto);
    Map<String, Object> model = new HashMap<>();
    model.put("message", "Su solcitud de redistribución ha sido registrada con éxito, esperamos su donación con ansias");
    context.status(201);
    ServiceLocator.get(StepMeterRegistry.class).counter("Redistribucion_de_viandas").increment();
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
