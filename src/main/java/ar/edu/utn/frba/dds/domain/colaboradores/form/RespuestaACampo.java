package ar.edu.utn.frba.dds.domain.colaboradores.form;

import java.util.*;
import lombok.Getter;
import lombok.Setter;

/**
 * RespuestaACampo class permite representar una respuesta a un campo de un formulario.
 */
@Getter
@Setter
public class RespuestaACampo {

  private Campo campo;

  private String respuesta;

  private List<Opcion> opcionesElegidas;

  /**
   * RespuestaACampo class constructor.
   */
  public RespuestaACampo(Campo campo, String respuesta) {
    this.campo = campo;
    this.respuesta = respuesta;
    this.opcionesElegidas = new ArrayList<Opcion>();
  }

  public void agregarOpcionesElegidas(Opcion... opciones) {
    Collections.addAll(this.opcionesElegidas, opciones);
  }

}