package ar.edu.utn.frba.dds.domain.telegramSender;

/**
 * Permite enviar un telegram.
 */
public interface TelegramSenderAdapter {
    void enviarTelegram(String mensaje, String usuarioId);
}