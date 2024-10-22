package ar.edu.utn.frba.dds.models.domain.heladeras;

import ar.edu.utn.frba.dds.brokers.BrokerPublisher;
import ar.edu.utn.frba.dds.helpers.ConfigReader;
import ar.edu.utn.frba.dds.helpers.DateHelper;
import ar.edu.utn.frba.dds.models.db.EntidadPersistente;
import ar.edu.utn.frba.dds.models.domain.colaboraciones.RedistribucionViandas;
import ar.edu.utn.frba.dds.models.domain.colaboradores.Colaborador;
import ar.edu.utn.frba.dds.models.domain.colaboradores.autenticacion.Usuario;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;

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

  @ManyToOne
  @JoinColumn(name = "redistribucion_id", referencedColumnName = "id")
  private RedistribucionViandas redistribucionViandas;

  @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.MERGE})
  @JoinColumn(name = "ingreso_vianda_id", referencedColumnName = "id")
  private IngresoVianda viandas;

  public static void main(String[] args) {
    Colaborador colab = new Colaborador();
    colab.setUsuario(new Usuario("jorge@mail", "contrasenia"));
    Heladera heladera = new Heladera(LocalDate.now());
    heladera.setNombre("heladera_de_thomi");
    SolicitudAperturaHeladera soli = new SolicitudAperturaHeladera(colab, "un motivo", LocalDateTime.now(), heladera, null, null);
    try {
      soli.publicarSolicitudABroker();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

//  public void agregarViandas(Vianda... viandas) {
//    this.viandas.agregarViandas(viandas);
//  }

  public boolean esDonacionDeViandas() {
    return this.viandas != null && !this.viandas.getViandas().isEmpty();
  }

  public void publicarSolicitudABroker() throws IOException {
    ConfigReader configReader = new ConfigReader("config.properties");
    String topic = configReader.getProperty("APERTURA_APERTURA_HELADERA_BROKER_TOPIC") + "/" + this.heladera.getNombre(); // cada heladera se va a suscribir a su topic
    String broker = configReader.getProperty("APERTURA_APERTURA_HELADERA_BROKER");
    String clientId = configReader.getProperty("CLIENT_ID");

    String timestampEnMilis = Long.toString(DateHelper.getTimestamp(this.timestamp));
    String content = String.join(";", this.colaborador.getId(), timestampEnMilis, this.id);

    BrokerPublisher brokerPublisher = new BrokerPublisher(topic, broker, clientId);
    brokerPublisher.publicar(content);
  }
}