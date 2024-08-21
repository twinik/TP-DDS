package ar.edu.utn.frba.dds.domain.tecnicos;

import ar.edu.utn.frba.dds.domain.utils.Ubicacion;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * AreaDeCobertura class permite representar el area de cobertura de un tecnico.
 */
@Getter
@AllArgsConstructor
public class AreaDeCobertura {
  private Long id;
  private Ubicacion referencia;
  private Float radioDeCoberturaEnKM;

  public boolean contieneUbicacion(Ubicacion ubicacion) {
    float distancia = referencia.calcularDistanciaHasta(ubicacion);
    return distancia <= radioDeCoberturaEnKM;
  }
}