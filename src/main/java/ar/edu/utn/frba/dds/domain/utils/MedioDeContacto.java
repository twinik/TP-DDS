package ar.edu.utn.frba.dds.domain.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * medioDeContacto class permite representar un medio de contacto.
 */
@AllArgsConstructor
@Getter
public class MedioDeContacto {
  private Long id;
  private CanalContacto canal;
  private String contacto;

  public MedioDeContacto(CanalContacto canal, String contacto) {
    this.canal = canal;
    this.contacto = contacto;
  }
}