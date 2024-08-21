package ar.edu.utn.frba.dds.domain.colaboradores.form;

import java.util.*;
import lombok.Getter;
import lombok.Setter;

/**
 * Campo class permite representar un campo de un formulario.
 */
@Getter
@Setter
public class Campo {

  private Long id;
  private TipoCampo tipo;

  private String pregunta;

  private boolean obligatorio;

  private List<Opcion> opciones;

  /**
   * Campo class constructor.
   */
  public Campo(TipoCampo tipo, String pregunta, boolean obligatorio) {
    this.tipo = tipo;
    this.pregunta = pregunta;
    this.obligatorio = obligatorio;
    this.opciones = new ArrayList<Opcion>();
  }


  public void agregarOpciones(Opcion... opciones) {
    Collections.addAll(this.opciones, opciones);
  }

}