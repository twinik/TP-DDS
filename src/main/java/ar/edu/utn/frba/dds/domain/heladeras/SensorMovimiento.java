package ar.edu.utn.frba.dds.domain.heladeras;

import ar.edu.utn.frba.dds.db.EntidadPersistente;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * SensorMovimiento class permite representar un sensor de movimiento.
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "sensor_movimiento")
public class SensorMovimiento extends EntidadPersistente {
  // TODO: PREGUNTAR CARDINALIDAD EN LOS SENSORES IGUAL ES LO MISMO
  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "heladera_id",referencedColumnName = "id")
  private Heladera heladera;
  public void alertarRobo() {
    this.heladera.inhabilitar();
  }

}