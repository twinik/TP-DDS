package ar.edu.utn.frba.dds.models.domain.tarjetas;

import ar.edu.utn.frba.dds.helpers.GeneradorDeCodigosHelper;
import ar.edu.utn.frba.dds.models.converters.FrecuenciaUsoAttributeConverter;
import ar.edu.utn.frba.dds.models.db.EntidadPersistente;
import ar.edu.utn.frba.dds.models.domain.PersonaVulnerable;
import ar.edu.utn.frba.dds.models.domain.excepciones.CodigoInvalidoException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Tarjeta class permite representar una tarjeta.
 */
@Entity
@Table(name = "tarjeta")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Tarjeta extends EntidadPersistente {

  @Column(name = "codigo", unique = true)
  private String codigo;

  @Column(name = "nro_usos")
  private Integer nroUsos;

  @Column(name = "tarjeta_activa")
  private boolean tarjetaActiva;

  @Convert(converter = FrecuenciaUsoAttributeConverter.class)
  @Column(name = "frecuencia_permitida")
  private FrecuenciaUso frecuenciaPermitida;

  @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH}, fetch = FetchType.LAZY)
  @JoinColumn(name = "tarjeta_id", referencedColumnName = "id")
  private List<UsoTarjeta> usos = new ArrayList<>();

  @OneToOne
  @JoinColumn(name = "duenio_id", referencedColumnName = "id", unique = true)
  private PersonaVulnerable duenio;

  @Column(name = "fecha_adjudicacion", columnDefinition = "DATE")
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
    if (!GeneradorDeCodigosHelper.esCodigoValido(codigo, 11))
      throw new CodigoInvalidoException("El codigo no es valido");
    this.codigo = codigo;
    this.nroUsos = nroUsos;
    this.frecuenciaPermitida = frecuenciaPermitida;
    this.tarjetaActiva = true;
    this.usos = new ArrayList<>();
    this.duenio = duenio;
    this.fechaAdjudicacion = fechaAdjudicacion;
    this.cantidadUsosDia = cantidadUsosDia;
  }

  public static Tarjeta of(String codigo, Integer nroUsos, FrecuenciaUso frecuenciaPermitida, PersonaVulnerable duenio, LocalDate fechaAdjudicacion, Integer cantidadUsosDia) {
    return new Tarjeta(codigo, nroUsos, frecuenciaPermitida, duenio, fechaAdjudicacion, cantidadUsosDia);
  }

  public static Tarjeta of(String codigo, Integer nroUsos, FrecuenciaUso frecuenciaPermitida, PersonaVulnerable duenio, Integer cantidadUsosDia) {
    return of(codigo, nroUsos, frecuenciaPermitida, duenio, LocalDate.now(), cantidadUsosDia);
  }

  public static Tarjeta of(String codigo, Integer nroUsos, FrecuenciaUso frecuenciaPermitida, PersonaVulnerable duenio) {
    return of(codigo, nroUsos, frecuenciaPermitida, duenio, 0);
  }

  public static Tarjeta of(String codigo, FrecuenciaUso frecuenciaPermitida, PersonaVulnerable duenio) {
    return of(codigo, 0, frecuenciaPermitida, duenio);
  }

  public void agregarUsos(UsoTarjeta uso) {
    this.usos.add(uso);
    this.cantidadUsosDia++;
    this.nroUsos++;
  }

  public void resetearUsosDiarios() {
    this.cantidadUsosDia = 0;
  }

  public boolean permiteUsar() {
    return this.tarjetaActiva && frecuenciaPermitida.permiteUsar(this);
  }
}