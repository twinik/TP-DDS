package ar.edu.utn.frba.dds.brokers;

import ar.edu.utn.frba.dds.domain.serviceLocator.ServiceLocator;
import ar.edu.utn.frba.dds.helpers.ConfigReader;
import ar.edu.utn.frba.dds.repositories.IAperturasHeladeraRepository;
import ar.edu.utn.frba.dds.repositories.IHeladerasRepository;
import ar.edu.utn.frba.dds.repositories.ISolicitudesAperturaHeladeraRepository;
import ar.edu.utn.frba.dds.repositories.ITarjetasColaboradorRepository;
import java.io.IOException;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

/**
 * Clase que se encarga de suscribirse al broker de aperturas de heladeras.
 */

public class AperturaHeladeraBroker {

  /**
   * Método que se encarga de suscribirse al broker de aperturas de heladeras.
   */
  public static void suscribirseAAperturasHeladeras(String[] args) throws IOException {

    ConfigReader configReader = new ConfigReader("config.properties");

    String topic = configReader.getProperty("APERTURA_HELADERA_BROKER_TOPIC");
    String broker = configReader.getProperty("APERTURA_HELADERA_BROKER");
    String clientId = "TP_DDS";
    MemoryPersistence persistence = new MemoryPersistence();

    try {
      MqttClient sampleClient = new MqttClient(broker, clientId, persistence);
      MqttConnectOptions connOpts = new MqttConnectOptions();
      connOpts.setCleanSession(true);

      System.out.println("Connecting to broker: " + broker);
      sampleClient.connect(connOpts);
      System.out.println("Connected");

      System.out.println("Building receptor");
      AperturaHeladeraListener receptor = new AperturaHeladeraListener();
      receptor.setHeladerasRepository((IHeladerasRepository) ServiceLocator.get("heladerasRepository"));
      receptor.setTarjetasColaboradorRepository((ITarjetasColaboradorRepository) ServiceLocator.get("tarjetasColaboradorRepository"));
      receptor.setAperturasHeladeraRepository((IAperturasHeladeraRepository) ServiceLocator.get("aperturasHeladeraRepository"));
      receptor.setSolicitudesAperturaHeladeraRepository((ISolicitudesAperturaHeladeraRepository) ServiceLocator.get("solicitudesAperturaHeladeraRepository"));

      System.out.println("Subscribe to topic");
      sampleClient.subscribe(topic, receptor);

      System.out.println("Right! We are subscribed");
    } catch (MqttException me) {
      System.out.println("reason " + me.getReasonCode());
      System.out.println("msg " + me.getMessage());
      System.out.println("loc " + me.getLocalizedMessage());
      System.out.println("cause " + me.getCause());
      System.out.println("excep " + me);
      me.printStackTrace();
    }
  }
}
