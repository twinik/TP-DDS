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

  }

  @Override
  public void save(Context context) {

  }

  @Override
  public void edit(Context context) {

    //TODO: generar vista para actualizar la heladera
    if(context.pathParam("id") != context.sessionAttribute("idColaborador")) throw new NoAutorizadoException(MensajeNoAutorizadoFactory.generarMensaje());

  }

  @Override
  public void update(Context context) {
    HeladeraDto heladeraDto = HeladeraDto.of(context);
    if(heladeraDto == null) throw new FormIncompletoException(MensajeFormIncompletoFactory.generarMensaje());
    this.heladerasService.actualizarHeladera(heladeraDto,context.sessionAttribute("idColaborador"));
  }

  @Override
  public void delete(Context context) {
    this.heladerasService.eliminarHeladera(context.pathParam("id"),context.sessionAttribute("idColaborador"));
  }

  public void getHeladerasMapa(Context context){
    context.json(this.heladerasService.getHeladerasParaMapa());
  }

  public void getHeladerasAptasDonacion(Context context){
    context.json(this.heladerasService.getHeladerasParaDonar());
  }
}
