package ar.edu.utn.frba.dds.controllers;

import ar.edu.utn.frba.dds.dtos.TipoDocumentoDto;
import ar.edu.utn.frba.dds.dtos.tecnicos.TecnicoDto;
import ar.edu.utn.frba.dds.exceptions.FormIncompletoException;
import ar.edu.utn.frba.dds.models.domain.utils.TipoDocumento;
import ar.edu.utn.frba.dds.models.messageFactory.MensajeFormIncompletoFactory;
import ar.edu.utn.frba.dds.services.TecnicosService;
import ar.edu.utn.frba.dds.utils.ICrudViewsHandler;
import io.javalin.http.Context;
import lombok.AllArgsConstructor;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
public class TecnicosController implements ICrudViewsHandler {
  private TecnicosService tecnicosService;

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
    model.put("datosForm", context.consumeSessionAttribute("formDto"));
    model.put("message", context.queryParam("message"));
    context.render("/app/admin/alta-tecnico.hbs", model);
  }

  @Override
  public void save(Context context) {
    TecnicoDto dto = TecnicoDto.of(context);
    if (!dto.estanCamposLlenos())
      throw new FormIncompletoException(MensajeFormIncompletoFactory.generarMensaje(), dto);

    this.tecnicosService.crearTecnico(dto);
    Map<String, Object> model = new HashMap<>();
    model.put("message", "El tecnico: " + dto.getNombre() + " " + dto.getApellido() + " fue registrado con exito");
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
