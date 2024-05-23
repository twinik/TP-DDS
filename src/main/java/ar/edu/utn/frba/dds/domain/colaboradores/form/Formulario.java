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

  public Formulario() {
    this.campos = new ArrayList<Campo>();
  }

  private List<Campo> campos;

  public void agregarCampos(Campo... campos) {
    Collections.addAll(this.campos, campos);
  }

}