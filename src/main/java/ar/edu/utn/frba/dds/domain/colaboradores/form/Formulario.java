package ar.edu.utn.frba.dds.domain.colaboradores.form;

import java.util.*;
import lombok.Getter;
import lombok.Setter;

/**
 * Formulario class permite representar un formulario.
 */
@Getter
@Setter
public class Formulario {

  private List<Campo> campos;
  private Long id;

  public Formulario() {
    this.campos = new ArrayList<Campo>();
  }


  public void agregarCampos(Campo... campos) {
    Collections.addAll(this.campos, campos);
  }

}