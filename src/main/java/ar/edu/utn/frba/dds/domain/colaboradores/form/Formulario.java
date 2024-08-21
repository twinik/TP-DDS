package ar.edu.utn.frba.dds.domain.colaboradores.form;

import java.util.*;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

/**
 * Formulario class permite representar un formulario.
 */
@Entity
@Getter
@Setter
public class Formulario {

  @Id
  @GeneratedValue
  private Long id;

  @OneToMany
  @JoinColumn(name = "formulario_id", referencedColumnName = "id")
  private List<Campo> campos;


  public Formulario() {
    this.campos = new ArrayList<Campo>();
  }


  public void agregarCampos(Campo... campos) {
    Collections.addAll(this.campos, campos);
  }

}