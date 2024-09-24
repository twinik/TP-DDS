package ar.edu.utn.frba.dds.models.domain.colaboraciones;

import ar.edu.utn.frba.dds.models.db.EntidadPersistente;
import ar.edu.utn.frba.dds.models.domain.colaboraciones.utils.FrecuenciaDonacion;
import ar.edu.utn.frba.dds.models.domain.colaboradores.Colaborador;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.time.LocalDate;


/**
 * DonacionDinero class representa una colaboracion de un colaborador.
 * Consiste en donar dinero.
 */
@Entity
@Table(name = "donacion_dinero")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DonacionDinero extends EntidadPersistente implements IPuntajeCalculable {
    private static final Float COEFICIENTE_DONACION_DINERO = .5f;


    @ManyToOne
    @JoinColumn(name = "colaborador_id", referencedColumnName = "id", nullable = false)
    private Colaborador colaborador;

    @Column(name = "fecha", columnDefinition = "DATE", nullable = false)
    private LocalDate fecha;

    @Column(name = "monto_donacion", nullable = false)
    private Float monto;

    @Enumerated(EnumType.STRING)
    private FrecuenciaDonacion frecuencia;

    @Override
    public Float calcularPuntaje() {
        return COEFICIENTE_DONACION_DINERO * this.monto;
    }
}