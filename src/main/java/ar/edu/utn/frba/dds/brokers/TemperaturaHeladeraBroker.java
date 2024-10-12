package ar.edu.utn.frba.dds.brokers;

import ar.edu.utn.frba.dds.helpers.ConfigReader;
import ar.edu.utn.frba.dds.models.repositories.IAlertasRepository;
import ar.edu.utn.frba.dds.models.repositories.IHeladerasRepository;
import ar.edu.utn.frba.dds.models.repositories.ISensorTemperaturaRepository;
import ar.edu.utn.frba.dds.serviceLocator.ServiceLocator;
import java.io.IOException;

public class TemperaturaHeladeraBroker {
  public static void suscribirseASensorTemperatura() throws IOException {

    ConfigReader configReader = new ConfigReader("config.properties");

    String topic = configReader.getProperty("SENSOR_TEMP_BROKER_TOPIC");
    String broker = configReader.getProperty("SENSOR_TEMP_BROKER");
    String clientId = configReader.getProperty("CLIENT_TEMPERATURA_ID");

    SensorTemperaturaListener receptor = new SensorTemperaturaListener();
    receptor.setSensorTemperaturaRepository(ServiceLocator.get(ISensorTemperaturaRepository.class));
    receptor.setHeladerasRepository(ServiceLocator.get(IHeladerasRepository.class));
    receptor.setAlertasRepository(ServiceLocator.get(IAlertasRepository.class));

    BrokerSubscriber brokerSubscriber = new BrokerSubscriber(topic, broker, clientId, receptor);
    brokerSubscriber.listen();
  }

  public static void main(String[] args) {
    try {
      suscribirseASensorTemperatura();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
