package ar.edu.utn.frba.dds.controllers;

import ar.edu.utn.frba.dds.dtos.formularios.RespuestaFormularioDto;
import ar.edu.utn.frba.dds.dtos.formularios.ShowFormularioDto;
import ar.edu.utn.frba.dds.exceptions.NoAutorizadoException;
import ar.edu.utn.frba.dds.services.FormulariosService;
import ar.edu.utn.frba.dds.services.RespuestaFormularioService;
import ar.edu.utn.frba.dds.utils.ICrudViewsHandler;
import io.javalin.http.Context;
import lombok.AllArgsConstructor;
import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
public class RespuestaFormularioController implements ICrudViewsHandler {

  RespuestaFormularioService rtaService;
  FormulariosService formService;

  @Override
  public void index(Context context) {

  }

  @Override
  public void show(Context context) {

  }

  @Override
  public void create(Context context) {
    if (context.sessionAttribute("idColaborador") != context.pathParam("idColaborador")) {
      throw new NoAutorizadoException("No estas autorizado para responder este formulario");
    }
    Map<String, Object> model = new HashMap<>();
    model.put("formulario", ShowFormularioDto.fromFormulario(formService.obtenerFormulario(context.pathParam("idFormulario"))));
    context.render("auth/registro/formulario-colaborador.hbs", model);
  }

  @Override
  public void save(Context context) {
    rtaService.crearRespuestaFormulario(RespuestaFormularioDto.fromContext(context));
    Map<String, Object> model = new HashMap<>();
    model.put("message", "Su respuesta fue guardad con exito! Gracias!");
    context.render("app/success.hbs", model);
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
