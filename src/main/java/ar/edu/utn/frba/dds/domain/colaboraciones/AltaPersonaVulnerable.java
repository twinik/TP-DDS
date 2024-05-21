package ar.edu.utn.frba.dds.domain.colaboraciones;

import ar.edu.utn.frba.dds.domain.PersonaVulnerable;
import ar.edu.utn.frba.dds.domain.tarjetas.Tarjeta;
import lombok.AllArgsConstructor;
import lombok.Getter;
import java.util.Date;

@Getter
@AllArgsConstructor
public class AltaPersonaVulnerable extends Colaboracion {
  private PersonaVulnerable persona;
  private Tarjeta tarjeta;
  private Date fechaAlta;

  @Override
  public void efectuar() {
    super.efectuar();
  }

}
