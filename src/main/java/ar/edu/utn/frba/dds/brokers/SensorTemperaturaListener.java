package ar.edu.utn.frba.dds.brokers;

import ar.edu.utn.frba.dds.domain.heladeras.Heladera;
import ar.edu.utn.frba.dds.domain.heladeras.SensorTemperatura;
import ar.edu.utn.frba.dds.domain.incidentes.Alerta;
import ar.edu.utn.frba.dds.domain.incidentes.TipoAlerta;
import ar.edu.utn.frba.dds.repositories.ISensorTemperaturaRepository;
import lombok.Setter;
import org.eclipse.paho.client.mqttv3.IMqttMessageListener;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import java.time.LocalDateTime;
import java.util.Optional;

@Setter
public class SensorTemperaturaListener implements IMqttMessageListener {

  ISensorTemperaturaRepository sensorTemperaturaRepository;

  @Override
  public void messageArrived(String s, MqttMessage mqttMessage) {
    // formato del mensaje: ID_SENSOR;TEMPERATURA
    String message[] = s.split(";");
    Optional<SensorTemperatura> sensorTemperaturaOpt = sensorTemperaturaRepository.buscar(Integer.parseInt(message[0]));

    if (sensorTemperaturaOpt.isPresent()) {
      SensorTemperatura sensorTemperatura = sensorTemperaturaOpt.get();
      Float temp = Float.parseFloat(message[1]);
      sensorTemperatura.registrarTemperatura(temp);

      Heladera heladera = sensorTemperatura.getHeladera();
      if (!heladera.temperaturaEsAdecuada()) {
        Alerta alerta = new Alerta(heladera, LocalDateTime.now(), TipoAlerta.TEMPERATURA);
        alerta.reportar();
      }
    }
  }
}
