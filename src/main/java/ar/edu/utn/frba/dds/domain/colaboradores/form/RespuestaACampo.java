package ar.edu.utn.frba.dds.domain.colaboradores.form;

import java.util.*;

import ar.edu.utn.frba.dds.db.EntidadPersistente;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * RespuestaACampo class permite representar una respuesta a un campo de un formulario.
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "respuesta_campo")
public class RespuestaACampo extends EntidadPersistente {

  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "campo_id", referencedColumnName = "id")
  private Campo campo;

  @Column(columnDefinition = "TEXT")
  private String respuesta;

  @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
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