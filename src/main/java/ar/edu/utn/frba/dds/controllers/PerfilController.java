package ar.edu.utn.frba.dds.controllers;

import ar.edu.utn.frba.dds.dtos.CanjeOutputDto;
import ar.edu.utn.frba.dds.dtos.TipoDocumentoDto;
import ar.edu.utn.frba.dds.dtos.personas.ColaboradorPerfilDto;
import ar.edu.utn.frba.dds.dtos.personas.FormaColaboracionOutputDto;
import ar.edu.utn.frba.dds.dtos.personas.TipoOrganizacionDto;
import ar.edu.utn.frba.dds.models.domain.colaboradores.Colaborador;
import ar.edu.utn.frba.dds.models.domain.colaboradores.TipoPersonaJuridica;
import ar.edu.utn.frba.dds.models.domain.utils.TipoDocumento;
import ar.edu.utn.frba.dds.services.ColaboradoresService;
import ar.edu.utn.frba.dds.services.FormaColaboracionService;
import ar.edu.utn.frba.dds.services.OfertasProductoService;
import ar.edu.utn.frba.dds.utils.ICrudViewsHandler;
import io.javalin.http.Context;
import lombok.AllArgsConstructor;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
public class PerfilController implements ICrudViewsHandler {
  private OfertasProductoService ofertasProductoService;
  private ColaboradoresService colaboradoresService;
  private FormaColaboracionService formaColaboracionService;

  @Override
  public void index(Context ctx) {
    String idColaborador = ctx.sessionAttribute("idColaborador");
    Colaborador colaborador = colaboradoresService.obtenerColaborador(idColaborador);
    ColaboradorPerfilDto colaboradorPerfilDto = ColaboradorPerfilDto.fromColaborador(colaborador);

    if (colaboradorPerfilDto.getFechaNacimiento().equals("null")) {
      colaboradorPerfilDto.setFechaNacimiento(null);
    }

    Map<String, Object> model = new HashMap<>();
    List<CanjeOutputDto> canjes = ofertasProductoService.obtenerCanjes(idColaborador);

    model.put("colaborador", colaboradorPerfilDto);
    model.put("tiposOrganizacion", Arrays.stream(TipoPersonaJuridica.values()).map(TipoOrganizacionDto::fromTipoOrganizacion).toList());
    model.put("tiposDocumento", Arrays.stream(TipoDocumento.values()).map(TipoDocumentoDto::fromTipoDocumento).toList());
    List<FormaColaboracionOutputDto> formitas = colaboradorPerfilDto.getTipoPersonaJuridica() != null ? this.formaColaboracionService.obtenerFormas("DONACION_DINERO", "COLOCACION_HELADERA", "OFRECER_PRODUCTOS") : this.formaColaboracionService.obtenerFormas("DONACION_DINERO", "DONACION_VIANDA", "REDISTRIBUCION_VIANDA", "REGISTRO_PERSONA");


    model.put("formasColaboracion", formitas.stream().filter(f -> colaboradorPerfilDto.getFormaColaboracionDtos().stream().noneMatch(forma -> f.getDesc().equals(forma.getDesc()))).toList());
    model.put("canjes", canjes);
    model.put("message", ctx.queryParam("message"));

    ctx.render("app/perfil.hbs", model);
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
    ColaboradorPerfilDto colaboradorPerfilDto = ColaboradorPerfilDto.of(context);
    if (colaboradorPerfilDto.getDireccionDto() != null) {
      colaboradorPerfilDto.getDireccionDto().estanCamposLlenos(colaboradorPerfilDto);
    }
    Map<String, Object> model = new HashMap<>();
//    try {
    colaboradoresService.actualizar(colaboradorPerfilDto);
    if (colaboradorPerfilDto.getNombre() != null) {
      context.sessionAttribute("username", colaboradorPerfilDto.getNombre() + " " + colaboradorPerfilDto.getApellido());
    } else {
      context.sessionAttribute("username", colaboradorPerfilDto.getRazonSocial());
    }
    context.sessionAttribute("email", colaboradorPerfilDto.getEmail());
    model.put("message", "Perfil actualizado correctamente");
    context.render("app/success.hbs", model);
//    } catch (Exception e) {
//      e.printStackTrace();
//      model.put("message", "Error al actualizar el perfil");
//      context.render("app/error.hbs", model);
//    }
  }

  @Override
  public void delete(Context context) {

  }

  public void handlePerfil(Context context) {
    context.redirect("/perfil/" + context.sessionAttribute("idColaborador"));
  }
}
