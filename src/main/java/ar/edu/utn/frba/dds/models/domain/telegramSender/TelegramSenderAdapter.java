package ar.edu.utn.frba.dds.models.domain.telegramSender;

/**
 * Permite enviar un telegram.
 */
public interface TelegramSenderAdapter {
  void enviarTelegram(String mensaje, String usuarioId);
}