package ar.edu.utn.frba.dds.services;

import ar.edu.utn.frba.dds.dtos.personas.PersonaHumanaDto;
import ar.edu.utn.frba.dds.dtos.personas.PersonaJuridicaDto;
import ar.edu.utn.frba.dds.dtos.usuarios.UsuarioDto;
import ar.edu.utn.frba.dds.exceptions.DniDuplicadoException;
import ar.edu.utn.frba.dds.exceptions.EmailDuplicadoException;
import ar.edu.utn.frba.dds.exceptions.RecursoInexistenteException;
import ar.edu.utn.frba.dds.exceptions.RegistroFailedException;
import ar.edu.utn.frba.dds.helpers.DateHelper;
import ar.edu.utn.frba.dds.helpers.DniHelper;
import ar.edu.utn.frba.dds.models.domain.colaboradores.Colaborador;
import ar.edu.utn.frba.dds.models.domain.colaboradores.TipoColaborador;
import ar.edu.utn.frba.dds.models.domain.colaboradores.TipoPersona;
import ar.edu.utn.frba.dds.models.domain.colaboradores.TipoPersonaJuridica;
import ar.edu.utn.frba.dds.models.domain.colaboradores.autenticacion.Usuario;
import ar.edu.utn.frba.dds.models.domain.excepciones.CodigoInvalidoException;
import ar.edu.utn.frba.dds.models.domain.excepciones.NoTieneDireccionException;
import ar.edu.utn.frba.dds.models.domain.utils.Direccion;
import ar.edu.utn.frba.dds.models.domain.utils.TipoDocumento;
import ar.edu.utn.frba.dds.models.domain.utils.TipoDocumentoMapper;
import ar.edu.utn.frba.dds.models.messageFactory.*;
import ar.edu.utn.frba.dds.models.repositories.IColaboradoresRepository;
import ar.edu.utn.frba.dds.models.repositories.IUsuariosRepository;
import ar.edu.utn.frba.dds.serviceLocator.ServiceLocator;
import ar.edu.utn.frba.dds.utils.PasswordHasher;
import lombok.AllArgsConstructor;
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
    return colab.get();
  }

  public void actualizar(Colaborador colaborador) {
    this.colaboradoresRepository.actualizar(colaborador);
  }

  public String registrar(PersonaHumanaDto dto) {
    Colaborador colaborador = new Colaborador();
    this.validarSiYaExisteMail(dto.getUsuarioDto());
    colaborador.setNombre(dto.getNombre());
    colaborador.setApellido(dto.getApellido());
    colaborador.setTipoDocumento(ServiceLocator.get(TipoDocumentoMapper.class).obtenerTipoDeDocumento(dto.getTipoDocumento()));
    this.validarDocumento(colaborador.getTipoDocumento(), dto.getNroDocumento());
    colaborador.setDocumento(dto.getNroDocumento());
    colaborador.setDireccion(dto.getDireccion() != null ? new Direccion(dto.getDireccion().getCalle(), dto.getDireccion().getNumero(), dto.getDireccion().getPiso(), dto.getDireccion().getCodigoPostal()) : null);
    TipoColaborador tipo = new TipoColaborador();
    tipo.setTipo(TipoPersona.PERSONA_HUMANA);
    tipo.agregarFormasColaboracion(this.formaColaboracionService.fromDtos(dto.getFormasColaboracion()));
    colaborador.setTipoColaborador(tipo);
    colaborador.setFormCompletado(false);

    if (colaborador.getTipoColaborador().tenesFormaColaboracion("REGISTRO_PERSONA") && colaborador.getDireccion() == null) {
      throw new NoTieneDireccionException(MensajeNoTieneDireccionFactory.generarMensaje());
    }

    if (dto.getFechaNacimiento() != null)
      colaborador.setFechaNacimiento(DateHelper.fechaFromString(dto.getFechaNacimiento(), "MM/dd/yyyy"));
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
    this.validarSiYaExisteMail(dto.getUsuarioDto());
    colaborador.setRazonSocial(dto.getRazonSocial());
    colaborador.setTipoPersonaJuridica(TipoPersonaJuridica.valueOf(dto.getTipoOrganizacion()));
    colaborador.setRubro(dto.getRubro());
    colaborador.setDireccion(dto.getDireccion() != null ? new Direccion(dto.getDireccion().getCalle(), dto.getDireccion().getNumero(), dto.getDireccion().getPiso(), dto.getDireccion().getCodigoPostal()) : null);
    TipoColaborador tipo = new TipoColaborador();
    tipo.setTipo(TipoPersona.PERSONA_JURIDICA);
    tipo.agregarFormasColaboracion(this.formaColaboracionService.fromDtos(dto.getFormasColaboracion()));
    colaborador.setTipoColaborador(tipo);
    colaborador.agregarMedioContacto(this.medioContactoService.fromDtos(dto.getMediosDeContacto()));
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

  private void validarDocumento(TipoDocumento tipoDocumento, String nroDocumento) {
    if (!DniHelper.esValido(nroDocumento))
      throw new DniDuplicadoException(MensajeDniInvalidoFactory.generarMensaje());
    Optional<Colaborador> user = this.colaboradoresRepository.buscarPorDni(tipoDocumento, nroDocumento);
    if (user.isPresent()) throw new DniDuplicadoException(MensajeDniDuplicadoFactory.generarMensaje());
  }
}
