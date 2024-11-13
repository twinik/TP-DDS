package ar.edu.utn.frba.dds.services;

import ar.edu.utn.frba.dds.brokers.BrokerPublisher;
import ar.edu.utn.frba.dds.helpers.ConfigReader;
import ar.edu.utn.frba.dds.helpers.DateHelper;
import ar.edu.utn.frba.dds.models.domain.colaboraciones.RedistribucionViandas;
import ar.edu.utn.frba.dds.models.domain.heladeras.IngresoVianda;
import ar.edu.utn.frba.dds.models.domain.heladeras.SolicitudAperturaHeladera;
import ar.edu.utn.frba.dds.models.repositories.ISolicitudesAperturaHeladeraRepository;
import lombok.AllArgsConstructor;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.concurrent.CompletableFuture;

@AllArgsConstructor
public class SolicitudAperturaHeladeraService {
  private ISolicitudesAperturaHeladeraRepository solicitudesAperturaHeladeraRepository;


  public void generarSolicitud(IngresoVianda ingreso) {
    SolicitudAperturaHeladera solicitudAperturaHeladera = new SolicitudAperturaHeladera();
    solicitudAperturaHeladera.setColaborador(ingreso.getColaborador());
    solicitudAperturaHeladera.setHeladera(ingreso.getHeladera());
    solicitudAperturaHeladera.setMotivo("apertura para ingresar una donacion");
    solicitudAperturaHeladera.setViandas(ingreso);
    solicitudAperturaHeladera.setTimestamp(LocalDateTime.now());
    this.publicarABrokerAsync(solicitudAperturaHeladera);
  }

  public void generarSolicitud(RedistribucionViandas redistribucionViandas) {
    // ORIGEN
    SolicitudAperturaHeladera solicitudAperturaHeladeraOrigen = new SolicitudAperturaHeladera();
    solicitudAperturaHeladeraOrigen.setColaborador(redistribucionViandas.getColaborador());
    solicitudAperturaHeladeraOrigen.setRedistribucionViandas(redistribucionViandas);
    solicitudAperturaHeladeraOrigen.setHeladera(redistribucionViandas.getHeladeraOrigen());
    solicitudAperturaHeladeraOrigen.setMotivo("Apertura para redistribuir viandas");
    solicitudAperturaHeladeraOrigen.setTimestamp(LocalDateTime.now());

    //DESTINO
    SolicitudAperturaHeladera solicitudAperturaDestino = new SolicitudAperturaHeladera();
    solicitudAperturaDestino.setColaborador(solicitudAperturaHeladeraOrigen.getColaborador());
    solicitudAperturaDestino.setHeladera(redistribucionViandas.getHeladeraDestino());
    solicitudAperturaDestino.setRedistribucionViandas(redistribucionViandas);
    solicitudAperturaDestino.setMotivo(solicitudAperturaHeladeraOrigen.getMotivo());
    solicitudAperturaDestino.setTimestamp(LocalDateTime.now());


    this.publicarABrokerAsync(solicitudAperturaHeladeraOrigen);
    this.publicarABrokerAsync(solicitudAperturaDestino);
  }

  private void publicarABroker(SolicitudAperturaHeladera solicitudAperturaHeladera) {
    this.solicitudesAperturaHeladeraRepository.guardar(solicitudAperturaHeladera);
    try {
      ConfigReader configReader = new ConfigReader("config.properties");
      String topic = configReader.getProperty("APERTURA_APERTURA_HELADERA_BROKER_TOPIC") + "/" + solicitudAperturaHeladera.getHeladera().getNombre(); // cada heladera se va a suscribir a su topic
      String broker = configReader.getProperty("APERTURA_APERTURA_HELADERA_BROKER");
      String clientId = configReader.getProperty("CLIENT_ID");

      String timestampEnMilis = Long.toString(DateHelper.getTimestamp(solicitudAperturaHeladera.getTimestamp()));
      String content = String.join(";", solicitudAperturaHeladera.getColaborador().getId(), timestampEnMilis, solicitudAperturaHeladera.getId());

      BrokerPublisher brokerPublisher = new BrokerPublisher(topic, broker, clientId);
      brokerPublisher.publicar(content);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  private void publicarABrokerAsync(SolicitudAperturaHeladera solicitudAperturaHeladera) {
    CompletableFuture.runAsync(() -> this.publicarABroker(solicitudAperturaHeladera));
  }
}
