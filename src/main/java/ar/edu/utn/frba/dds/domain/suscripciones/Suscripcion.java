package ar.edu.utn.frba.dds.domain.suscripciones;

import ar.edu.utn.frba.dds.domain.colaboradores.Colaborador;
import ar.edu.utn.frba.dds.domain.heladeras.Heladera;
import ar.edu.utn.frba.dds.domain.notifications.NotificationStrategy;
import ar.edu.utn.frba.dds.domain.utils.MedioDeContacto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;

/**
 * Representa una suscripcion de la cual un colaborador va a recibir notificaciones
 */
@Entity
@Table(name = "suscripcion")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Suscripcion {

    @Id
    @GeneratedValue
    private Long id;

    @OneToOne
    @JoinColumn(name = "colaborador_id", referencedColumnName = "id")
    private Colaborador colaborador;

    @Transient
    private NotificationStrategy notificacionStrategy;

    @Transient
    private ITipoSuscripcion tipoSuscripcion;

    @Column(name = "numero")
    private int numero;

    public Suscripcion(Colaborador colaborador, NotificationStrategy notificacionStrategy, ITipoSuscripcion tipoSuscripcion, int numero) {
        this.colaborador = colaborador;
        this.notificacionStrategy = notificacionStrategy;
        this.tipoSuscripcion = tipoSuscripcion;
        this.numero = numero;
    }

    public void avisarEvento(Heladera heladera) {
        tipoSuscripcion.notificar(heladera, this);
    }

}