package ar.edu.utn.frba.dds.models.domain.heladeras;

import ar.edu.utn.frba.dds.models.db.EntidadPersistente;
import ar.edu.utn.frba.dds.models.domain.colaboraciones.DonacionVianda;
import ar.edu.utn.frba.dds.models.domain.colaboradores.Colaborador;
import ar.edu.utn.frba.dds.models.domain.excepciones.ViandasIncosistentesException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * IngresoVianda class permite representar un ingreso de vianda.
 */
@Getter
@AllArgsConstructor
@Entity
@NoArgsConstructor
@Setter
@Table(name = "ingreso_vianda")
public class IngresoVianda extends EntidadPersistente {
  @Column(name = "fechaDonacion")
  private LocalDate fechaDonacion;
  @ManyToOne
  @JoinColumn(name = "colaborador_id", referencedColumnName = "id")
  private Colaborador colaborador;
//    private boolean entregada;

  @OneToMany(fetch = FetchType.LAZY)
  @JoinColumn(name = "ingreso_vianda_id", referencedColumnName = "id")
  private List<Vianda> viandas = new ArrayList<>();

  @ManyToOne
  @JoinColumn(name = "heladera_id", referencedColumnName = "id")
  private Heladera heladera;

  public IngresoVianda(LocalDate fechaDonacion, Colaborador colaborador, Heladera h) {
    this.fechaDonacion = fechaDonacion;
    this.colaborador = colaborador;
    this.heladera = h;
  }

  public void agregarViandas(Vianda... viandas) {
    this.viandas.addAll(Arrays.stream(viandas).toList());
  }

  public List<DonacionVianda> donar() {
    if (this.getViandas().stream().anyMatch(v -> !v.getHeladera().getNombre().equals(this.heladera.getNombre())))
      throw new ViandasIncosistentesException();
    this.heladera.agregarVianda(this.viandas.size());
    return this.viandas.stream().map(vianda -> {
      vianda.marcarEntregada();
      return new DonacionVianda(this.colaborador, vianda.getFechaDonacion(), vianda);
    }).toList();
  }

}