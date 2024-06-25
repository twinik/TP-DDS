package ar.edu.utn.frba.dds.brokers;

import ar.edu.utn.frba.dds.serviceLocator.ServiceLocator;
import ar.edu.utn.frba.dds.helpers.ConfigReader;
import ar.edu.utn.frba.dds.repositories.ISensorTemperaturaRepository;
import java.io.IOException;

public class TemperaturaHeladeraBroker {
  public static void suscribirseASensorTemperatura(String[] args) throws IOException {

    ConfigReader configReader = new ConfigReader("config.properties");

    String topic        = configReader.getProperty("SENSOR_TEMP_BROKER_TOPIC");
    String broker       = configReader.getProperty("SENSOR_TEMP_BROKER");
    String clientId     = "TP_DDS";

    SensorTemperaturaListener receptor = new SensorTemperaturaListener();
    receptor.setSensorTemperaturaRepository((ISensorTemperaturaRepository) ServiceLocator.get("sensoresTemperaturaRepository"));

    BrokerListener brokerListener = new BrokerListener(topic, broker, clientId, receptor);
    brokerListener.listen();
  }
}
