package ar.edu.utn.frba.dds.models.domain.heladeras;

import ar.edu.utn.frba.dds.models.db.EntidadPersistente;
import ar.edu.utn.frba.dds.models.domain.colaboraciones.DonacionVianda;
import ar.edu.utn.frba.dds.models.domain.colaboradores.Colaborador;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * IngresoVianda class permite representar un ingreso de vianda.
 */
@Getter
@AllArgsConstructor
@Entity
@NoArgsConstructor
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

//    private Heladera heladera;

  public IngresoVianda(LocalDate fechaDonacion, Colaborador colaborador) {
    this.fechaDonacion = fechaDonacion;
    this.colaborador = colaborador;
  }

  public void agregarViandas(Vianda... viandas) {
    this.viandas.addAll(Arrays.stream(viandas).toList());
  }

  public List<DonacionVianda> donar() {
    return this.viandas.stream().map(vianda -> new DonacionVianda(this.colaborador, vianda.getFechaDonacion(), vianda)).toList();
  }

}