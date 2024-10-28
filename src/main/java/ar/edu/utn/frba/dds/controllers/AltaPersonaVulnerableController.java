package ar.edu.utn.frba.dds.controllers;

import ar.edu.utn.frba.dds.dtos.TipoDocumentoDto;
import ar.edu.utn.frba.dds.dtos.colaboraciones.AltaPersonaVulnerableDto;
import ar.edu.utn.frba.dds.dtos.colaboraciones.TutoradoInputDto;
import ar.edu.utn.frba.dds.models.domain.utils.TipoDocumento;
import ar.edu.utn.frba.dds.services.AltaPersonaVulnerableService;
import ar.edu.utn.frba.dds.utils.ICrudViewsHandler;
import io.javalin.http.Context;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AltaPersonaVulnerableController implements ICrudViewsHandler {
  private AltaPersonaVulnerableService service;

  @Override
  public void index(Context context) {

  }

  @Override
  public void show(Context context) {

  }

  @Override
  public void create(Context context) {
    Map<String, Object> model = new HashMap<>();
    model.put("tiposDocumento", Arrays.stream(TipoDocumento.values()).map(TipoDocumentoDto::fromTipoDocumento).toList());
    model.put("datosForm", context.consumeSessionAttribute("formDto"));
    model.put("message", context.queryParam("message"));
    context.render("/app/colaboraciones/alta-persona-vulnerable.hbs", model);
  }

  public void createTutorados(Context context) {
    Map<String, Object> model = new HashMap<>();
    model.put("tiposDocumento", Arrays.stream(TipoDocumento.values()).map(TipoDocumentoDto::fromTipoDocumento).toList());

    List<Integer> menores = new ArrayList<>();
    for (int i = 1; i <= Integer.parseInt(context.sessionAttribute("cantMenores")); i++) {
      menores.add(i);
    }
    model.put("menores", menores);
    model.put("datosForm", context.consumeSessionAttribute("formDto"));
    model.put("message", context.queryParam("message"));
    context.render("/app/colaboraciones/alta-hijo-vulnerable.hbs", model);
  }

  @Override
  public void save(Context context) {
    AltaPersonaVulnerableDto dto = AltaPersonaVulnerableDto.of(context);
    String idPersona = this.service.darAltaPersonaVulnerable(dto);

    if (context.formParam("tiene-tutorados").equals("si")) {
      context.sessionAttribute("cantMenores", dto.getCantidadTutorados());
      context.sessionAttribute("domicilioFamiliaVulnerable", dto.getDomicilio());
      context.redirect("/colaborar/registrar-persona-vulnerable/" + idPersona + "/registrar-tutorados");
    } else {
      Map<String, Object> model = new HashMap<>();
      model.put("message", "El alta de la persona: " + dto.getNombre() + " " + dto.getApellido() + " fue registrado con exito");
      context.status(201);
      context.render("/app/success.hbs", model);
    }
  }

  public void saveTutorados(Context context) {
    for (int i = 1; i <= Integer.parseInt(context.sessionAttribute("cantMenores")); i++) {
      TutoradoInputDto dto = TutoradoInputDto.of(context, i);
      this.service.darAltaTutorados(dto, context.pathParam("id"));
    }

    context.sessionAttribute("cantMenores", null);
    context.sessionAttribute("domicilioFamiliaVulnerable", null);
    Map<String, Object> model = new HashMap<>();
    model.put("message", "El alta de los tutorados fue registrado con exito");
    context.status(201);
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
