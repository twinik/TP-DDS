package ar.edu.utn.frba.dds.services;

import static ar.edu.utn.frba.dds.helpers.DateHelper.fechaFromString;

import ar.edu.utn.frba.dds.dtos.colaboraciones.AltaPersonaVulnerableDto;
import ar.edu.utn.frba.dds.dtos.colaboraciones.TutoradoInputDto;
import ar.edu.utn.frba.dds.exceptions.DniDuplicadoException;
import ar.edu.utn.frba.dds.helpers.DniHelper;
import ar.edu.utn.frba.dds.models.domain.PersonaVulnerable;
import ar.edu.utn.frba.dds.models.domain.colaboraciones.AltaPersonaVulnerable;
import ar.edu.utn.frba.dds.models.domain.colaboraciones.calculadores.ICalculadorPuntos;
import ar.edu.utn.frba.dds.models.domain.colaboradores.Colaborador;
import ar.edu.utn.frba.dds.models.domain.utils.TipoDocumento;
import ar.edu.utn.frba.dds.models.domain.utils.TipoDocumentoMapper;
import ar.edu.utn.frba.dds.models.messageFactory.MensajeDniDuplicadoFactory;
import ar.edu.utn.frba.dds.models.messageFactory.MensajeDniInvalidoFactory;
import ar.edu.utn.frba.dds.models.repositories.IAltaPersonaVulnerableRepository;
import ar.edu.utn.frba.dds.models.repositories.IPersonaVulnerableRepository;
import ar.edu.utn.frba.dds.serviceLocator.ServiceLocator;
import lombok.AllArgsConstructor;
import java.time.LocalDate;
import java.util.Optional;

@AllArgsConstructor
public class AltaPersonaVulnerableService {
  private IPersonaVulnerableRepository personasVulnerablesRepository;
  private IAltaPersonaVulnerableRepository altaPersonaVulnerableRepository;
  private ColaboradoresService colaboradoresService;
  private ICalculadorPuntos calculadorPuntos;
  private TarjetasService tarjetasService;

  public String darAltaPersonaVulnerable(AltaPersonaVulnerableDto dto) {
    Colaborador colaborador = this.colaboradoresService.obtenerColaborador(dto.getIdColaborador());
    PersonaVulnerable p = obtenerPersonaVulnerable(dto);

    this.validarDocumento(p.getTipoDocumento(), dto.getNroDocumento());

    AltaPersonaVulnerable a = new AltaPersonaVulnerable();
    a.setPersona(p);
    a.setColaborador(colaborador);
    a.setFecha(LocalDate.now());
    this.personasVulnerablesRepository.guardar(p);
    this.tarjetasService.crearTarjeta(p, dto.getTarjeta());
    //a.setTarjeta();
    // TODO Tarjeta

    this.calculadorPuntos.sumarPuntosPara(colaborador, a);
    this.altaPersonaVulnerableRepository.guardar(a);
    return p.getId();
  }

  public void darAltaTutorados(TutoradoInputDto dto, String idPersona) {
    PersonaVulnerable p = obtenerTutorado(dto);
    Optional<PersonaVulnerable> rta = personasVulnerablesRepository.buscar(idPersona);
    if (rta.isPresent()) {
      PersonaVulnerable tutor = rta.get();
      tutor.agregarTutorados(p);
      this.personasVulnerablesRepository.actualizar(tutor);
    }
  }

  public String crearPersonaVulnerable(AltaPersonaVulnerableDto dto) {
    PersonaVulnerable p = obtenerPersonaVulnerable(dto);
    this.personasVulnerablesRepository.guardar(p);
    return p.getId();
  }

  public PersonaVulnerable obtenerPersonaVulnerable(AltaPersonaVulnerableDto dto) {
    PersonaVulnerable p = new PersonaVulnerable();
    p.setNombre(dto.getNombre());
    p.setApellido(dto.getApellido());
    p.setFechaNacimiento(fechaFromString(dto.getFechaNacimiento(), "dd/MM/yyyy"));
    p.setFechaRegistro(LocalDate.now());
    p.setPoseeDomicilio(dto.getDomicilio() != null);
    p.setDomicilio(dto.getDomicilio());
    p.setTipoDocumento(ServiceLocator.get(TipoDocumentoMapper.class).obtenerTipoDeDocumento(dto.getTipoDocumento()));
    p.setNroDocumento(dto.getNroDocumento());
    p.setColaborador(this.colaboradoresService.obtenerColaborador(dto.getIdColaborador()));
    return p;
  }

  public PersonaVulnerable obtenerTutorado(TutoradoInputDto dto) {
    PersonaVulnerable p = new PersonaVulnerable();
    p.setNombre(dto.getNombre());
    p.setApellido(dto.getApellido());
    p.setFechaNacimiento(fechaFromString(dto.getFechaNacimiento(), "dd/MM/yyyy"));
    p.setFechaRegistro(LocalDate.now());
    p.setDomicilio(dto.getDomicilio());
    p.setPoseeDomicilio(dto.getDomicilio() != null);
    p.setTipoDocumento(ServiceLocator.get(TipoDocumentoMapper.class).obtenerTipoDeDocumento(dto.getTipoDocumento()));
    p.setNroDocumento(dto.getNroDocumento());
    p.setColaborador(this.colaboradoresService.obtenerColaborador(dto.getIdColaborador()));
    return p;
  }

  private void validarDocumento(TipoDocumento tipoDocumento, String nroDocumento) {
    if (!DniHelper.esValido(nroDocumento)) throw new DniDuplicadoException(MensajeDniInvalidoFactory.generarMensaje());
    Optional<PersonaVulnerable> user = this.personasVulnerablesRepository.buscarPorDni(tipoDocumento,nroDocumento);
    if (user.isPresent()) throw new DniDuplicadoException(MensajeDniDuplicadoFactory.generarMensaje());
  }

}
