package ar.edu.utn.frba.dds.brokers;

import ar.edu.utn.frba.dds.domain.heladeras.AperturaHeladera;
import ar.edu.utn.frba.dds.domain.heladeras.Heladera;
import ar.edu.utn.frba.dds.domain.tarjetas.TarjetaColaborador;
import ar.edu.utn.frba.dds.helpers.ConfigReader;
import ar.edu.utn.frba.dds.helpers.DateHelper;
import ar.edu.utn.frba.dds.repositories.IAperturasHeladeraRepository;
import ar.edu.utn.frba.dds.repositories.IHeladerasRepository;
import ar.edu.utn.frba.dds.repositories.ITarjetasColaboradorRepository;
import lombok.Setter;
import org.eclipse.paho.client.mqttv3.IMqttMessageListener;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import java.util.Optional;

@Setter
public class AperturaHeladeraListener implements IMqttMessageListener {

  IHeladerasRepository heladerasRepository;
  ITarjetasColaboradorRepository tarjetasColaboradorRepository;
  IAperturasHeladeraRepository aperturasHeladeraRepository;

  @Override
  public void messageArrived(String s, MqttMessage mqttMessage) throws Exception {
    // formato: ID_HELADERA;ID_TARJETA_COLABORADOR
    String[] message = s.split(";"); // TODO cambiar todos los String[] message de todos los listeners de los broker por un ValueObject.
    Optional<Heladera> heladeraOpt = heladerasRepository.buscar(Integer.parseInt(message[0]));
    Optional<TarjetaColaborador> tarjetaOpt = tarjetasColaboradorRepository.buscar(message[1]);
    if (heladeraOpt.isEmpty() || tarjetaOpt.isEmpty()) {
      return;
    }
    Heladera heladera = heladeraOpt.get();
    TarjetaColaborador tarjeta = tarjetaOpt.get();
    ConfigReader configReader = new ConfigReader("config.properties");
    AperturaHeladera aperturaHeladera = AperturaHeladera.from(heladera, tarjeta, DateHelper.localDateTimeFromTimestamp(Long.parseLong(message[2])), configReader);
    aperturasHeladeraRepository.guardar(aperturaHeladera);
  }
}
