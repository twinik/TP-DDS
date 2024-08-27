package ar.edu.utn.frba.dds.domain.tarjetas;

import ar.edu.utn.frba.dds.db.EntidadPersistente;
import ar.edu.utn.frba.dds.domain.colaboradores.Colaborador;
import ar.edu.utn.frba.dds.domain.excepciones.NoTieneDireccionException;
import ar.edu.utn.frba.dds.domain.heladeras.AperturaHeladera;
import ar.edu.utn.frba.dds.messageFactory.MensajeExcepcionDireccionTarjetaFactory;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * representa una tarjeta que usa el colaborador para acceder a las heladeras
 */
@Entity
@Table(name = "tarjeta_colaborador")
@Getter
@Setter
@NoArgsConstructor
public class TarjetaColaborador extends EntidadPersistente {

  @Column(name = "codigo")
  private String codigo;

  @OneToOne
  @JoinColumn(name = "colaborador_id", referencedColumnName = "id")
  private Colaborador colaborador;

  @Column(name = "activa")
  private boolean activa;

  @Column(name = "fecha_alta", columnDefinition = "DATE")
  private LocalDate fechaAlta;

  @Column(name = "fecha_baja", columnDefinition = "DATE")
  private LocalDate fechaBaja;

  @OneToMany
  @JoinColumn(name = "tarjeta_id")
  private List<AperturaHeladera> usos;

  public TarjetaColaborador(String codigo, Colaborador colaborador, boolean activa, LocalDate fechaAlta, LocalDate fechaBaja, List<AperturaHeladera> usos) {
    this.codigo = codigo;
    this.colaborador = colaborador;
    this.activa = activa;
    this.fechaAlta = fechaAlta;
    this.fechaBaja = fechaBaja;
    this.usos = usos;
  }

  public static TarjetaColaborador of(Colaborador colaborador, String codigo) throws NoTieneDireccionException {
    if (colaborador.getDireccion() == null)
      throw new NoTieneDireccionException(MensajeExcepcionDireccionTarjetaFactory.generarMensaje());
    return new TarjetaColaborador(codigo, colaborador, true, LocalDate.now(), null, new ArrayList<>());
  }

  public static TarjetaColaborador of(Colaborador colaborador, String codigo, LocalDate fechaAlta) throws NoTieneDireccionException {
    if (colaborador.getDireccion() == null)
      throw new NoTieneDireccionException(MensajeExcepcionDireccionTarjetaFactory.generarMensaje());
    return new TarjetaColaborador(codigo, colaborador, true, fechaAlta, null, new ArrayList<>());
  }

}