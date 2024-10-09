package ar.edu.utn.frba.dds.services;

import static ar.edu.utn.frba.dds.helpers.DateHelper.fechaFromString;

import ar.edu.utn.frba.dds.dtos.colaboraciones.AltaPersonaVulnerableDto;
import ar.edu.utn.frba.dds.dtos.colaboraciones.TutoradoInputDto;
import ar.edu.utn.frba.dds.models.domain.PersonaVulnerable;
import ar.edu.utn.frba.dds.models.domain.colaboraciones.AltaPersonaVulnerable;
import ar.edu.utn.frba.dds.models.domain.colaboraciones.calculadores.ICalculadorPuntos;
import ar.edu.utn.frba.dds.models.domain.colaboradores.Colaborador;
import ar.edu.utn.frba.dds.models.domain.utils.TipoDocumentoMapper;
import ar.edu.utn.frba.dds.models.repositories.IAltaPersonaVulnerableRepository;
import ar.edu.utn.frba.dds.models.repositories.IPersonaVulnerableRepository;
import ar.edu.utn.frba.dds.serviceLocator.ServiceLocator;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import java.time.LocalDate;

@AllArgsConstructor
public class AltaPersonaVulnerableService {
  private IPersonaVulnerableRepository personasVulnerablesRepository;
  private IAltaPersonaVulnerableRepository altaPersonaVulnerableRepository;
  private ColaboradoresService colaboradoresService;
  private ICalculadorPuntos calculadorPuntos;

  public void darAltaPersonaVulnerable(AltaPersonaVulnerableDto dto) {
    Colaborador colaborador = this.colaboradoresService.obtenerColaborador(dto.getIdColaborador());
    PersonaVulnerable p = obtenerPersonaVulnerable(dto);

    AltaPersonaVulnerable a = new AltaPersonaVulnerable();
    a.setPersona(p);
    a.setColaborador(colaborador);
    a.setFecha(LocalDate.now());
    //a.setTarjeta();
    // TODO Tarjeta

    this.calculadorPuntos.sumarPuntosPara(colaborador, a);
    this.personasVulnerablesRepository.guardar(p);
    this.altaPersonaVulnerableRepository.guardar(a);
  }

  public void darAltaTutorados(TutoradoInputDto dto) {
    PersonaVulnerable p = obtenerTutorado(dto);
    this.personasVulnerablesRepository.guardar(p);
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
    p.setDomicilio(dto.getDomicilio());
    p.setTipoDocumento(ServiceLocator.get(TipoDocumentoMapper.class).obtenerTipoDeDocumento(dto.getTipoDocumento()));
    p.setNroDocumento(dto.getNroDocumento());
    p.setColaborador(this.colaboradoresService.obtenerColaborador(dto.getIdColaborador()));
    return p;
  }

}
