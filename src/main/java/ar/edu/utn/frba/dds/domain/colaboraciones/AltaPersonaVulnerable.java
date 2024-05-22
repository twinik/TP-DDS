package ar.edu.utn.frba.dds.domain.colaboraciones;

import ar.edu.utn.frba.dds.domain.PersonaVulnerable;
import ar.edu.utn.frba.dds.domain.colaboraciones.calculadores.CalculadorDePuntos;
import ar.edu.utn.frba.dds.domain.colaboraciones.calculadores.CalculadorPuntosAltaPersona;
import ar.edu.utn.frba.dds.domain.colaboradores.Colaborador;
import ar.edu.utn.frba.dds.domain.tarjetas.Tarjeta;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
public class AltaPersonaVulnerable extends Colaboracion {
  private PersonaVulnerable persona;
  private Tarjeta tarjeta;

  public AltaPersonaVulnerable(Colaborador colaborador, PersonaVulnerable persona, Tarjeta tarjeta, LocalDate fecha) {
    super(colaborador, new CalculadorPuntosAltaPersona(), fecha);
    this.persona = persona;
    this.tarjeta = tarjeta;
  }

  public AltaPersonaVulnerable() {
    this.calculadorDePuntos = new CalculadorPuntosAltaPersona();
  }

  @Override
  public void efectuar() {
    super.efectuar();
  }

}
