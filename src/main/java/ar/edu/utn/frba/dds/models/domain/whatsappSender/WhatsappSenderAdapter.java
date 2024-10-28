package ar.edu.utn.frba.dds.models.domain.whatsappSender;

/**
 * Permite enviar un WhatsApp.
 */
public interface WhatsappSenderAdapter {
  void enviarWhatsapp(String mensaje, String numeroTelefono);
}