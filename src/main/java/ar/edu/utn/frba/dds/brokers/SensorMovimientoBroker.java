package ar.edu.utn.frba.dds.brokers;

import ar.edu.utn.frba.dds.domain.serviceLocator.ServiceLocator;
import ar.edu.utn.frba.dds.helpers.ConfigReader;
import ar.edu.utn.frba.dds.repositories.ISensorMovimientoRepository;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import java.io.IOException;

public class SensorMovimientoBroker {
  public static void suscribirseASensorMovimiento(String[] args) throws IOException {

    ConfigReader configReader = new ConfigReader("config.properties");

    String topic        = configReader.getProperty("SENSOR_MOV_BROKER_TOPIC");
    String broker       = configReader.getProperty("SENSOR_MOV_BROKER");
    String clientId     = "TP_DDS";

    SensorMovimientoListener receptor = new SensorMovimientoListener();
    receptor.setSensorMovimientoRepository((ISensorMovimientoRepository) ServiceLocator.get("sensoresMovimientoRepository"));

    BrokerListener brokerListener = new BrokerListener(topic, broker, clientId, receptor);
    brokerListener.listen();
  }
}
