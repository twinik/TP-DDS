package ar.edu.utn.frba.dds.domain.colaboradores.form;

import java.util.*;

import ar.edu.utn.frba.dds.db.EntidadPersistente;
import ar.edu.utn.frba.dds.domain.colaboradores.Colaborador;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.criteria.CriteriaBuilder;

/**
 * RespuestaFormulario class permite representar una respuesta a un formulario.
 */
@Entity
@Table(name = "respuesta_formulario")
@Getter
@Setter
@NoArgsConstructor
public class RespuestaFormulario extends EntidadPersistente {
  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "formulario_id",referencedColumnName = "id")
  private Formulario formulario;

  @OneToMany(cascade = CascadeType.ALL)
  @JoinColumn(name = "respuesta_formulario_id",referencedColumnName = "id")
  private List<RespuestaACampo> respuestas = new ArrayList<>();

  public RespuestaFormulario(Formulario formulario) {
    this.formulario = formulario;
  }


  public void agregarRespuestasACampo(RespuestaACampo... respuestas) {
    Collections.addAll(this.respuestas, respuestas);
  }

}