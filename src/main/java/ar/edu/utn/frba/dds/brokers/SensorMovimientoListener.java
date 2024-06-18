package ar.edu.utn.frba.dds.brokers;

import ar.edu.utn.frba.dds.domain.heladeras.Heladera;
import ar.edu.utn.frba.dds.domain.heladeras.SensorMovimiento;
import ar.edu.utn.frba.dds.domain.heladeras.SensorTemperatura;
import ar.edu.utn.frba.dds.domain.incidentes.Alerta;
import ar.edu.utn.frba.dds.domain.incidentes.TipoAlerta;
import ar.edu.utn.frba.dds.helpers.DateHelper;
import ar.edu.utn.frba.dds.repositories.ISensorMovimientoRepository;
import ar.edu.utn.frba.dds.repositories.ISensorTemperaturaRepository;
import lombok.Setter;
import org.eclipse.paho.client.mqttv3.IMqttMessageListener;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Optional;

@Setter
public class SensorMovimientoListener implements IMqttMessageListener {

  ISensorMovimientoRepository sensorMovimientoRepository;

  @Override
  public void messageArrived(String s, MqttMessage mqttMessage) {
    // formato del mensaje: ID_SENSOR;TIMESTAMP
    String[] message = s.split(";");
    Optional<SensorMovimiento> sensorMovimientoOpt = sensorMovimientoRepository.buscar(Integer.parseInt(message[0]));

    if (sensorMovimientoOpt.isPresent()) {
      Heladera heladera = sensorMovimientoOpt.get().getHeladera();
      Alerta alerta = new Alerta(heladera, DateHelper.localDateTimeFromTimestamp(Long.parseLong(message[1])), TipoAlerta.FRAUDE);
      alerta.reportar();
    }
  }
}
