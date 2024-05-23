package ar.edu.utn.frba.dds.domain.tecnicos;

import ar.edu.utn.frba.dds.domain.utils.Direccion;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * AreaDeCobertura class permite representar el area de cobertura de un tecnico.
 */
@Getter
@AllArgsConstructor
public class AreaDeCobertura {

  private Direccion referencia;

  private Float radioDeCoberturaEnKM;

}