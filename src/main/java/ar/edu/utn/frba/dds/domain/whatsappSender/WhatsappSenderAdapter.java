package ar.edu.utn.frba.dds.domain.whatsappSender;

/**
 * Permite enviar un WhatsApp.
 */
public interface WhatsappSenderAdapter {
    void enviarWhatsapp(String mensaje, String numeroTelefono);
}