package ar.edu.utn.frba.dds.brokers;

import ar.edu.utn.frba.dds.brokers.dtos.AperturaHeladeraBrokerDTO;
import ar.edu.utn.frba.dds.exceptions.HeladeraVaciaException;
import ar.edu.utn.frba.dds.helpers.DateHelper;
import ar.edu.utn.frba.dds.models.domain.heladeras.AperturaHeladera;
import ar.edu.utn.frba.dds.models.domain.heladeras.Heladera;
import ar.edu.utn.frba.dds.models.domain.heladeras.SolicitudAperturaHeladera;
import ar.edu.utn.frba.dds.models.domain.tarjetas.Tarjeta;
import ar.edu.utn.frba.dds.models.domain.tarjetas.TarjetaColaborador;
import ar.edu.utn.frba.dds.models.domain.tarjetas.UsoTarjeta;
import ar.edu.utn.frba.dds.models.messageFactory.MensajeRecursoInexistenteFactory;
import ar.edu.utn.frba.dds.models.repositories.*;
import ar.edu.utn.frba.dds.services.DonacionesViandaService;
import ar.edu.utn.frba.dds.services.RedistribucionViandaService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.eclipse.paho.client.mqttv3.IMqttMessageListener;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import java.util.Optional;

@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AperturaHeladeraListener implements IMqttMessageListener {
  private IHeladerasRepository heladerasRepository;
  private ITarjetasColaboradorRepository tarjetasColaboradorRepository;
  private IAperturasHeladeraRepository aperturasHeladeraRepository;
  private ISolicitudesAperturaHeladeraRepository solicitudesAperturaHeladeraRepository;
  private ITarjetasRepository tarjetasRepository;
  private DonacionesViandaService donacionesViandaService;
  private RedistribucionViandaService redistribucionViandaService;
  private IUsosTarjetaRepository usosTarjetaRepository;

  @Override
  public void messageArrived(String s, MqttMessage mqttMessage) {
    try {
      AperturaHeladeraBrokerDTO aperturaDto = AperturaHeladeraBrokerDTO.fromString(mqttMessage.toString());
      Optional<Heladera> heladeraOpt = heladerasRepository.buscar(aperturaDto.getIdHeladera());
      Optional<TarjetaColaborador> tarjetaOpt = tarjetasColaboradorRepository.buscar(aperturaDto.getIdTarjetaColaborador());
      Optional<Tarjeta> tarjetaPersonaVulnerableOpt = tarjetasRepository.buscar(aperturaDto.getIdTarjetaPersonaVulnerable());
      Optional<SolicitudAperturaHeladera> solicitudAperturaHeladeraOpt = solicitudesAperturaHeladeraRepository.buscar(aperturaDto.getIdSolicitudApertura());
      if (heladeraOpt.isEmpty()) {
        throw new RuntimeException(MensajeRecursoInexistenteFactory.generarMensaje("Heladera"));
      }
      Heladera heladera = heladeraOpt.get();
      AperturaHeladera aperturaHeladera = AperturaHeladera.of(solicitudAperturaHeladeraOpt.orElse(null), DateHelper.fromTimestamp(aperturaDto.getTimestamp()), heladera);
      aperturasHeladeraRepository.guardar(aperturaHeladera);
      if (tarjetaOpt.isPresent()) {
        tarjetaOpt.get().agregarUso(aperturaHeladera);
        if (solicitudAperturaHeladeraOpt.isEmpty())
          throw new RuntimeException(MensajeRecursoInexistenteFactory.generarMensaje("Solcitud apertura", aperturaDto.idSolicitudApertura));
        manejarAperturaColaborador(heladera, solicitudAperturaHeladeraOpt.get());
      } else {
        if (tarjetaPersonaVulnerableOpt.isEmpty())
          throw new RuntimeException(MensajeRecursoInexistenteFactory.generarMensaje("Tarjeta de persona vulnerable", aperturaDto.idTarjetaPersonaVulnerable));
        manejarAperturaPersonaVulnerable(heladera, tarjetaPersonaVulnerableOpt.get(), aperturaHeladera);
      }
    } catch (RuntimeException e) {
      System.err.println("Error in messageArrived: " + e.getMessage());
      e.printStackTrace();
    }

  }

  private void manejarAperturaColaborador(Heladera heladera, SolicitudAperturaHeladera solicitudAperturaHeladera) {
    if (solicitudAperturaHeladera.esDonacionDeViandas()) {
      // asumo que es donacion vianda
      donacionesViandaService.crearDonaciones(solicitudAperturaHeladera.getViandas());
      return;
    }
    try {
      if (heladera.getNombre().equals(solicitudAperturaHeladera.getRedistribucionViandas().getHeladeraOrigen().getNombre())) {
        // estoy en la de origen
        this.redistribucionViandaService.efectuarRedistribucionOrigen(solicitudAperturaHeladera.getRedistribucionViandas());
      } else {
        this.redistribucionViandaService.efecutarRedistribucionDestino(solicitudAperturaHeladera.getRedistribucionViandas());
      }
    } catch (HeladeraVaciaException e) {
      e.printStackTrace();
    }

  }

  private void manejarAperturaPersonaVulnerable(Heladera heladera, Tarjeta tarjeta, AperturaHeladera aperturaHeladera) {
    try {
      // TODO: el que quiera modularizar esto bienvenido sea
      heladera.quitarVianda();
      UsoTarjeta usoTarjeta = UsoTarjeta.of(aperturaHeladera.getTimestamp(), heladera);
      if (!tarjeta.permiteUsar()) usoTarjeta.marcarNoAutorizado();
      this.usosTarjetaRepository.guardar(usoTarjeta);
      tarjeta.agregarUsos(usoTarjeta);
      aperturaHeladera.setUsoTarjeta(usoTarjeta);
      this.tarjetasRepository.actualizar(tarjeta);
    } catch (HeladeraVaciaException e) {
      e.printStackTrace();
    }
    this.aperturasHeladeraRepository.actualizar(aperturaHeladera);
  }

}
