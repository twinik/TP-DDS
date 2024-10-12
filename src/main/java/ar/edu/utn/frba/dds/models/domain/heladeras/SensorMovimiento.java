package ar.edu.utn.frba.dds.models.domain.heladeras;

import ar.edu.utn.frba.dds.models.db.EntidadPersistente;
import lombok.AllArgsConstructor;
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
@AllArgsConstructor
@Table(name = "sensor_movimiento")
public class SensorMovimiento extends EntidadPersistente {
    @ManyToOne
    @JoinColumn(name = "heladera_id", referencedColumnName = "id")
    private Heladera heladera;

    //TODO: esto esta al cuete revisar si merece la pena refactorear incidente o solo borrar esto
    public void alertarRobo() {
        this.heladera.inhabilitar();
    }
}