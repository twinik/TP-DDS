package ar.edu.utn.frba.dds.domain.heladeras;

import ar.edu.utn.frba.dds.domain.colaboradores.Colaborador;
import ar.edu.utn.frba.dds.helpers.ConfigReader;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import java.io.IOException;
import java.time.LocalDateTime;

/**
 * Registra una solicud de apertura por parte del colabnardor
 */
@Getter
@AllArgsConstructor
public class SolicitudAperturaHeladera {
    private Colaborador colaborador;
    private String motivo;
    private LocalDateTime timestamp;
    private Heladera heladera;

    public void publicarSolicitudABroker() throws IOException {
        ConfigReader configReader = new ConfigReader("config.properties");
        String topic        = configReader.getProperty("APERTURA_APERTURA_HELADERA_BROKER_TOPIC") + "/" + heladera.getNombre(); // cada heladera se va a suscribir a su topic
        int qos             = 2;
        String broker       = configReader.getProperty("APERTURA_APERTURA_HELADERA_BROKER");
        String clientId     = configReader.getProperty("CLIENT_ID");
        MemoryPersistence persistence = new MemoryPersistence();
        String content      = String.join(";", colaborador.getUsuario().getTipoDocumento().toString(), colaborador.getUsuario().getDocumento().toString(), timestamp.toString());

        try {
            MqttClient sampleClient = new MqttClient(broker, clientId, persistence);    //TODO emprolijar esto
            MqttConnectOptions connOpts = new MqttConnectOptions();
            connOpts.setCleanSession(true);
            System.out.println("Connecting to broker: "+broker);
            sampleClient.connect(connOpts);
            System.out.println("Connected");
            System.out.println("Publishing message: "+content);
            MqttMessage message = new MqttMessage(content.getBytes());
            message.setQos(qos);
            sampleClient.publish(topic, message);
            System.out.println("Message published");
            sampleClient.disconnect();
            System.out.println("Disconnected");
            System.exit(0);
        } catch(MqttException me) {
            System.out.println("reason "+me.getReasonCode());
            System.out.println("msg "+me.getMessage());
            System.out.println("loc "+me.getLocalizedMessage());
            System.out.println("cause "+me.getCause());
            System.out.println("excep "+me);
            me.printStackTrace();
        }
    }
}