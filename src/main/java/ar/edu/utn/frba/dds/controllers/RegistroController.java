package ar.edu.utn.frba.dds.controllers;

import ar.edu.utn.frba.dds.dtos.TipoDocumentoDto;
import ar.edu.utn.frba.dds.dtos.personas.PersonaHumanaDto;
import ar.edu.utn.frba.dds.dtos.personas.PersonaJuridicaDto;
import ar.edu.utn.frba.dds.dtos.usuarios.UsuarioDto;
import ar.edu.utn.frba.dds.exceptions.ClaveDebilException;
import ar.edu.utn.frba.dds.exceptions.ClaveNoCoincidenException;
import ar.edu.utn.frba.dds.exceptions.RecursoInexistenteException;
import ar.edu.utn.frba.dds.exceptions.RegistroFailedException;
import ar.edu.utn.frba.dds.helpers.ValidadorClaves;
import ar.edu.utn.frba.dds.helpers.factories.ValidadorFactory;
import ar.edu.utn.frba.dds.models.domain.colaboradores.FormaColaboracion;
import ar.edu.utn.frba.dds.models.domain.utils.Direccion;
import ar.edu.utn.frba.dds.models.domain.utils.MedioDeContacto;
import ar.edu.utn.frba.dds.models.domain.utils.TipoDocumento;
import ar.edu.utn.frba.dds.services.ColaboradoresService;
import ar.edu.utn.frba.dds.services.FormaColaboracionService;
import ar.edu.utn.frba.dds.services.UsuarioService;
import ar.edu.utn.frba.dds.utils.ICrudViewsHandler;
import io.javalin.http.Context;
import lombok.AllArgsConstructor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
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
    if (!nuevaPersonaHumana.sonClavesIguales()) throw new ClaveNoCoincidenException();
    this.validarContra(nuevaPersonaHumana.getUsuarioDto().getClave());
    try {
      this.colaboradoresService.registrar(nuevaPersonaHumana);
      ctx.redirect("/login");
    } catch (RegistroFailedException e) {
      ctx.status(400);
      ctx.result("El registro ha fallado: " + e.getMessage());
    }
  }

  private void validarContra(String contra) {

    ValidadorClaves validador = ValidadorFactory.create();
    if (!validador.esValida(contra)) throw new ClaveDebilException(validador.getMotivoNoValida().getMotivo());
  }

  public void handleRegistroJuridico(Context ctx) {
    try {
      String email = ctx.formParam("email");
      String clave = ctx.formParam("password");
      String claveConf = ctx.formParam("passConf");
      String razonSocial = ctx.formParam("razonSocial");
      String tipoOrganizacion = ctx.formParam("tipoOrganizacion");
      String rubro = ctx.formParam("rubro");
      String calle = ctx.formParam("calle");
      Integer altura = Integer.parseInt(ctx.formParam("altura"));
      Integer piso = Integer.parseInt(ctx.formParam("piso"));
      String codigoPostal = ctx.formParam("cp");
      Direccion direccion = new Direccion(calle, altura, piso, codigoPostal);

      // TODO: formasColaboracion y mediosDeContacto
      //  List<FormaColaboracion> formasColaboracion = ctx.formParams("formasColaboracion").stream().map(FormaColaboracion::new).toList();
      List<MedioDeContacto> mediosDeContacto = new ArrayList<>();
      //List<MedioDeContacto> mediosDeContacto = ctx.formParams("mediosDeContacto").stream().map(MedioDeContacto::new).toList();

      if (!clave.equals(claveConf)) {
        throw new RegistroFailedException("Las contraseñas no coinciden");
      }

      //PersonaJuridicaDto nuevaPersonaJuridica = new PersonaJuridicaDto(razonSocial, tipoOrganizacion, rubro, direccion, formasColaboracion, mediosDeContacto);

      UsuarioDto nuevoUsuario = new UsuarioDto(email, clave);
      //this.colaboradoresService.registrar(nuevoUsuario);

      ctx.redirect("/login");
    } catch (RegistroFailedException e) {
      ctx.status(400);
      ctx.result("El registro ha fallado: " + e.getMessage());
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
    if (context.pathParam("tipo-persona").equals("humana")) {
      model.put("tiposDocumento", Arrays.stream(TipoDocumento.values()).map(TipoDocumentoDto::fromTipoDocumento).toList());
      model.put("formasColaboracion", this.formaColaboracionService.obtenerFormas("DONACION_DINERO", "DONACION_VIANDA", "REDISTRIBUCION_VIANDA", "REGISTRO_PERSONA"));
      context.render("/auth/registro/registro-humano.hbs", model);
    } else if (context.pathParam("tipo-persona").equals("juridica")) {
      model.put("formasColaboracion", this.formaColaboracionService.obtenerFormas("DONACION_DINERO", "COLOCACION_HELADERA", "REGISTRO_PERSONA", "OFRECER_PRODUCTOS"));
      context.render("/auth/registro/registro-juridico.hbs", model);
    } else if (context.pathParam("tipo-persona").equals("admin")) {
      // TODO: registro admin????
      context.redirect("/");
    } else {
      throw new RecursoInexistenteException("no existe registro para este recurso");
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
