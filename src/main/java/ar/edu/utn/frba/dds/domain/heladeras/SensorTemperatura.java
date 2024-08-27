package ar.edu.utn.frba.dds.domain.heladeras;

import ar.edu.utn.frba.dds.db.EntidadPersistente;
import com.itextpdf.text.pdf.PRIndirectReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * SensorTemperatura class permite representar un sensor de temperatura.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "sensor_temperatura")
public class SensorTemperatura extends EntidadPersistente {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @ManyToOne
  @JoinColumn(name = "heladera_id",referencedColumnName = "id")
  private Heladera heladera;

  public void registrarTemperatura(Float temp) {
    heladera.registrarTemperatura(temp);
  }

}