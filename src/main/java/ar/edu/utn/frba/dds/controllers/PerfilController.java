package ar.edu.utn.frba.dds.controllers;

import ar.edu.utn.frba.dds.dtos.CanjeOutputDto;
import ar.edu.utn.frba.dds.dtos.TipoDocumentoDto;
import ar.edu.utn.frba.dds.dtos.personas.ColaboradorPerfilDto;
import ar.edu.utn.frba.dds.dtos.personas.TipoOrganizacionDto;
import ar.edu.utn.frba.dds.models.domain.colaboradores.Colaborador;
import ar.edu.utn.frba.dds.models.domain.colaboradores.TipoPersonaJuridica;
import ar.edu.utn.frba.dds.models.domain.utils.TipoDocumento;
import ar.edu.utn.frba.dds.services.ColaboradoresService;
import ar.edu.utn.frba.dds.services.OfertasProductoService;
import ar.edu.utn.frba.dds.utils.ICrudViewsHandler;
import io.javalin.http.Context;
import java.util.Arrays;
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
    ColaboradorPerfilDto colaboradorPerfilDto = ColaboradorPerfilDto.of(context);
    colaboradoresService.actualizar(colaboradorPerfilDto);

    Map<String, Object> model = new HashMap<>();
    model.put("message", "Perfil actualizado correctamente");
    context.render("/app/success.hbs", model);
  }

  @Override
  public void delete(Context context) {

  }
}
