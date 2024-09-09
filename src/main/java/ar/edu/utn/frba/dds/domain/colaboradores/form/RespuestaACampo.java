package ar.edu.utn.frba.dds.domain.colaboradores.form;

import ar.edu.utn.frba.dds.db.EntidadPersistente;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * RespuestaACampo class permite representar una respuesta a un campo de un formulario.
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "respuesta_campo")
public class RespuestaACampo extends EntidadPersistente {

  @ManyToOne
  @JoinColumn(name = "campo_id", referencedColumnName = "id")
  private Campo campo;

  @Column(columnDefinition = "TEXT")
  private String respuesta;

  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(name = "opciones_x_respuesta", inverseJoinColumns = @JoinColumn(name = "opcion_elegida_id",referencedColumnName = "id"),
  joinColumns = @JoinColumn(name = "respuesta_a_campo_id",referencedColumnName = "id"))
  private List<Opcion> opcionesElegidas = new ArrayList<>();

  /**
   * RespuestaACampo class constructor.
   */
  public RespuestaACampo(Campo campo, String respuesta) {
    this.campo = campo;
    this.respuesta = respuesta;
  }

  public void agregarOpcionesElegidas(Opcion... opciones) {
    Collections.addAll(this.opcionesElegidas, opciones);
  }

}