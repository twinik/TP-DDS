package ar.edu.utn.frba.dds.controllers;

import ar.edu.utn.frba.dds.dtos.TipoDocumentoDto;
import ar.edu.utn.frba.dds.dtos.colaboraciones.AltaPersonaVulnerableDto;
import ar.edu.utn.frba.dds.exceptions.FormIncompletoException;
import ar.edu.utn.frba.dds.models.domain.utils.TipoDocumento;
import ar.edu.utn.frba.dds.services.AltaPersonaVulnerableService;
import ar.edu.utn.frba.dds.utils.ICrudViewsHandler;
import io.javalin.http.Context;
import java.util.Arrays;
import lombok.AllArgsConstructor;
import java.util.HashMap;
import java.util.Map;

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
    HashMap<String, Object> model = new HashMap<>();
    model.put("tiposDocumento", Arrays.stream(TipoDocumento.values()).map(TipoDocumentoDto::fromTipoDocumento).toList());
    context.render("/app/colaboraciones/alta-persona-vulnerable.hbs", model);
  }

  @Override
  public void save(Context context) {
    AltaPersonaVulnerableDto dto = AltaPersonaVulnerableDto.of(context);

    if (context.formParam("tiene-tutorados").equals("si")) {
      String idPersona = this.service.crearPersonaVulnerable(dto);
      context.redirect("/colaborar/registrar-persona-vulnerable/" + idPersona + "/registrar-tutorados");
    } else {
      try {
        this.service.darAltaPersonaVulnerable(dto);
        Map<String, Object> model = new HashMap<>();
        model.put("message", "El alta de la persona: " + dto.getNombre() + " " + dto.getApellido() + " fue registrado con exito");
        context.render("/app/success.hbs", model);
      } catch (FormIncompletoException e) {
        // TODO: Mostrar pop up error ?
      }
    }
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
