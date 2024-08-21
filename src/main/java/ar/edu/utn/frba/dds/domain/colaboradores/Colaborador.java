package ar.edu.utn.frba.dds.domain.colaboradores;

import ar.edu.utn.frba.dds.domain.colaboraciones.ColocacionHeladeras;
import ar.edu.utn.frba.dds.domain.colaboradores.form.RespuestaACampo;
import ar.edu.utn.frba.dds.domain.colaboradores.form.RespuestaFormulario;
import java.util.ArrayList;
import java.util.List;
import ar.edu.utn.frba.dds.domain.utils.MedioDeContacto;
import ar.edu.utn.frba.dds.domain.utils.Direccion;
import ar.edu.utn.frba.dds.domain.utils.TipoDocumento;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Colaborador class permite representar un colaborador.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Colaborador {

  private Long id;

  private Usuario usuario;

  private TipoDocumento tipoDocumento;

  private Integer documento;

  private TipoColaborador tipoColaborador;

  private RespuestaFormulario respuestas;

  private Float puntosGanados = 0f;

  private List<ColocacionHeladeras> heladerasColocadas = new ArrayList<>();

  private Direccion direccion;

  private List<MedioDeContacto> medioContacto;

  private String nombre;

  private String apellido;

  private String rubro;

  private String razonSocial;

  private TipoPersonaJuridica tipoPersonaJuridica;

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

  public String getNombreYapellido(){
    return String.format("%s %s",this.nombre,this.apellido);
  }

}

//el colaborador a lo largo del tiempo podria tener distintas tarjetas
//podria tener una lista de tarjetas con el dia que se dio de alta y de baja