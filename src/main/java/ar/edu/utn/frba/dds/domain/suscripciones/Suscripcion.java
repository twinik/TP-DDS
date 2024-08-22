package ar.edu.utn.frba.dds.domain.suscripciones;

import ar.edu.utn.frba.dds.domain.colaboradores.Colaborador;
import ar.edu.utn.frba.dds.domain.heladeras.Heladera;
import ar.edu.utn.frba.dds.domain.notifications.NotificationStrategy;
import ar.edu.utn.frba.dds.domain.utils.MedioDeContacto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * Representa una suscripcion de la cual un colaborador va a recibir notificaciones
 */
@AllArgsConstructor
@Setter
@Getter
public class Suscripcion {

    private Long id;
    private Colaborador colaborador;
    private NotificationStrategy notificacionStrategy;
    private ITipoSuscripcion tipoSuscripcion;
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