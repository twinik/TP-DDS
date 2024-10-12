package ar.edu.utn.frba.dds.models.domain.colaboraciones;

import ar.edu.utn.frba.dds.models.db.EntidadPersistente;
import ar.edu.utn.frba.dds.models.domain.colaboraciones.utils.MotivoRedistribucionVianda;
import ar.edu.utn.frba.dds.models.domain.colaboradores.Colaborador;
import ar.edu.utn.frba.dds.models.domain.heladeras.Heladera;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.time.LocalDate;


/**
 * RedistribucionViandas class representa una colaboracion de un colaborador.
 * Representa el movimiento de una vianda entre heladeras.
 */

@Entity
@Table(name = "redistribucion_viandas")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RedistribucionViandas extends EntidadPersistente implements IPuntajeCalculable {
    @ManyToOne
    @JoinColumn(name = "colaborador_id", referencedColumnName = "id", nullable = false)
    private Colaborador colaborador;

    @Column(name = "fecha_redistribucion", columnDefinition = "DATE", nullable = false)
    private LocalDate fecha;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "heladera_origen", referencedColumnName = "id", nullable = false)
    private Heladera heladeraOrigen;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "heladera_destino", referencedColumnName = "id", nullable = false)
    private Heladera heladeraDestino;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "motivo_id", referencedColumnName = "id")
    private MotivoRedistribucionVianda motivo;

    @Column(name = "cantidad")
    private Integer cantidad;

    public void redistribuirEnOrigen(){
        this.heladeraOrigen.quitarVianda(this.cantidad);
    }

    public void redistribuirEnDestino(){
        this.heladeraDestino.agregarVianda(this.cantidad);
    }
    @Override
    public Float calcularPuntaje() {
        return (float) this.cantidad;
    }
}