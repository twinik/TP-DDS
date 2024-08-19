package ar.edu.utn.frba.dds.domain.colaboradores;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * FormaColaboracion enum permite representar las formas de colaboracion que puede tener un colaborador.
 */

@Getter
@AllArgsConstructor
public class FormaColaboracion {
  private long id;
  private String nombre;
}
