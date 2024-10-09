package ar.edu.utn.frba.dds.services;

import ar.edu.utn.frba.dds.dtos.personas.PersonaHumanaDto;
import ar.edu.utn.frba.dds.dtos.personas.PersonaJuridicaDto;
import ar.edu.utn.frba.dds.exceptions.RecursoInexistenteException;
import ar.edu.utn.frba.dds.helpers.DateHelper;
import ar.edu.utn.frba.dds.models.domain.colaboradores.Colaborador;
import ar.edu.utn.frba.dds.models.domain.colaboradores.TipoColaborador;
import ar.edu.utn.frba.dds.models.domain.colaboradores.TipoPersona;
import ar.edu.utn.frba.dds.models.domain.colaboradores.autenticacion.Usuario;
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
    colaborador.setFechaNacimiento(DateHelper.fechaFromString(dto.getFechaNacimiento(), "MM/dd/yyyy"));
    colaborador.setMedioContacto(this.medioContactoService.fromDtos(dto.getMediosDeContacto()));
    colaborador.setTipoColaborador(new TipoColaborador(TipoPersona.PERSONA_HUMANA, this.formaColaboracionService.fromDtos(dto.getFormasColaboracion())));
    Usuario user = new Usuario(dto.getUsuarioDto().getEmail(), PasswordHasher.hashPassword(dto.getUsuarioDto().getClave()));
    user.agregarRoles(this.rolesService.obtnerRolPara(colaborador.getTipoColaborador()));
    colaborador.setUsuario(user);
    this.colaboradoresRepository.guardar(colaborador);
  }

  public void regisrar(PersonaJuridicaDto dto) {
    //TODO
  }

}
