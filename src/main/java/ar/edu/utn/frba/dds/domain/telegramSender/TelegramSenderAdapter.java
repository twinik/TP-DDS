package ar.edu.utn.frba.dds.domain.telegramSender;

/**
 * permite enviar un telegram
 */
public interface TelegramSenderAdapter {
    void enviarTelegram(String mensaje, String numeroTelefono);
}