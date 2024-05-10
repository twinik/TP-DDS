package ar.edu.utn.frba.dds.domain.colaboradores;

import ar.edu.utn.frba.dds.domain.colaboraciones.OfertaProducto;
import ar.edu.utn.frba.dds.domain.colaboradores.form.RespuestaFormulario;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Colaborador {

  private Usuario usuario;

  private String clave;

  private TipoColaborador tipoColaborador;

  public Date fechaCaducidad;

  private RespuestaFormulario respuestas;

  private Float puntosGanados;

  public void sumarPuntos(Float puntos) {
    this.puntosGanados += puntos;
  }


}