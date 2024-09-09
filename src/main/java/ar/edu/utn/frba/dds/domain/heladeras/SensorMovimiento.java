package ar.edu.utn.frba.dds.domain.heladeras;

import ar.edu.utn.frba.dds.db.EntidadPersistente;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.Entity;
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
    @ManyToOne
    @JoinColumn(name = "heladera_id", referencedColumnName = "id")
    private Heladera heladera;

    public void alertarRobo() {
        this.heladera.inhabilitar();
    }
}