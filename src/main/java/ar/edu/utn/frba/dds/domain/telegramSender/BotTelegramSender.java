package ar.edu.utn.frba.dds.domain.telegramSender;

import ar.edu.utn.frba.dds.helpers.ConfigReader;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import java.io.IOException;

/**
 * Permite enviar un mensaje de Telegram.
 */
public class BotTelegramSender extends TelegramLongPollingBot implements TelegramSenderAdapter {

    private final String BOT_USERNAME;
    private final String BOT_TOKEN;

    private ConfigReader config;

    public BotTelegramSender() {
        config = new ConfigReader("config.properties");
        try {
            this.BOT_USERNAME = config.getProperty("BOT_TELEGRAM_USERNAME");
            this.BOT_TOKEN = config.getProperty("BOT_TELEGRAM_TOKEN");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String getBotUsername() {
        return this.BOT_USERNAME;
    }

    @Override
    public String getBotToken() {
        return this.BOT_TOKEN;
    }

    @Override
    public void onUpdateReceived(Update update) {
        // No se implementa
    }


    /**
     * Método principal para enviar un mensaje de Telegram.
     */
    public static void main(String[] args) {
        try {
            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
            BotTelegramSender bot = new BotTelegramSender();
            botsApi.registerBot(bot);

            // Agrega aquí el ID de usuario de Telegram del técnico
            String usuarioId = "usuarioID";
            // Agrega aquí el mensaje que deseas enviar
            String mensaje = "Este es un mensaje del Grupo 7 de dds";

            // Llama al método enviarTelegram
            bot.enviarTelegram(mensaje, usuarioId);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }


    public void enviarTelegram(String mensaje, String usuarioId) {
        SendMessage message = new SendMessage();
        message.setChatId(usuarioId);
        message.setText(mensaje);

        try {
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
