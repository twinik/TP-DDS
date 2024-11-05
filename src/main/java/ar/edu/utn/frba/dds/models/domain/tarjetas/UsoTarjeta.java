package ar.edu.utn.frba.dds.models.domain.tarjetas;

import ar.edu.utn.frba.dds.models.db.EntidadPersistente;
import ar.edu.utn.frba.dds.models.domain.heladeras.Heladera;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;

/**
 * UsoTarjeta class permite representar el uso de una tarjeta.
 */
@Entity
@Table(name = "uso_tarjeta")
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class UsoTarjeta extends EntidadPersistente {

  @Column(name = "fecha_uso", columnDefinition = "TIMESTAMP")
  private LocalDateTime fechaUso;

  @ManyToOne
  @JoinColumn(name = "heladera_id", referencedColumnName = "id")
  private Heladera heladera;

  private boolean autorizado = true;

  private UsoTarjeta(LocalDateTime fechaUso, Heladera heladera) {
    this.fechaUso = fechaUso;
    this.heladera = heladera;
  }

  public static UsoTarjeta of(LocalDateTime fechaUso, Heladera heladera) {
    return new UsoTarjeta(fechaUso, heladera);
  }

  public void marcarNoAutorizado() {
    this.autorizado = false;
  }


}