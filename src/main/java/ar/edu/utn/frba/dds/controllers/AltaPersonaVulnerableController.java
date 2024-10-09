package ar.edu.utn.frba.dds.controllers;

import ar.edu.utn.frba.dds.dtos.colaboraciones.AltaPersonaVulnerableDto;
import ar.edu.utn.frba.dds.exceptions.FormIncompletoException;
import ar.edu.utn.frba.dds.services.AltaPersonaVulnerableService;
import ar.edu.utn.frba.dds.utils.ICrudViewsHandler;
import io.javalin.http.Context;
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
    context.render("/app/colaboraciones/alta-persona-vulnerable.hbs");
  }

  @Override
  public void save(Context context) {
    AltaPersonaVulnerableDto dto = AltaPersonaVulnerableDto.of(context);

    if (dto.getTieneTutorados() == "si") {

      context.redirect("/colaborar/registrar-persona-vulnerable/registrar-tutorados");
    } else {
      try {
        this.service.darAltaPersonaVulnerable(dto);
      } catch (FormIncompletoException e) {
        // TODO: Mostrar pop up error ?
      }
      //TODO: mostrar cartel de creado con exito
      context.redirect("/colaborar");
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
