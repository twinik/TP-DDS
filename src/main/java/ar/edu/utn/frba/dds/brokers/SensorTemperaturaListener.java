package ar.edu.utn.frba.dds.brokers;

import ar.edu.utn.frba.dds.brokers.dtos.SensorTemperaturaBrokerDto;
import ar.edu.utn.frba.dds.domain.heladeras.Heladera;
import ar.edu.utn.frba.dds.domain.heladeras.SensorTemperatura;
import ar.edu.utn.frba.dds.domain.incidentes.Alerta;
import ar.edu.utn.frba.dds.domain.incidentes.TipoAlerta;
import ar.edu.utn.frba.dds.domain.notifications.NotificationStrategyFactory;
import ar.edu.utn.frba.dds.helpers.DateHelper;
import ar.edu.utn.frba.dds.helpers.TecnicosHelper;
import ar.edu.utn.frba.dds.repositories.IAlertasRepository;
import ar.edu.utn.frba.dds.repositories.ISensorTemperaturaRepository;
import ar.edu.utn.frba.dds.repositories.ITecnicosRepository;
import ar.edu.utn.frba.dds.serviceLocator.ServiceLocator;
import lombok.Setter;
import org.eclipse.paho.client.mqttv3.IMqttMessageListener;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import java.util.Optional;

@Setter
public class SensorTemperaturaListener implements IMqttMessageListener {
    ISensorTemperaturaRepository sensorTemperaturaRepository;

    @Override
    public void messageArrived(String s, MqttMessage mqttMessage) {
        SensorTemperaturaBrokerDto sensorDto = SensorTemperaturaBrokerDto.fromString(mqttMessage.toString());
        Optional<SensorTemperatura> sensorTemperaturaOpt = sensorTemperaturaRepository.buscar(sensorDto.getIdSensor());

        if (sensorTemperaturaOpt.isPresent()) {
            SensorTemperatura sensorTemperatura = sensorTemperaturaOpt.get();
            sensorTemperatura.registrarTemperatura(sensorDto.getTemperatura());

            Heladera heladera = sensorTemperatura.getHeladera();
            if (!heladera.temperaturaEsAdecuada()) {
                Alerta alerta = Alerta.of(heladera, DateHelper.localDateTimeFromTimestamp(sensorDto.getTimestamp()), new TecnicosHelper(ServiceLocator.get(ITecnicosRepository.class))
                        , new NotificationStrategyFactory(), TipoAlerta.TEMPERATURA);
                alerta.reportar();
                IAlertasRepository repository = ServiceLocator.get(IAlertasRepository.class);
                repository.guardar(alerta);
            }
        }
    }
}