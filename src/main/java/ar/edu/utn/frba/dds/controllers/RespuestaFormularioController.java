package ar.edu.utn.frba.dds.controllers;

import ar.edu.utn.frba.dds.dtos.formularios.RespuestaFormularioDto;
import ar.edu.utn.frba.dds.dtos.formularios.ShowFormularioDto;
import ar.edu.utn.frba.dds.models.domain.colaboradores.form.Formulario;
import ar.edu.utn.frba.dds.serviceLocator.ServiceLocator;
import ar.edu.utn.frba.dds.services.ColaboradoresService;
import ar.edu.utn.frba.dds.services.FormulariosService;
import ar.edu.utn.frba.dds.services.RespuestaFormularioService;
import ar.edu.utn.frba.dds.utils.ICrudViewsHandler;
import io.javalin.http.Context;
import lombok.AllArgsConstructor;
import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
public class RespuestaFormularioController implements ICrudViewsHandler {

  private RespuestaFormularioService rtaService;
  private FormulariosService formService;

  private ColaboradoresService colaboradoresService;

  @Override
  public void index(Context context) {

  }

  @Override
  public void show(Context context) {

  }

  public void obtenerFormulario(Context context) {
    // obtiene el ultimo formulario activo. Si existe, lo hace contestarlo. Si no existe, pasa directo a login
    String idColaborador = context.pathParam("idColaborador");
    Formulario form = this.formService.obtenerUltimo();
    if (form == null) {
      this.colaboradoresService.marcarFormCompletado(idColaborador);
      context.redirect("/login");
    } else {
      context.redirect("/responder-formulario/" + form.getId() + "/colaborador/" + idColaborador);
    }
  }

  @Override
  public void create(Context context) {
    Map<String, Object> model = new HashMap<>();
    model.put("formulario", ShowFormularioDto.fromFormulario(formService.obtenerFormulario(context.pathParam("idFormulario"))));
    context.render("auth/registro/formulario-colaborador.hbs", model);
  }

  @Override
  public void save(Context context) {
    rtaService.crearRespuestaFormulario(RespuestaFormularioDto.fromContext(context));
//    Map<String, Object> model = new HashMap<>();
//    model.put("message", "Su respuesta fue guardada con exito! Gracias!");
//    context.render("auth/registro/form-success.hbs", model);
    context.redirect("/login");
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
