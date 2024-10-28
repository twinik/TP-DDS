package ar.edu.utn.frba.dds.brokers;

import ar.edu.utn.frba.dds.helpers.ConfigReader;
import ar.edu.utn.frba.dds.models.repositories.*;
import ar.edu.utn.frba.dds.serviceLocator.ServiceLocator;
import ar.edu.utn.frba.dds.services.DonacionesViandaService;
import ar.edu.utn.frba.dds.services.RedistribucionViandaService;
import java.io.IOException;

/**
 * Clase que se encarga de suscribirse al broker de aperturas de heladeras.
 */

public class AperturaHeladeraBroker {

  /**
   * Método que se encarga de suscribirse al broker de aperturas de heladeras.
   */
  public static void suscribirseAAperturasHeladeras() throws IOException {

    ConfigReader configReader = new ConfigReader("config.properties");

    String topic = configReader.getProperty("APERTURA_HELADERA_BROKER_TOPIC");
    String broker = configReader.getProperty("APERTURA_HELADERA_BROKER");
    String clientId = configReader.getProperty("CLIENT_APERTURAS_ID");

    AperturaHeladeraListener receptor = new AperturaHeladeraListener();
    receptor.setHeladerasRepository(ServiceLocator.get(IHeladerasRepository.class));
    receptor.setTarjetasColaboradorRepository(ServiceLocator.get(ITarjetasColaboradorRepository.class));
    receptor.setAperturasHeladeraRepository(ServiceLocator.get(IAperturasHeladeraRepository.class));
    receptor.setSolicitudesAperturaHeladeraRepository(ServiceLocator.get(ISolicitudesAperturaHeladeraRepository.class));
    receptor.setTarjetasRepository(ServiceLocator.get(ITarjetasRepository.class));
    receptor.setRedistribucionViandaService(ServiceLocator.get(RedistribucionViandaService.class));
    receptor.setDonacionesViandaService(ServiceLocator.get(DonacionesViandaService.class));
    receptor.setUsosTarjetaRepository(ServiceLocator.get(IUsosTarjetaRepository.class));

    BrokerSubscriber brokerSubscriber = new BrokerSubscriber(topic, broker, clientId, receptor);
    brokerSubscriber.listen();
  }

  public static void main(String[] args) {
//        Colaborador c = new Colaborador();
//        c.setUsuario(new Usuario("dfmaskdmf","fladsmlfmds"));
//        c.setDireccion(new Direccion("hola", 534, 1, "434"));
//        IHeladerasRepository heladerasRepository = ServiceLocator.get(IHeladerasRepository.class);
//        Heladera heladera = new Heladera(LocalDate.now());
//        // heladera.setId(2L);
//        heladera.setNombre("heladerita");
//        heladerasRepository.guardar(heladera);
//        ServiceLocator.get(IColaboradoresRepository.class).guardar(c);
//        ITarjetasColaboradorRepository tarjetasColaboradorRepository = ServiceLocator.get(ITarjetasColaboradorRepository.class);
//        TarjetaColaborador tarjetaColaborador = TarjetaColaborador.of(c, GeneradorDeCodigosHelper.generarAlfanumericoUnico(11), LocalDate.now());
//        ///tarjetaColaborador.setId(3L);
//        tarjetasColaboradorRepository.guardar(tarjetaColaborador);
//        ISolicitudesAperturaHeladeraRepository solicitudesAperturaHeladeraRepository = ServiceLocator.get(ISolicitudesAperturaHeladeraRepository.class);SolicitudAperturaHeladera solicitudAperturaHeladera = new SolicitudAperturaHeladera(c, "un motivo", LocalDateTime.now(), heladera, new RedistribucionViandas(),new IngresoVianda());
//        solicitudesAperturaHeladeraRepository.guardar(solicitudAperturaHeladera);
    try {
      suscribirseAAperturasHeladeras();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}


