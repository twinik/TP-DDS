package ar.edu.utn.frba.dds.brokers;

import ar.edu.utn.frba.dds.brokers.dtos.SensorMovimientoBrokerDto;
import ar.edu.utn.frba.dds.helpers.DateHelper;
import ar.edu.utn.frba.dds.helpers.TecnicosHelper;
import ar.edu.utn.frba.dds.models.domain.heladeras.Heladera;
import ar.edu.utn.frba.dds.models.domain.heladeras.SensorMovimiento;
import ar.edu.utn.frba.dds.models.domain.incidentes.Alerta;
import ar.edu.utn.frba.dds.models.domain.incidentes.TipoAlerta;
import ar.edu.utn.frba.dds.models.domain.notifications.NotificationStrategyFactory;
import ar.edu.utn.frba.dds.models.repositories.IAlertasRepository;
import ar.edu.utn.frba.dds.models.repositories.ISensorMovimientoRepository;
import ar.edu.utn.frba.dds.serviceLocator.ServiceLocator;
import lombok.Setter;
import org.eclipse.paho.client.mqttv3.IMqttMessageListener;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import java.util.Optional;

@Setter
public class SensorMovimientoListener implements IMqttMessageListener {
  private ISensorMovimientoRepository sensorMovimientoRepository;
  private IAlertasRepository alertasRepository;

  @Override
  public void messageArrived(String s, MqttMessage mqttMessage) {
    try{
      SensorMovimientoBrokerDto sensorDto = SensorMovimientoBrokerDto.fromString(mqttMessage.toString());
      Optional<SensorMovimiento> sensorMovimientoOpt = sensorMovimientoRepository.buscar(sensorDto.getIdSensor());

      if (sensorMovimientoOpt.isPresent()) {
        Heladera heladera = sensorMovimientoOpt.get().getHeladera();
        Alerta alerta = Alerta.of(heladera, DateHelper.localDateTimeFromTimestamp(sensorDto.getTimestamp()), ServiceLocator.get(TecnicosHelper.class)
            , new NotificationStrategyFactory(), TipoAlerta.FRAUDE);
        alerta.reportar();
        this.alertasRepository.guardar(alerta);
      }
    } catch (RuntimeException e){
      e.printStackTrace();
    }

  }
}
