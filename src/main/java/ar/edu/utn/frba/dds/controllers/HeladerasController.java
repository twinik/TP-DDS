package ar.edu.utn.frba.dds.controllers;

import ar.edu.utn.frba.dds.dtos.heladeras.HeladeraDto;
import ar.edu.utn.frba.dds.exceptions.FormIncompletoException;
import ar.edu.utn.frba.dds.exceptions.NoAutorizadoException;
import ar.edu.utn.frba.dds.models.messageFactory.MensajeFormIncompletoFactory;
import ar.edu.utn.frba.dds.models.messageFactory.MensajeNoAutorizadoFactory;
import ar.edu.utn.frba.dds.services.HeladerasService;
import ar.edu.utn.frba.dds.utils.ICrudViewsHandler;
import io.javalin.http.Context;
import lombok.AllArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
public class HeladerasController implements ICrudViewsHandler {
  private HeladerasService heladerasService;

  @Override
  public void index(Context context) {
    context.render("/app/heladeras/heladeras.hbs");
  }

  @Override
  public void show(Context context) {

  }

  @Override
  public void create(Context context) {
    context.render("/app/heladeras/mis-heladeras.hbs");
    context.redirect("/heladeras/" + context.sessionAttribute("idColaborador") + "/mis-heladeras");
  }

  @Override
  public void save(Context context) {

  }

  @Override
  public void edit(Context context) {
    //TODO: generar vista para actualizar la heladera
    Map<String, Object> model = new HashMap<>();
    model.put("heladeras", this.heladerasService.obtenerHeladerasColaborador(context.sessionAttribute("idColaborador")));
    context.render("/app/heladeras/mis-heladeras.hbs", model);
  }

  @Override
  public void update(Context context) {
    HeladeraDto heladeraDto = HeladeraDto.of(context);
    if (heladeraDto == null) throw new FormIncompletoException(MensajeFormIncompletoFactory.generarMensaje());
    Map<String, Object> model = new HashMap<>();
    this.heladerasService.actualizarHeladera(heladeraDto, context.sessionAttribute("idColaborador"));
    model.put("message", "La heladera fue editada exitosamente");
    context.render("/app/success.hbs", model);
  }

  @Override
  public void delete(Context context) {
    this.heladerasService.eliminarHeladera(context.pathParam("id"), context.sessionAttribute("idColaborador"));
    Map<String, Object> model = new HashMap<>();
    model.put("message", "La heladera fue dada de baja exitosamente");
    context.render("/app/success.hbs", model);
  }

  public void getHeladerasMapa(Context context) {
    context.json(this.heladerasService.getHeladerasParaMapa());
  }

  public void getHeladerasAptasDonacion(Context context) {
    context.json(this.heladerasService.getHeladerasParaDonar());
  }
}
