package ar.edu.utn.frba.dds.domain.heladeras;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Clase: RegistroTemperatura: guarda la hora y la temperatura registrada en ese instante
 */
@Getter
@AllArgsConstructor
@Entity
@Table(name = "registro_temperatura")
@NoArgsConstructor
public class RegistroTemperatura {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column(name = "fecha_hora",columnDefinition = "TIMESTAMP", nullable = false)
  LocalDateTime fechaHora;
  @Column(name = "temp_registrada", nullable = false)
  float temperaturaRegistrada;

  public RegistroTemperatura(LocalDateTime fechaHora, float temperaturaRegistrada) {
    this.fechaHora = fechaHora;
    this.temperaturaRegistrada = temperaturaRegistrada;
  }
}
