package ar.edu.utn.frba.dds.domain.tecnicos;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * medioDeContacto class permite representar un medio de contacto.
 */
@AllArgsConstructor
@Getter
public class MedioDeContacto {
  private CanalContacto canal;
  private String contacto;
}