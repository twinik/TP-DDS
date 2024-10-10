package ar.edu.utn.frba.dds.services;

import ar.edu.utn.frba.dds.dtos.personas.PersonaHumanaDto;
import ar.edu.utn.frba.dds.dtos.personas.PersonaJuridicaDto;
import ar.edu.utn.frba.dds.dtos.usuarios.UsuarioDto;
import ar.edu.utn.frba.dds.exceptions.RecursoInexistenteException;
import ar.edu.utn.frba.dds.helpers.DateHelper;
import ar.edu.utn.frba.dds.models.domain.colaboradores.Colaborador;
import ar.edu.utn.frba.dds.models.domain.colaboradores.TipoColaborador;
import ar.edu.utn.frba.dds.models.domain.colaboradores.TipoPersona;
import ar.edu.utn.frba.dds.models.domain.colaboradores.TipoPersonaJuridica;
import ar.edu.utn.frba.dds.models.domain.colaboradores.autenticacion.Usuario;
import ar.edu.utn.frba.dds.models.domain.excepciones.NoTieneDireccionException;
import ar.edu.utn.frba.dds.models.domain.utils.Direccion;
import ar.edu.utn.frba.dds.models.repositories.IColaboradoresRepository;
import ar.edu.utn.frba.dds.utils.PasswordHasher;
import lombok.AllArgsConstructor;
import java.util.Optional;


@AllArgsConstructor
public class ColaboradoresService {
  private IColaboradoresRepository colaboradoresRepository;
  private MedioContactoService medioContactoService;
  private FormaColaboracionService formaColaboracionService;
  private RolesService rolesService;

  public Optional<Colaborador> colaboradorFromUsuario(String idUsuario) {
    return this.colaboradoresRepository.buscarPorUsuario(idUsuario);
  }

  public Colaborador obtenerColaborador(String id) {
    // TODO: hacerlo con messageFactory
    if (id == null) throw new RecursoInexistenteException("no existe colaborador asociado a este id");
    Optional<Colaborador> colab = this.colaboradoresRepository.buscar(id);
    if (colab.isEmpty()) throw new RecursoInexistenteException("No existe colaborador asociado a este id");
    return colab.get();
  }

  public void registrar(PersonaHumanaDto dto) {
    Colaborador colaborador = new Colaborador();
    colaborador.setNombre(dto.getNombre());
    colaborador.setApellido(dto.getApellido());
    colaborador.setDireccion(dto.getDireccion() != null ? new Direccion(dto.getDireccion().getCalle(), dto.getDireccion().getNumero(), dto.getDireccion().getPiso(), dto.getDireccion().getCodigoPostal()) : null);
    colaborador.setTipoColaborador(new TipoColaborador(TipoPersona.PERSONA_HUMANA, this.formaColaboracionService.fromDtos(dto.getFormasColaboracion())));

    if (colaborador.getTipoColaborador().tenesFormaColaboracion("REGISTRO_PERSONA") && colaborador.getDireccion() == null) {
      // TODO: por favor que alguien me ponga las alerts de estooooo
      throw new NoTieneDireccionException("Por favor si querés dar de alta a personas en situacion vulnerable tenes que llenar una direccion");
    }


    if (dto.getFechaNacimiento() != null)
      colaborador.setFechaNacimiento(DateHelper.fechaFromString(dto.getFechaNacimiento(), "MM/dd/yyyy"));
    colaborador.setMedioContacto(this.medioContactoService.fromDtos(dto.getMediosDeContacto()));
    this.darleNuevoUsuarioA(dto.getUsuarioDto(), colaborador);
    this.colaboradoresRepository.guardar(colaborador);
  }

  public void registrar(PersonaJuridicaDto dto) {
    Colaborador colaborador = new Colaborador();
    colaborador.setRazonSocial(dto.getRazonSocial());
    colaborador.setTipoPersonaJuridica(TipoPersonaJuridica.valueOf(dto.getTipoOrganizacion()));
    colaborador.setRubro(dto.getRubro());
    colaborador.setDireccion(dto.getDireccion() != null ? new Direccion(dto.getDireccion().getCalle(), dto.getDireccion().getNumero(), dto.getDireccion().getPiso(), dto.getDireccion().getCodigoPostal()) : null);
    colaborador.setTipoColaborador(new TipoColaborador(TipoPersona.PERSONA_JURIDICA, this.formaColaboracionService.fromDtos(dto.getFormasColaboracion())));
    colaborador.setMedioContacto(this.medioContactoService.fromDtos(dto.getMediosDeContacto()));
    this.darleNuevoUsuarioA(dto.getUsuarioDto(), colaborador);
    this.colaboradoresRepository.guardar(colaborador);
  }


  private void darleNuevoUsuarioA(UsuarioDto dto, Colaborador colaborador) {
    Usuario user = new Usuario(dto.getEmail(), PasswordHasher.hashPassword(dto.getClave()));
    user.agregarRoles(this.rolesService.obtnerRolPara(colaborador.getTipoColaborador()));
    colaborador.setUsuario(user);
  }
}
