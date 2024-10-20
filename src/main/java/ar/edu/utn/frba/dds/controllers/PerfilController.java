package ar.edu.utn.frba.dds.controllers;

import ar.edu.utn.frba.dds.dtos.CanjeOutputDto;
import ar.edu.utn.frba.dds.models.domain.colaboradores.Colaborador;
import ar.edu.utn.frba.dds.services.ColaboradoresService;
import ar.edu.utn.frba.dds.services.OfertasProductoService;
import ar.edu.utn.frba.dds.utils.ICrudViewsHandler;
import io.javalin.http.Context;
import lombok.AllArgsConstructor;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
public class PerfilController implements ICrudViewsHandler {

  OfertasProductoService ofertasProductoService;
  ColaboradoresService colaboradoresService;


  @Override
  public void index(Context ctx) {
    String idColaborador = ctx.sessionAttribute("idColaborador");

//        if (idColaborador == null) {
//            ctx.redirect("/login");
//            return;
//        }
//
//
//
//
//        if (colaborador == null) {
//            ctx.redirect("/login");
//            return;
//        }

    Colaborador colaborador = colaboradoresService.obtenerColaborador(idColaborador);

    // Crea un modelo con los datos del usuario
    // TODO: usar el dto 
    Map<String, Object> model = new HashMap<>();
    model.put("nombre", colaborador.getNombre());
    model.put("apellido", colaborador.getApellido());
    model.put("email", colaborador.getUsuario().getEmail());
    model.put("documento", colaborador.getDocumento());
    model.put("tipoDocumento", colaborador.getTipoDocumento());
    model.put("calle", colaborador.getDireccion().getCalle());
    model.put("altura", colaborador.getDireccion().getAltura());
    model.put("piso", colaborador.getDireccion().getPiso());
    model.put("codigoPostal", colaborador.getDireccion().getCodigoPostal());
    model.put("mediosDeContacto", colaborador.getMedioContacto());
    model.put("fechaNacimiento", colaborador.getFechaNacimiento());
    model.put("rubro", colaborador.getRubro());
    model.put("razonSocial", colaborador.getRazonSocial());
    model.put("tipoPersonaJuridica", colaborador.getTipoPersonaJuridica());
    model.put("formCompletado", colaborador.getFormCompletado());
    model.put("puntosGanados", colaborador.getPuntosGanados());

    List<CanjeOutputDto> canjes = ofertasProductoService.obtenerCanjes(idColaborador);
    model.put("canjes", canjes);

    ctx.render("/app/perfil.hbs", model);
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
