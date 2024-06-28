package ar.edu.utn.frba.dds.brokers;

import ar.edu.utn.frba.dds.brokers.dtos.AperturaHeladeraBrokerDTO;
import ar.edu.utn.frba.dds.domain.heladeras.AperturaHeladera;
import ar.edu.utn.frba.dds.domain.heladeras.Heladera;
import ar.edu.utn.frba.dds.domain.heladeras.SolicitudAperturaHeladera;
import ar.edu.utn.frba.dds.domain.tarjetas.TarjetaColaborador;
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
  public void messageArrived(String s, MqttMessage mqttMessage) {
    AperturaHeladeraBrokerDTO aperturaDto = AperturaHeladeraBrokerDTO.fromString(s);
    Optional<Heladera> heladeraOpt = heladerasRepository.buscar(aperturaDto.getIdHeladera());
    Optional<TarjetaColaborador> tarjetaOpt = tarjetasColaboradorRepository.buscar(aperturaDto.getIdTarjetaColaborador());
    Optional<SolicitudAperturaHeladera> solicitudAperturaHeladeraOpt = solicitudesAperturaHeladeraRepository.buscar(aperturaDto.getIdSolicitudApertura());
    if (heladeraOpt.isEmpty() || tarjetaOpt.isEmpty() || solicitudAperturaHeladeraOpt.isEmpty()) {
      return;
    }
    Heladera heladera = heladeraOpt.get();
    SolicitudAperturaHeladera solicitudAperturaHeladera = solicitudAperturaHeladeraOpt.get();
    AperturaHeladera aperturaHeladera = new AperturaHeladera(solicitudAperturaHeladera, DateHelper.localDateTimeFromTimestamp(aperturaDto.getTimestamp()), heladera);
    aperturasHeladeraRepository.guardar(aperturaHeladera);
  }
}
