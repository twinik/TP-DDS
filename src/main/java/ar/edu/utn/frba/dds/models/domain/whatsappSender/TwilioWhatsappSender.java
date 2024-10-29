package ar.edu.utn.frba.dds.models.domain.whatsappSender;

import ar.edu.utn.frba.dds.helpers.ConfigReader;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import java.io.IOException;


public class TwilioWhatsappSender implements WhatsappSenderAdapter {
  // Your Twilio Account SID and Auth Token from twilio.com/console
  private final String ACCOUNT_SID;
  private final String AUTH_TOKEN;

  private final String TWILIO_NUMBER;
  private ConfigReader config;

  public TwilioWhatsappSender() {
    config = new ConfigReader("config.properties");
    try {
      this.ACCOUNT_SID = config.getProperty("TWILIO_SID");
      this.AUTH_TOKEN = config.getProperty("TWILIO_AUTH_TOKEN");
      this.TWILIO_NUMBER = config.getProperty("TWILIO_NUMBER");
      Twilio.init(this.ACCOUNT_SID, this.AUTH_TOKEN);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  public static void main(String[] args) {
    // Example usage of the sendWhatsAppMessage method
    String toWhatsAppNumber = "whatsapp:+"; // agregar numero de telefono valido
    String messageBody = "Hello, this is a message from your Java application!";

    TwilioWhatsappSender sender = new TwilioWhatsappSender();

    sender.enviarWhatsapp(messageBody, toWhatsAppNumber);
    System.out.println("Message sent!");
  }

  public void enviarWhatsapp(String mensaje, String numeroTelefono) {
    // Send the WhatsApp message
    Message.creator(
        new PhoneNumber("whatsapp:" +  numeroTelefono),
        new PhoneNumber(TWILIO_NUMBER),
        mensaje
    ).create();

  }
}
