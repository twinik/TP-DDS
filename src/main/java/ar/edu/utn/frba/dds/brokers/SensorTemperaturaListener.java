package ar.edu.utn.frba.dds.brokers;

import ar.edu.utn.frba.dds.brokers.dtos.SensorTemperaturaBrokerDto;
import ar.edu.utn.frba.dds.helpers.DateHelper;
import ar.edu.utn.frba.dds.helpers.TecnicosHelper;
import ar.edu.utn.frba.dds.models.domain.heladeras.Heladera;
import ar.edu.utn.frba.dds.models.domain.heladeras.SensorTemperatura;
import ar.edu.utn.frba.dds.models.domain.incidentes.Alerta;
import ar.edu.utn.frba.dds.models.domain.incidentes.TipoAlerta;
import ar.edu.utn.frba.dds.models.domain.notifications.NotificationStrategyFactory;
import ar.edu.utn.frba.dds.models.repositories.IAlertasRepository;
import ar.edu.utn.frba.dds.models.repositories.IHeladerasRepository;
import ar.edu.utn.frba.dds.models.repositories.ISensorTemperaturaRepository;
import ar.edu.utn.frba.dds.serviceLocator.ServiceLocator;
import ar.edu.utn.frba.dds.services.AlertasService;
import lombok.Setter;
import org.eclipse.paho.client.mqttv3.IMqttMessageListener;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import java.util.Optional;

@Setter
public class SensorTemperaturaListener implements IMqttMessageListener {
  private ISensorTemperaturaRepository sensorTemperaturaRepository;
  private IHeladerasRepository heladerasRepository;
  private AlertasService alertasService;

  @Override
  public void messageArrived(String s, MqttMessage mqttMessage) {
    try{
      SensorTemperaturaBrokerDto sensorDto = SensorTemperaturaBrokerDto.fromString(mqttMessage.toString());
      Optional<SensorTemperatura> sensorTemperaturaOpt = sensorTemperaturaRepository.buscar(sensorDto.getIdSensor());

      if (sensorTemperaturaOpt.isPresent()) {
        SensorTemperatura sensorTemperatura = sensorTemperaturaOpt.get();
        sensorTemperatura.registrarTemperatura(sensorDto.getTemperatura());

        Heladera heladera = sensorTemperatura.getHeladera();
        heladerasRepository.actualizar(heladera);
        if (!heladera.temperaturaEsAdecuada()) {
          Alerta alerta = Alerta.of(heladera, DateHelper.fromTimestamp(sensorDto.getTimestamp()), ServiceLocator.get(TecnicosHelper.class)
              , new NotificationStrategyFactory(), TipoAlerta.TEMPERATURA);
          this.alertasService.reportarYGuardarSiNoEstabaElMismoProblema(alerta, heladera);
        }
      }
    }catch (RuntimeException e){
      e.printStackTrace();
    }

  }
}