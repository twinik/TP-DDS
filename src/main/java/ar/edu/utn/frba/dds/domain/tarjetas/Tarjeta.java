package ar.edu.utn.frba.dds.domain.tarjetas;

import ar.edu.utn.frba.dds.domain.PersonaVulnerable;
import java.time.LocalDate;
import java.util.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * Tarjeta class permite representar una tarjeta.
 */
@Getter
@Setter
@AllArgsConstructor
public class Tarjeta {
  private String codigo;
  private Integer nroUsos;
  private boolean activa;
  private FrecuenciaUso frecuenciaPermitida;
  private List<UsoTarjeta> usos;
  private PersonaVulnerable duenio;
  private LocalDate fechaAdjudicacion;
  private Integer cantidadUsosDia;
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