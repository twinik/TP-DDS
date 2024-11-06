package ar.edu.utn.frba.dds.controllers;

import ar.edu.utn.frba.dds.dtos.TipoDocumentoDto;
import ar.edu.utn.frba.dds.dtos.personas.PersonaHumanaDto;
import ar.edu.utn.frba.dds.dtos.personas.PersonaJuridicaDto;
import ar.edu.utn.frba.dds.dtos.personas.TipoOrganizacionDto;
import ar.edu.utn.frba.dds.exceptions.ClaveDebilException;
import ar.edu.utn.frba.dds.exceptions.ClaveNoCoincidenException;
import ar.edu.utn.frba.dds.exceptions.FormIncompletoException;
import ar.edu.utn.frba.dds.exceptions.RecursoInexistenteException;
import ar.edu.utn.frba.dds.exceptions.RegistroFailedException;
import ar.edu.utn.frba.dds.helpers.ValidadorClaves;
import ar.edu.utn.frba.dds.helpers.factories.ValidadorFactory;
import ar.edu.utn.frba.dds.models.domain.colaboradores.TipoPersonaJuridica;
import ar.edu.utn.frba.dds.models.domain.utils.TipoDocumento;
import ar.edu.utn.frba.dds.models.messageFactory.MensajeFormIncompletoFactory;
import ar.edu.utn.frba.dds.serviceLocator.ServiceLocator;
import ar.edu.utn.frba.dds.services.ColaboradoresService;
import ar.edu.utn.frba.dds.services.FormaColaboracionService;
import ar.edu.utn.frba.dds.services.UsuarioService;
import ar.edu.utn.frba.dds.utils.ICrudViewsHandler;
import io.javalin.http.Context;
import io.micrometer.core.instrument.step.StepMeterRegistry;
import lombok.AllArgsConstructor;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Se encarga de controlar los Registros.
 */

@AllArgsConstructor
public class RegistroController implements ICrudViewsHandler {
  private UsuarioService usuarioService;
  private ColaboradoresService colaboradoresService;
  private FormaColaboracionService formaColaboracionService;

  public void handleRegistroHumano(Context ctx) {
    PersonaHumanaDto nuevaPersonaHumana = PersonaHumanaDto.of(ctx);
    if (!nuevaPersonaHumana.estanCamposLlenos())
      throw new FormIncompletoException(MensajeFormIncompletoFactory.generarMensaje(), nuevaPersonaHumana);
    this.validarContra(nuevaPersonaHumana);
    try {
      String idNuevoColab = this.colaboradoresService.registrar(nuevaPersonaHumana);
      ctx.redirect("/responder-formulario/colaborador/" + idNuevoColab);
    } catch (RegistroFailedException e) {
      ctx.status(400);
      ctx.result("El registro ha fallado: " + e.getMessage());
    }
  }

  public void handleRegistroJuridico(Context ctx) {
    PersonaJuridicaDto personaJuridicaDto = PersonaJuridicaDto.of(ctx);
    if (!personaJuridicaDto.estanCamposLlenos())
      throw new FormIncompletoException(MensajeFormIncompletoFactory.generarMensaje(), personaJuridicaDto);
    this.validarContra(personaJuridicaDto);
    try {
      this.colaboradoresService.registrar(personaJuridicaDto);
      ctx.status(201);
      Map<String, Object> model = new HashMap<>();
      model.put("message", "Felicidades! su cuenta ha sido creada");
      ctx.render("/auth/registro/form-success.hbs", model);
    } catch (RegistroFailedException e) {
      ctx.status(400);
      ServiceLocator.get(StepMeterRegistry.class).counter("Registro","status","failed").increment();
      ctx.result("El registro ha fallado: " + e.getMessage());
    }
  }

  private void validarContra(PersonaHumanaDto personaHumanaDto) {
    if (!personaHumanaDto.sonClavesIguales()) {
      ServiceLocator.get(StepMeterRegistry.class).counter("Registro", "status", "failed").increment();
      throw new ClaveNoCoincidenException(personaHumanaDto);
    }
    ValidadorClaves validador = ValidadorFactory.create();
    if (!validador.esValida(personaHumanaDto.getUsuarioDto().getClave())) {
      ServiceLocator.get(StepMeterRegistry.class).counter("Registro", "status", "failed").increment();
      throw new ClaveDebilException(validador.getMotivoNoValida().getMotivo(), personaHumanaDto);
    }
  }

  private void validarContra(PersonaJuridicaDto personaJuridicaDto) {
    if (!personaJuridicaDto.sonClavesIguales()) {
      ServiceLocator.get(StepMeterRegistry.class).counter("Registro", "status", "failed").increment();
      throw new ClaveNoCoincidenException(personaJuridicaDto);
    }
    ValidadorClaves validador = ValidadorFactory.create();
    if (!validador.esValida(personaJuridicaDto.getUsuarioDto().getClave())) {
      ServiceLocator.get(StepMeterRegistry.class).counter("Registro", "status", "failed").increment();
      throw new ClaveDebilException(validador.getMotivoNoValida().getMotivo(), personaJuridicaDto);
    }
  }

  @Override
  public void index(Context context) {
    context.render("/auth/registro/registro.hbs");
  }

  @Override
  public void show(Context context) {

  }

  @Override
  public void create(Context context) {
    Map<String, Object> model = new HashMap<>();
    model.put("datosForm", context.consumeSessionAttribute("formDto"));
    if (context.pathParam("tipo-persona").equals("persona-humana")) {
      model.put("tiposDocumento", Arrays.stream(TipoDocumento.values()).map(TipoDocumentoDto::fromTipoDocumento).toList());
      model.put("formasColaboracion", this.formaColaboracionService.obtenerFormas("DONACION_DINERO", "DONACION_VIANDA", "REDISTRIBUCION_VIANDA", "REGISTRO_PERSONA"));
      model.put("message", context.queryParam("message"));
      context.render("/auth/registro/registro-humano.hbs", model);
    } else if (context.pathParam("tipo-persona").equals("persona-juridica")) {
      model.put("tiposOrganizacion", Arrays.stream(TipoPersonaJuridica.values()).map(TipoOrganizacionDto::fromTipoOrganizacion).toList());
      model.put("formasColaboracion", this.formaColaboracionService.obtenerFormas("DONACION_DINERO", "COLOCACION_HELADERA", "OFRECER_PRODUCTOS"));
      model.put("message", context.queryParam("message"));
      context.render("/auth/registro/registro-juridico.hbs", model);
    } else if (context.pathParam("tipo-persona").equals("admin")) {
      // TODO: registro admin????
      context.redirect("/");
    } else {
      throw new RecursoInexistenteException("No existe registro para este recurso");
    }
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
