package ar.edu.utn.frba.dds.services;

import ar.edu.utn.frba.dds.dtos.personas.ColaboradorPerfilDto;
import ar.edu.utn.frba.dds.dtos.personas.PersonaHumanaDto;
import ar.edu.utn.frba.dds.dtos.personas.PersonaJuridicaDto;
import ar.edu.utn.frba.dds.dtos.usuarios.UsuarioDto;
import ar.edu.utn.frba.dds.exceptions.DniDuplicadoException;
import ar.edu.utn.frba.dds.exceptions.EmailDuplicadoException;
import ar.edu.utn.frba.dds.exceptions.FormIncompletoException;
import ar.edu.utn.frba.dds.exceptions.RecursoInexistenteException;
import ar.edu.utn.frba.dds.exceptions.RegistroFailedException;
import ar.edu.utn.frba.dds.helpers.DateHelper;
import ar.edu.utn.frba.dds.helpers.DniHelper;
import ar.edu.utn.frba.dds.models.domain.colaboradores.Colaborador;
import ar.edu.utn.frba.dds.models.domain.colaboradores.FormaColaboracion;
import ar.edu.utn.frba.dds.models.domain.colaboradores.TipoColaborador;
import ar.edu.utn.frba.dds.models.domain.colaboradores.TipoPersona;
import ar.edu.utn.frba.dds.models.domain.colaboradores.TipoPersonaJuridica;
import ar.edu.utn.frba.dds.models.domain.colaboradores.autenticacion.Rol;
import ar.edu.utn.frba.dds.models.domain.colaboradores.autenticacion.Usuario;
import ar.edu.utn.frba.dds.models.domain.excepciones.CodigoInvalidoException;
import ar.edu.utn.frba.dds.models.domain.excepciones.NoTieneDireccionException;
import ar.edu.utn.frba.dds.models.domain.utils.Direccion;
import ar.edu.utn.frba.dds.models.domain.utils.TipoDocumento;
import ar.edu.utn.frba.dds.models.domain.utils.TipoDocumentoMapper;
import ar.edu.utn.frba.dds.models.messageFactory.MensajeDniDuplicadoFactory;
import ar.edu.utn.frba.dds.models.messageFactory.MensajeDniInvalidoFactory;
import ar.edu.utn.frba.dds.models.messageFactory.MensajeEmailDuplicadoFactory;
import ar.edu.utn.frba.dds.models.messageFactory.MensajeFechaInvalidaFactory;
import ar.edu.utn.frba.dds.models.messageFactory.MensajeNoTieneDireccionFactory;
import ar.edu.utn.frba.dds.models.messageFactory.MensajeRecursoInexistenteFactory;
import ar.edu.utn.frba.dds.models.repositories.IColaboradoresRepository;
import ar.edu.utn.frba.dds.models.repositories.IUsuariosRepository;
import ar.edu.utn.frba.dds.serviceLocator.ServiceLocator;
import ar.edu.utn.frba.dds.utils.PasswordHasher;
import ar.edu.utn.frba.dds.utils.PermisosHelper;
import lombok.AllArgsConstructor;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


@AllArgsConstructor
public class ColaboradoresService {
  private IColaboradoresRepository colaboradoresRepository;
  private MedioContactoService medioContactoService;
  private FormaColaboracionService formaColaboracionService;
  private RolesService rolesService;
  private TarjetasService tarjetasService;
  private IUsuariosRepository usuariosRepository;

  public Optional<Colaborador> colaboradorFromUsuario(String idUsuario) {
    return this.colaboradoresRepository.buscarPorUsuario(idUsuario);
  }

  public Colaborador obtenerColaborador(String id) {
    if (id == null)
      throw new RecursoInexistenteException(MensajeRecursoInexistenteFactory.generarMensaje("Colaborador"));
    Optional<Colaborador> colab = this.colaboradoresRepository.buscar(id);
    if (colab.isEmpty())
      throw new RecursoInexistenteException(MensajeRecursoInexistenteFactory.generarMensaje("Colaborador", id));
    this.colaboradoresRepository.refresh(colab.get());
    return colab.get();
  }

  public void actualizar(Colaborador colaborador) {
    this.colaboradoresRepository.actualizar(colaborador);
    this.colaboradoresRepository.refresh(colaborador);
  }

  public void actualizar(ColaboradorPerfilDto dto) {
    Colaborador colaborador = this.obtenerColaborador(dto.getId());
    colaborador = this.actualizarFromDto(colaborador, dto);
    this.colaboradoresRepository.actualizar(colaborador);
    this.colaboradoresRepository.refresh(colaborador);
  }

