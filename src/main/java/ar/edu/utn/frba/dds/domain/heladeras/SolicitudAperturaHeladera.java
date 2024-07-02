package ar.edu.utn.frba.dds.domain.heladeras;

import ar.edu.utn.frba.dds.brokers.BrokerPublisher;
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
 * Registra una solicud de apertura por parte del colaborador
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
        String broker       = configReader.getProperty("APERTURA_APERTURA_HELADERA_BROKER");
        String clientId     = configReader.getProperty("CLIENT_ID");
        String content      = String.join(";", colaborador.getUsuario().getTipoDocumento().toString(), colaborador.getUsuario().getDocumento().toString(), timestamp.toString());

        BrokerPublisher brokerPublisher = new BrokerPublisher(topic, broker, clientId);
        brokerPublisher.publicar(content);
    }
}