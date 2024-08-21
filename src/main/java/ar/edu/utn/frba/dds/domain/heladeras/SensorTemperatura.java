package ar.edu.utn.frba.dds.domain.heladeras;

import com.itextpdf.text.pdf.PRIndirectReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * SensorTemperatura class permite representar un sensor de temperatura.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SensorTemperatura {
  private Long id;
  private Heladera heladera;

  public void registrarTemperatura(Float temp) {
    heladera.registrarTemperatura(temp);
  }

}