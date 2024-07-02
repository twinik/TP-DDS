package ar.edu.utn.frba.dds.brokers;

import ar.edu.utn.frba.dds.serviceLocator.ServiceLocator;
import ar.edu.utn.frba.dds.helpers.ConfigReader;
import ar.edu.utn.frba.dds.repositories.IAperturasHeladeraRepository;
import ar.edu.utn.frba.dds.repositories.IHeladerasRepository;
import ar.edu.utn.frba.dds.repositories.ISolicitudesAperturaHeladeraRepository;
import ar.edu.utn.frba.dds.repositories.ITarjetasColaboradorRepository;
import java.io.IOException;

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
    String clientId = configReader.getProperty("CLIENT_ID");

    AperturaHeladeraListener receptor = new AperturaHeladeraListener();
    receptor.setHeladerasRepository((IHeladerasRepository) ServiceLocator.get("heladerasRepository"));
    receptor.setTarjetasColaboradorRepository((ITarjetasColaboradorRepository) ServiceLocator.get("tarjetasColaboradorRepository"));
    receptor.setAperturasHeladeraRepository((IAperturasHeladeraRepository) ServiceLocator.get("aperturasHeladeraRepository"));
    receptor.setSolicitudesAperturaHeladeraRepository((ISolicitudesAperturaHeladeraRepository) ServiceLocator.get("solicitudesAperturaHeladeraRepository"));

    BrokerSubscriber brokerSubscriber = new BrokerSubscriber(topic, broker, clientId, receptor);
    brokerSubscriber.listen();
  }
}
