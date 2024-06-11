package ar.edu.utn.frba.dds.domain.whatsappSender;

public interface WhatsappSenderAdapter {
    void enviarWhatsapp(String mensaje, String numeroTelefono);
}