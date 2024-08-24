package ar.edu.utn.frba.dds.domain.tarjetas;

import ar.edu.utn.frba.dds.converters.FrecuenciaUsoAttributeConverter;
import ar.edu.utn.frba.dds.domain.PersonaVulnerable;
import java.time.LocalDate;
import java.util.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;

/**
 * Tarjeta class permite representar una tarjeta.
 */
@Entity
@Table(name = "tarjeta")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Tarjeta {

  @Id
  @GeneratedValue
  private Long id;

  @Column(name = "codigo")
  private String codigo;

  @Column(name = "nro_usos")
  private Integer nroUsos;

  @Column(name = "activa")
  private boolean activa;

  @Convert(converter = FrecuenciaUsoAttributeConverter.class)
  @Column(name = "frecuencia_permitida")
  private FrecuenciaUso frecuenciaPermitida;

  @OneToMany
  @JoinColumn(name = "tarjeta_id", referencedColumnName = "id")
  private List<UsoTarjeta> usos;

  @OneToOne
  @JoinColumn(name = "duenio_id", referencedColumnName = "id")
  private PersonaVulnerable duenio;

  @Column(name = "fecha_adjudicacion",columnDefinition = "DATE",  nullable = false)
  private LocalDate fechaAdjudicacion;

  @Column(name = "cantidad_usos_dia")
  private Integer cantidadUsosDia;

  @Column(name = "fecha_baja", columnDefinition = "DATE")
  private LocalDate fechaBaja = null;
  // CRON JOB string (todos los dias a las 00:00hs): "0 0 0 1/1 * ? *"

  /**
   * Constructor de Tarjeta.
   */
  public Tarjeta(String codigo, Integer nroUsos, FrecuenciaUso frecuenciaPermitida, PersonaVulnerable duenio, LocalDate fechaAdjudicacion, Integer cantidadUsosDia) {
    this.codigo = codigo;
    this.nroUsos = nroUsos;
    this.frecuenciaPermitida = frecuenciaPermitida;
    this.activa = true;
    this.usos = new ArrayList<>();
    this.duenio = duenio;
    this.fechaAdjudicacion = fechaAdjudicacion;
    this.cantidadUsosDia = cantidadUsosDia;
  }

  public void agregarUsos() {
    this.cantidadUsosDia++;
    this.nroUsos++;
  }

  public void resetearUsosDiarios() {
    this.cantidadUsosDia = 0;
  }

  boolean permiteUsar() {
    return this.activa && frecuenciaPermitida.permiteUsar(this);
  }

  public static Tarjeta of(String codigo, Integer nroUsos, FrecuenciaUso frecuenciaPermitida, PersonaVulnerable duenio, LocalDate fechaAdjudicacion, Integer cantidadUsosDia) {
    return new Tarjeta(codigo, nroUsos, frecuenciaPermitida, duenio, fechaAdjudicacion, cantidadUsosDia);
  }

  public static Tarjeta of(String codigo, Integer nroUsos, FrecuenciaUso frecuenciaPermitida, PersonaVulnerable duenio, Integer cantidadUsosDia) {
    return Tarjeta.of(codigo, nroUsos, frecuenciaPermitida, duenio, LocalDate.now(), cantidadUsosDia);
  }

  public static Tarjeta of(String codigo, Integer nroUsos, FrecuenciaUso frecuenciaPermitida, PersonaVulnerable duenio) {
    return Tarjeta.of(codigo, nroUsos, frecuenciaPermitida, duenio, 0);
  }

  public static Tarjeta of(String codigo, FrecuenciaUso frecuenciaPermitida, PersonaVulnerable duenio) {
    return Tarjeta.of(codigo, 0, frecuenciaPermitida, duenio);
  }
}