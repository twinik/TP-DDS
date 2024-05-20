package ar.edu.utn.frba.dds.domain.colaboradores;

import ar.edu.utn.frba.dds.domain.colaboraciones.ColocacionHeladeras;
import ar.edu.utn.frba.dds.domain.colaboraciones.OfertaProducto;
import ar.edu.utn.frba.dds.domain.colaboradores.form.Campo;
import ar.edu.utn.frba.dds.domain.colaboradores.form.RespuestaACampo;
import ar.edu.utn.frba.dds.domain.colaboradores.form.RespuestaFormulario;
import java.util.Date;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Colaborador {

  private Usuario usuario;

  private String clave;

  private TipoColaborador tipoColaborador;

  public Date fechaCaducidad;

  private RespuestaFormulario respuestas;

  private Float puntosGanados;

  private List<ColocacionHeladeras> heladerasColocadas;

  public void sumarPuntos(Float puntos) {
    this.puntosGanados += puntos;
  }

  public void restarPuntos(Float puntos) {
    this.puntosGanados -= puntos;
  }

  public void agregarColocacionHeladera(ColocacionHeladeras colocacion) {
    this.heladerasColocadas.add(colocacion);
  }

  public void completarCampo(RespuestaACampo respuesta) {
    this.respuestas.agregarRespuestasACampo(respuesta);
  }

}