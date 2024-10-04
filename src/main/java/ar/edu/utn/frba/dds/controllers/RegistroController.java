package ar.edu.utn.frba.dds.controllers;

import static ar.edu.utn.frba.dds.helpers.DateHelper.fechaFromString;

import ar.edu.utn.frba.dds.dtos.UsuarioDto;
import ar.edu.utn.frba.dds.dtos.personas.PersonaHumanaDto;
import ar.edu.utn.frba.dds.dtos.personas.PersonaJuridicaDto;
import ar.edu.utn.frba.dds.models.domain.colaboradores.FormaColaboracion;
import ar.edu.utn.frba.dds.models.domain.utils.Direccion;
import ar.edu.utn.frba.dds.models.domain.utils.MedioDeContacto;
import ar.edu.utn.frba.dds.services.RegistroFailedException;
import ar.edu.utn.frba.dds.services.UsuarioService;
import ar.edu.utn.frba.dds.utils.ICrudViewsHandler;
import io.javalin.http.Context;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;

/**
 * Se encarga de controlar los Registros.
 */

@AllArgsConstructor
public class RegistroController implements ICrudViewsHandler {
  private UsuarioService usuarioService;

  public void handleRegistroHumano(Context ctx) {
    try {
      String email = ctx.formParam("email");
      String clave = ctx.formParam("pass");
      String claveConf = ctx.formParam("passConf");
      String nombre = ctx.formParam("nombre");
      String apellido = ctx.formParam("apellido");
      LocalDate fechaNacimiento = fechaFromString(ctx.formParam("fechaNacimiento"), "dd/MM/yyyy");
      String calle = ctx.formParam("calle");
      Integer altura = Integer.parseInt(ctx.formParam("altura"));
      Integer piso = Integer.parseInt(ctx.formParam("piso"));
      String codigoPostal = ctx.formParam("cp");
      Direccion direccion = new Direccion(calle, altura, piso, codigoPostal);

      // TODO: formasColaboracion y mediosDeContacto
      List<FormaColaboracion> formasColaboracion = new ArrayList<>();
      List<MedioDeContacto> mediosDeContacto = new ArrayList<>();

      if (!clave.equals(claveConf)) {
        throw new RegistroFailedException("Las contraseñas no coinciden");
      }

      PersonaHumanaDto nuevaPersonaHumana = new PersonaHumanaDto(nombre, apellido, fechaNacimiento, direccion, formasColaboracion, mediosDeContacto);

      UsuarioDto nuevoUsuario = new UsuarioDto(email, clave);
      usuarioService.registrar(nuevoUsuario);

      ctx.redirect("/login");
    } catch (RegistroFailedException e) {
      ctx.status(400);
      ctx.result("El registro ha fallado: " + e.getMessage());
    }
  }

  public void handleRegistroJuridico(Context ctx) {
    try {
      String email = ctx.formParam("email");
      String clave = ctx.formParam("pass");
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
      List<FormaColaboracion> formasColaboracion = new ArrayList<>();
      List<MedioDeContacto> mediosDeContacto = new ArrayList<>();

      if (!clave.equals(claveConf)) {
        throw new RegistroFailedException("Las contraseñas no coinciden");
      }

      PersonaJuridicaDto nuevaPersonaJuridica = new PersonaJuridicaDto(razonSocial, tipoOrganizacion, rubro, direccion, formasColaboracion, mediosDeContacto);

      UsuarioDto nuevoUsuario = new UsuarioDto(email, clave);
      usuarioService.registrar(nuevoUsuario);

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
