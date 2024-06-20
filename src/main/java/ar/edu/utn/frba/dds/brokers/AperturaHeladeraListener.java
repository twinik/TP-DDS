package ar.edu.utn.frba.dds.brokers;

import ar.edu.utn.frba.dds.domain.heladeras.AperturaHeladera;
import ar.edu.utn.frba.dds.domain.heladeras.Heladera;
import ar.edu.utn.frba.dds.domain.heladeras.SolicitudAperturaHeladera;
import ar.edu.utn.frba.dds.domain.tarjetas.TarjetaColaborador;
import ar.edu.utn.frba.dds.helpers.ConfigReader;
import ar.edu.utn.frba.dds.helpers.DateHelper;
import ar.edu.utn.frba.dds.repositories.IAperturasHeladeraRepository;
import ar.edu.utn.frba.dds.repositories.IHeladerasRepository;
import ar.edu.utn.frba.dds.repositories.ISolicitudesAperturaHeladeraRepository;
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
  ISolicitudesAperturaHeladeraRepository solicitudesAperturaHeladeraRepository;

  @Override
  public void messageArrived(String s, MqttMessage mqttMessage) throws Exception {
    // formato: ID_HELADERA;ID_TARJETA_COLABORADOR;ID_SOLICITUD_APERTURA;TIMESTAMP
    String[] message = s.split(";"); // TODO cambiar todos los String[] message de todos los listeners de los broker por un ValueObject.
    Optional<Heladera> heladeraOpt = heladerasRepository.buscar(Integer.parseInt(message[0]));
    Optional<TarjetaColaborador> tarjetaOpt = tarjetasColaboradorRepository.buscar(message[1]);
    Optional<SolicitudAperturaHeladera> solicitudAperturaHeladeraOpt = solicitudesAperturaHeladeraRepository.buscar(Integer.parseInt(message[2]));
    if (heladeraOpt.isEmpty() || tarjetaOpt.isEmpty() || solicitudAperturaHeladeraOpt.isEmpty()) {
      return;
    }
    Heladera heladera = heladeraOpt.get();
    TarjetaColaborador tarjeta = tarjetaOpt.get();
    SolicitudAperturaHeladera solicitudAperturaHeladera = solicitudAperturaHeladeraOpt.get();
    AperturaHeladera aperturaHeladera = new AperturaHeladera(solicitudAperturaHeladera, DateHelper.localDateTimeFromTimestamp(Long.parseLong(message[3])), heladera);
    aperturasHeladeraRepository.guardar(aperturaHeladera);
  }
}
