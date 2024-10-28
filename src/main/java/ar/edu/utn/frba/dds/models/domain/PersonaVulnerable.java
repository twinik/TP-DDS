package ar.edu.utn.frba.dds.models.domain;

import ar.edu.utn.frba.dds.models.db.EntidadPersistente;
import ar.edu.utn.frba.dds.models.domain.colaboradores.Colaborador;
import ar.edu.utn.frba.dds.models.domain.utils.TipoDocumento;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.time.LocalDate;
import java.time.Period;
import java.util.Collections;
import java.util.List;

/**
 * PersonaVulnerable class permite representar una persona vulnerable.
 */
@Getter
@Setter
@Entity
@Table(name = "persona_vulnerable")
@NoArgsConstructor
public class PersonaVulnerable extends EntidadPersistente {


  @Column(name = "nombre", nullable = false)
  private String nombre;

  @Column(name = "apellido", nullable = false)
  private String apellido;

  @Column(name = "fecha_naciminiento")
  private LocalDate fechaNacimiento;

  @Column(name = "fecha_registro")
  private LocalDate fechaRegistro;

  @Column(name = "posee_domicilio")
  private boolean poseeDomicilio;

  @Column(name = "domicilio")
  private String domicilio;

  @Enumerated(EnumType.STRING)
  private TipoDocumento tipoDocumento;

  @Column(name = "nro_documento", columnDefinition = "varchar(11)")
  private String nroDocumento;

  @ManyToOne
  @JoinColumn(name = "colaborador_id", referencedColumnName = "id")
  private Colaborador colaborador;

  @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH}, fetch = FetchType.LAZY)
  @JoinColumn(name = "tutor_id", referencedColumnName = "id")
  private List<PersonaVulnerable> tutorados;


  public PersonaVulnerable(String nombre, LocalDate fechaNacimiento, LocalDate fechaRegistro, boolean poseeDomicilio, String domicilio, TipoDocumento tipoDocumento, String nroDocumento, Colaborador colaborador, List<PersonaVulnerable> tutorados) {
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
    LocalDate fechaActual = LocalDate.now();
    int age = Period.between(this.fechaNacimiento, fechaActual).getYears();
    return age;
  }

  public void agregarTutorados(PersonaVulnerable... tutorados) {
    Collections.addAll(this.tutorados, tutorados);
  }

}