  public Colaborador actualizarFromDto(Colaborador colaborador, ColaboradorPerfilDto dto) {
    colaborador.setNombre(dto.getNombre() != null ? dto.getNombre() : colaborador.getNombre());
    colaborador.setApellido(dto.getApellido() != null ? dto.getApellido() : colaborador.getApellido());
    colaborador.getUsuario().setEmail(dto.getEmail() != null ? dto.getEmail() : colaborador.getUsuario().getEmail());
    colaborador.setTipoDocumento(dto.getTipoDocumento() != null ? ServiceLocator.get(TipoDocumentoMapper.class).obtenerTipoDeDocumento(dto.getTipoDocumento()) : colaborador.getTipoDocumento());
    if (dto.getDocumento() != null && !dto.getDocumento().equals(colaborador.getDocumento())) {
      this.validarDocumento(dto.getDocumento());
    }
    colaborador.setDocumento(dto.getDocumento() != null ? dto.getDocumento() : colaborador.getDocumento());
    colaborador.setDireccion(dto.getDireccionDto() != null ? new Direccion(dto.getDireccionDto().getCalle(), dto.getDireccionDto().getAltura(), dto.getDireccionDto().getPiso(), dto.getDireccionDto().getCodigoPostal()) : null);
    if (dto.getFechaNacimiento() != null) {
      colaborador.setFechaNacimiento(DateHelper.fechaFromString(dto.getFechaNacimiento(), "dd/MM/yyyy"));
      this.validarFechaNacimiento(colaborador);
    }
    colaborador.setRubro(dto.getRubro() != null ? dto.getRubro() : colaborador.getRubro());
    colaborador.setRazonSocial(dto.getRazonSocial() != null ? dto.getRazonSocial() : colaborador.getRazonSocial());
    colaborador.setTipoPersonaJuridica(dto.getTipoPersonaJuridica() != null ? TipoPersonaJuridica.valueOf(dto.getTipoPersonaJuridica()) : colaborador.getTipoPersonaJuridica());
    List<FormaColaboracion> posiblesNuevasFormas = this.formaColaboracionService.fromDtos(dto.getFormaColaboracionInput());

    if (!posiblesNuevasFormas.isEmpty()) {
      posiblesNuevasFormas.forEach(forma -> {
        colaborador.getTipoColaborador().agregarFormasColaboracion(forma);

        if (colaborador.getTipoColaborador().tenesFormaColaboracion("REGISTRO_PERSONA") && colaborador.getDireccion() == null)
          throw new NoTieneDireccionException(MensajeNoTieneDireccionFactory.generarMensaje(), null);

        this.rolesService.agregarRolPara(colaborador.getUsuario(), Rol.of(PermisosHelper.getInstance().
            buscarPorNombres(PermisosHelper.getInstance().fromFormaColaboracion(forma).toArray(new String[0]))));
      });


    }
    return colaborador;
  }

  public void refresh(Colaborador c) {
    this.colaboradoresRepository.refresh(c);
  }

  public String registrar(PersonaHumanaDto dto) {
    Colaborador colaborador = new Colaborador();
    try {
      this.validarSiYaExisteMail(dto.getUsuarioDto());
    } catch (EmailDuplicadoException e) {
      throw new EmailDuplicadoException(e.getMessage(), dto);
    }
    colaborador.setNombre(dto.getNombre());
    colaborador.setApellido(dto.getApellido());
    colaborador.setTipoDocumento(ServiceLocator.get(TipoDocumentoMapper.class).obtenerTipoDeDocumento(dto.getTipoDocumento()));

    colaborador.setDocumento(dto.getNroDocumento());
    colaborador.setDireccion(dto.getDireccion() != null ? new Direccion(dto.getDireccion().getCalle(), dto.getDireccion().getAltura(), dto.getDireccion().getPiso(), dto.getDireccion().getCodigoPostal()) : null);
    TipoColaborador tipo = new TipoColaborador();
    tipo.setTipo(TipoPersona.PERSONA_HUMANA);
    tipo.agregarFormasColaboracion(this.formaColaboracionService.fromDtos(dto.getFormasColaboracion()));
    colaborador.setTipoColaborador(tipo);
    colaborador.setFormCompletado(false);

    if (colaborador.getTipoColaborador().tenesFormaColaboracion("REGISTRO_PERSONA") && colaborador.getDireccion() == null) {
      throw new NoTieneDireccionException(MensajeNoTieneDireccionFactory.generarMensaje(), dto);
    }

    if (dto.getFechaNacimiento() != null) {
      colaborador.setFechaNacimiento(DateHelper.fechaFromString(dto.getFechaNacimiento(), "dd/MM/yyyy"));
      this.validarFechaNacimiento(colaborador);
    }

    colaborador.agregarMedioContacto(this.medioContactoService.fromDtos(dto.getMediosDeContacto()));
    this.darleNuevoUsuarioA(dto.getUsuarioDto(), colaborador);
    this.colaboradoresRepository.guardar(colaborador);
    try {
      this.tarjetasService.asignarTarjetaColaborador(colaborador);
    } catch (CodigoInvalidoException e) {
      this.colaboradoresRepository.eliminar(colaborador);
      throw e;
    }

    return colaborador.getId();
  }

