package ar.edu.utn.frba.dds.domain.colaboradores.form;

import java.util.*;
import lombok.Getter;
import lombok.Setter;

/**
 * RespuestaFormulario class permite representar una respuesta a un formulario.
 */
@Getter
@Setter
public class RespuestaFormulario {

  private Formulario formulario;

  private List<RespuestaACampo> respuestas;


  public RespuestaFormulario(Formulario formulario) {
    this.formulario = formulario;
    this.respuestas = new ArrayList<RespuestaACampo>();
  }


  public void agregarRespuestasACampo(RespuestaACampo... respuestas) {
    Collections.addAll(this.respuestas, respuestas);
  }

}