package ar.edu.utn.frba.dds.domain;

import ar.edu.utn.frba.dds.domain.colaboradores.Colaborador;
import ar.edu.utn.frba.dds.domain.utils.TipoDocumento;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * PersonaVulnerable class permite representar una persona vulnerable.
 */
@Getter
@Setter
@AllArgsConstructor
public class PersonaVulnerable {

  private long id;
  private String nombre;
  private Date fechaNacimiento;
  private Date fechaRegistro;
  private boolean poseeDomicilio;
  private String domicilio;
  private TipoDocumento tipoDocumento;
  private String nroDocumento;
  private Colaborador colaborador;
  private List<PersonaVulnerable> tutorados;


  public PersonaVulnerable(String nombre, Date fechaNacimiento, Date fechaRegistro, boolean poseeDomicilio, String domicilio, TipoDocumento tipoDocumento, String nroDocumento, Colaborador colaborador, List<PersonaVulnerable> tutorados) {
    this.nombre = nombre;
    this.fechaNacimiento = fechaNacimiento;
    this.fechaRegistro = fechaRegistro;
    this.poseeDomicilio = poseeDomicilio;
    this.domicilio = domicilio;
    this.tipoDocumento = tipoDocumento;
    this.nroDocumento = nroDocumento;
    this.colaborador = colaborador;
    this.tutorados = tutorados;
  }

  public boolean poseeMenores() {
    return tutorados.stream().anyMatch(PersonaVulnerable::esMenor);
  }

  public Integer cantidadMenores() {
    return Math.toIntExact(tutorados.stream().filter(PersonaVulnerable::esMenor).count());
  }

  public boolean esMenor() {
    return this.edad() < 18;
  }

  private Integer edad() {
    DateFormat formatter = new SimpleDateFormat("yyyyMMdd");
    int d1 = Integer.parseInt(formatter.format(this.fechaNacimiento));
    int d2 = Integer.parseInt(formatter.format(new Date()));
    int age = (d2 - d1) / 10000;
    return age;
  }

  public void agregarTutorados(PersonaVulnerable... tutorados) {
    Collections.addAll(this.tutorados, tutorados);
  }

}