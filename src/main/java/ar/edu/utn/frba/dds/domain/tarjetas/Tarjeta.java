package ar.edu.utn.frba.dds.domain.tarjetas;

import ar.edu.utn.frba.dds.domain.PersonaVulnerable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.*;

@Getter
@Setter
@AllArgsConstructor
public class Tarjeta {

  private String codigo;

  private Integer nroUsos;

  private FrecuenciaUso frecuenciaPermitida;

  private List<UsoTarjeta> usos;

  private PersonaVulnerable duenio;

  private Date fechaAdjudicacion;

  private Integer cantidadUsosDia; // TODO agregar cron job para reiniciarlos todos los dias a las 00hs

}