package ar.edu.utn.frba.dds.domain.notifications;

import ar.edu.utn.frba.dds.domain.emailSending.MailSenderAdapter;
import ar.edu.utn.frba.dds.domain.utils.MedioDeContacto;
import lombok.AllArgsConstructor;

/**
 * 
 */
@AllArgsConstructor
public class MailNotificationStrategy implements NotificationStrategy {
    private MailSenderAdapter mailSenderAdapter;

    /**
     * @param medioContacto
     */
    public void notificar(MedioDeContacto medioContacto) {
        // TODO implement here
    }

}