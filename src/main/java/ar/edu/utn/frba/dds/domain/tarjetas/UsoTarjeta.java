package ar.edu.utn.frba.dds.domain.tarjetas;

import ar.edu.utn.frba.dds.domain.heladeras.Heladera;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.persistence.*;

/**
 * UsoTarjeta class permite representar el uso de una tarjeta.
 */
@Entity
@Table(name = "uso_tarjeta")
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class UsoTarjeta {

  @Id
  @GeneratedValue
  private Long id;

  @Column(name = "fecha_uso", columnDefinition = "TIMESTAMP") //TODO converter LOCALDATE TIME
  private LocalDateTime fechaUso;

  @OneToOne
  @JoinColumn(name = "heladera_id", referencedColumnName = "id")
  private Heladera heladera;

  public UsoTarjeta(Tarjeta tarjeta, LocalDateTime fechaUso, Heladera heladera) {
    this.fechaUso = fechaUso;
    this.heladera = heladera;
    tarjeta.agregarUsos();
  }

}