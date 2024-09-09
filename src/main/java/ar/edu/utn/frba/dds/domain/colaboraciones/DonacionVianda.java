package ar.edu.utn.frba.dds.domain.colaboraciones;


import ar.edu.utn.frba.dds.db.EntidadPersistente;
import ar.edu.utn.frba.dds.domain.colaboradores.Colaborador;
import ar.edu.utn.frba.dds.domain.heladeras.Heladera;
import ar.edu.utn.frba.dds.domain.heladeras.Vianda;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.time.LocalDate;

/**
 * DonacionVianda class representa una colaboracion de un colaborador.
 * Consiste en donar viandas.
 */
@Entity
@Table(name = "donacion_vianda")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DonacionVianda extends EntidadPersistente implements IPuntajeCalculable {
    @ManyToOne
    @JoinColumn(name = "colaborador_id", referencedColumnName = "id", nullable = false)
    private Colaborador colaborador;

    @Column(name = "fecha", columnDefinition = "DATE", nullable = false)
    private LocalDate fecha;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "vianda_id", referencedColumnName = "id", nullable = false, unique = true)
    private Vianda vianda;

    //DESNORMALIZACION POR PERFORMANCE BOEEEEEEEEEEEE
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH}, fetch = FetchType.LAZY)
    @JoinColumn(name = "heladera_id", referencedColumnName = "id")
    private Heladera heladera;

    private static final Float PUNTAJE_POR_DONACION = 1.5f;

    public DonacionVianda(Colaborador colaborador, LocalDate fecha, Vianda vianda) {
        this.colaborador = colaborador;
        this.fecha = fecha;
        this.vianda = vianda;
    }

    @Override
    public Float calcularPuntaje() {
        return PUNTAJE_POR_DONACION;
    }
}