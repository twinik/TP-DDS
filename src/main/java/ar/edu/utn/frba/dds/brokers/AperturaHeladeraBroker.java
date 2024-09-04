package ar.edu.utn.frba.dds.brokers;

import ar.edu.utn.frba.dds.domain.colaboradores.Colaborador;
import ar.edu.utn.frba.dds.domain.heladeras.Heladera;
import ar.edu.utn.frba.dds.domain.heladeras.SolicitudAperturaHeladera;
import ar.edu.utn.frba.dds.domain.tarjetas.TarjetaColaborador;
import ar.edu.utn.frba.dds.domain.utils.Direccion;
import ar.edu.utn.frba.dds.serviceLocator.ServiceLocator;
import ar.edu.utn.frba.dds.helpers.ConfigReader;
import ar.edu.utn.frba.dds.repositories.IAperturasHeladeraRepository;
import ar.edu.utn.frba.dds.repositories.IHeladerasRepository;
import ar.edu.utn.frba.dds.repositories.ISolicitudesAperturaHeladeraRepository;
import ar.edu.utn.frba.dds.repositories.ITarjetasColaboradorRepository;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;

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
        String clientId = configReader.getProperty("CLIENT_ID");

        AperturaHeladeraListener receptor = new AperturaHeladeraListener();
        receptor.setHeladerasRepository(ServiceLocator.get("heladerasRepository", IHeladerasRepository.class));
        receptor.setTarjetasColaboradorRepository(ServiceLocator.get("tarjetasColaboradorRepository", ITarjetasColaboradorRepository.class));
        receptor.setAperturasHeladeraRepository(ServiceLocator.get("aperturasHeladeraRepository", IAperturasHeladeraRepository.class));
        receptor.setSolicitudesAperturaHeladeraRepository(ServiceLocator.get("solicitudesAperturaHeladeraRepository", ISolicitudesAperturaHeladeraRepository.class));

        BrokerSubscriber brokerSubscriber = new BrokerSubscriber(topic, broker, clientId, receptor);
        brokerSubscriber.listen();
    }

    public static void main(String[] args) {
        Colaborador c = new Colaborador();
        c.setDireccion(new Direccion("hola", 534, 1, "434"));
        IHeladerasRepository heladerasRepository = ServiceLocator.get("heladerasRepository", IHeladerasRepository.class);
        Heladera heladera = new Heladera(LocalDate.now());
        // heladera.setId(2L);
        heladera.setNombre("heladerita");
        heladerasRepository.guardar(heladera);
        ITarjetasColaboradorRepository tarjetasColaboradorRepository = ServiceLocator.get("tarjetasColaboradorRepository", ITarjetasColaboradorRepository.class);
        TarjetaColaborador tarjetaColaborador = TarjetaColaborador.of(c, "3", LocalDate.now());
        ///tarjetaColaborador.setId(3L);
        tarjetasColaboradorRepository.guardar(tarjetaColaborador);
        ISolicitudesAperturaHeladeraRepository solicitudesAperturaHeladeraRepository = ServiceLocator.get("solicitudesAperturaHeladeraRepository", ISolicitudesAperturaHeladeraRepository.class);
        SolicitudAperturaHeladera solicitudAperturaHeladera = new SolicitudAperturaHeladera(c, "un motivo", LocalDateTime.now(), heladera);
        solicitudesAperturaHeladeraRepository.guardar(solicitudAperturaHeladera);
        try {
            suscribirseAAperturasHeladeras();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}


