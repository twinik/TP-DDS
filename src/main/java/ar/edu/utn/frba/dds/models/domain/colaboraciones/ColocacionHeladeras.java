package ar.edu.utn.frba.dds.models.domain.colaboraciones;

import ar.edu.utn.frba.dds.models.db.EntidadPersistente;
import ar.edu.utn.frba.dds.models.domain.colaboradores.Colaborador;
import ar.edu.utn.frba.dds.models.domain.heladeras.Heladera;
import ar.edu.utn.frba.dds.helpers.DateHelper;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.time.LocalDate;

/**
 * ColocacionHeladeras class representa una colaboracion de un colaborador.
 * Consiste en colocar una heladera en un establecimiento.
 */

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "colocacion_heladera")
public class ColocacionHeladeras extends EntidadPersistente implements IPuntajeCalculable {
    public static final Float PUNTOS_POR_MES = 5.0f;

    @ManyToOne
    @JoinColumn(name = "colaborador_id", referencedColumnName = "id", nullable = false)
    private Colaborador colaborador;

    @Column(name = "fecha", nullable = false, columnDefinition = "DATE")
    private LocalDate fecha;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "heladera_id", referencedColumnName = "id", nullable = false, unique = true)
    private Heladera heladera;

    public ColocacionHeladeras(Colaborador colaborador, LocalDate fecha, Heladera heladera) {
        this.colaborador = colaborador;
        this.fecha = fecha;
        this.heladera = heladera;
    }

    /**
     * Metodo getMesesActiva que devuelve la cantidad de meses que la heladera lleva activa.
     */
    public Integer getMesesActiva() {
        return DateHelper.mesesEntre(this.heladera.getFechaPuestaFuncionamiento(), LocalDate.now());
    }

    @Override
    public Float calcularPuntaje() {
        return PUNTOS_POR_MES * this.getMesesActiva();
    }
}
