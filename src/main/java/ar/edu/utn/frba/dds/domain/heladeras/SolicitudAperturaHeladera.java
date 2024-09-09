package ar.edu.utn.frba.dds.domain.heladeras;

import ar.edu.utn.frba.dds.brokers.BrokerPublisher;
import ar.edu.utn.frba.dds.db.EntidadPersistente;
import ar.edu.utn.frba.dds.domain.colaboradores.Colaborador;
import ar.edu.utn.frba.dds.domain.colaboradores.Usuario;
import ar.edu.utn.frba.dds.helpers.ConfigReader;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

/**
 * Registra una solicud de apertura por parte del colaborador
 */
@Entity
@Table(name = "solicitud_apertura_heladera")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SolicitudAperturaHeladera extends EntidadPersistente {

    @ManyToOne
    @JoinColumn(name = "colaborador_id", referencedColumnName = "id")
    private Colaborador colaborador;

    @Column(name = "motivo", columnDefinition = "TEXT")
    private String motivo;

    @Column(name = "timestamp", columnDefinition = "DATETIME")
    private LocalDateTime timestamp;

    @ManyToOne
    @JoinColumn(name = "heladera_id", referencedColumnName = "id")
    private Heladera heladera;

    public void publicarSolicitudABroker() throws IOException {
        ConfigReader configReader = new ConfigReader("config.properties");
        String topic        = configReader.getProperty("APERTURA_APERTURA_HELADERA_BROKER_TOPIC") + "/" + this.heladera.getNombre(); // cada heladera se va a suscribir a su topic
        String broker       = configReader.getProperty("APERTURA_APERTURA_HELADERA_BROKER");
        String clientId     = configReader.getProperty("CLIENT_ID");
        ZonedDateTime zdt = ZonedDateTime.of(this.timestamp, ZoneId.systemDefault());
        String timestampEnMilis = Long.toString(zdt.toInstant().toEpochMilli());
        String content      = String.join(";", this.colaborador.getId().toString(), timestampEnMilis);

        BrokerPublisher brokerPublisher = new BrokerPublisher(topic, broker, clientId);
        brokerPublisher.publicar(content);
    }

    public static void main(String[] args) {
        Colaborador colab = new Colaborador();
        colab.setUsuario(new Usuario("jorge@mail","contrasenia"));
        Heladera heladera = new Heladera(LocalDate.now());
        heladera.setNombre("heladera_de_thomi");
        SolicitudAperturaHeladera soli =  new SolicitudAperturaHeladera(colab,"un motivo",LocalDateTime.now(),heladera);
      try {
        soli.publicarSolicitudABroker();
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
    }
}