  public void registrar(PersonaJuridicaDto dto) {
    Colaborador colaborador = new Colaborador();
    try {
      this.validarSiYaExisteMail(dto.getUsuarioDto());
    } catch (EmailDuplicadoException e) {
      throw new EmailDuplicadoException(e.getMessage(), dto);
    }

    colaborador.setRazonSocial(dto.getRazonSocial());
    colaborador.setTipoPersonaJuridica(TipoPersonaJuridica.valueOf(dto.getTipoOrganizacion()));
    colaborador.setRubro(dto.getRubro());
    colaborador.setDireccion(dto.getDireccion() != null ? new Direccion(dto.getDireccion().getCalle(), dto.getDireccion().getAltura(), dto.getDireccion().getPiso(), dto.getDireccion().getCodigoPostal()) : null);
    TipoColaborador tipo = new TipoColaborador();
    tipo.setTipo(TipoPersona.PERSONA_JURIDICA);
    tipo.agregarFormasColaboracion(this.formaColaboracionService.fromDtos(dto.getFormasColaboracion()));
    colaborador.setTipoColaborador(tipo);
    colaborador.agregarMedioContacto(this.medioContactoService.fromDtos(dto.getMediosDeContacto()));
    colaborador.setFormCompletado(true);
    this.darleNuevoUsuarioA(dto.getUsuarioDto(), colaborador);
    this.colaboradoresRepository.guardar(colaborador);
  }

  private void darleNuevoUsuarioA(UsuarioDto dto, Colaborador colaborador) {
    Usuario user = new Usuario(dto.getEmail(), PasswordHasher.hashPassword(dto.getClave()));
    user.agregarRoles(this.rolesService.obtnerRolPara(colaborador.getTipoColaborador()));
    colaborador.setUsuario(user);
  }

  public void marcarFormCompletado(String idColaborador) {
    Optional<Colaborador> c = this.colaboradoresRepository.buscar(idColaborador);
    if (c.isEmpty())
      throw new RegistroFailedException(MensajeRecursoInexistenteFactory.generarMensaje("Colaborador", idColaborador));
    c.get().setFormCompletado(true);
    this.colaboradoresRepository.actualizar(c.get());
    // this.colaboradoresRepository.marcarFormCompletado(idColaborador);
  }

  private void validarSiYaExisteMail(UsuarioDto dto) {
    Optional<Usuario> user = this.usuariosRepository.buscarPorEmail(dto.getEmail());
    if (user.isPresent()) throw new EmailDuplicadoException(MensajeEmailDuplicadoFactory.generarMensaje());
  }

  private void validarDocumento(TipoDocumento tipoDocumento, String nroDocumento, PersonaHumanaDto dto) {
    if (!DniHelper.esValido(nroDocumento))
      throw new DniDuplicadoException(MensajeDniInvalidoFactory.generarMensaje(), dto);
    Optional<Colaborador> user = this.colaboradoresRepository.buscarPorDni(tipoDocumento, nroDocumento);
    if (user.isPresent()) throw new DniDuplicadoException(MensajeDniDuplicadoFactory.generarMensaje(), dto);
  }

  private void validarDocumento(String nroDocumento) {
    if (!DniHelper.esValido(nroDocumento))
      throw new DniDuplicadoException(MensajeDniInvalidoFactory.generarMensaje());
  }

  private void validarFechaNacimiento(Colaborador c) {
    if (c.getFechaNacimiento().isAfter(LocalDate.now()))
      throw new FormIncompletoException(MensajeFechaInvalidaFactory.generarMensaje());
  }

}
