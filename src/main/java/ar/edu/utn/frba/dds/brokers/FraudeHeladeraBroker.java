package ar.edu.utn.frba.dds.brokers;

import ar.edu.utn.frba.dds.domain.serviceLocator.ServiceLocator;
import ar.edu.utn.frba.dds.helpers.ConfigReader;
import ar.edu.utn.frba.dds.repositories.ISensorMovimientoRepository;
import ar.edu.utn.frba.dds.repositories.ISensorTemperaturaRepository;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import java.io.IOException;

public class FraudeHeladeraBroker {
  public static void suscribirseASensorMovimiento(String[] args) throws IOException {

    ConfigReader configReader = new ConfigReader("config.properties");

    String topic        = configReader.getProperty("SENSOR_MOV_BROKER_TOPIC");
    String broker       = configReader.getProperty("SENSOR_MOV_BROKER");
    String clientId     = "TP_DDS";
    MemoryPersistence persistence = new MemoryPersistence();

    try {
      MqttClient sampleClient = new MqttClient(broker, clientId, persistence);
      MqttConnectOptions connOpts = new MqttConnectOptions();
      connOpts.setCleanSession(true);

      System.out.println("Connecting to broker: "+broker);
      sampleClient.connect(connOpts);
      System.out.println("Connected");

      System.out.println("Building receptor");
      SensorMovimientoListener receptor = new SensorMovimientoListener();
      receptor.setSensorMovimientoRepository((ISensorMovimientoRepository) ServiceLocator.get("SensoresMovimientoRepository"));

      System.out.println("Subscribe to topic");
      sampleClient.subscribe(topic, receptor);

      System.out.println("Right! We are subscribed");
    } catch(MqttException me) {
      System.out.println("reason " + me.getReasonCode());
      System.out.println("msg " + me.getMessage());
      System.out.println("loc " + me.getLocalizedMessage());
      System.out.println("cause " + me.getCause());
      System.out.println("excep " + me);
      me.printStackTrace();
    }
  }
}
