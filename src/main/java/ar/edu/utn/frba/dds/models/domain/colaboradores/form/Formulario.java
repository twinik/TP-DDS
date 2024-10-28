package ar.edu.utn.frba.dds.models.domain.colaboradores.form;

import ar.edu.utn.frba.dds.models.db.EntidadPersistente;
import ar.edu.utn.frba.dds.models.domain.colaboradores.autenticacion.Usuario;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Formulario class permite representar un formulario.
 */

@Getter
@Setter
@Entity
@Table(name = "formulario")
public class Formulario extends EntidadPersistente {

  @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
  @JoinColumn(name = "formulario_id", referencedColumnName = "id")
  private List<Campo> campos;

  @Column(name = "nombre")
  private String nombre;

  @ManyToOne
  @JoinColumn(name = "usuario_id", referencedColumnName = "id")
  private Usuario autor;


  public Formulario() {
    this.campos = new ArrayList<Campo>();
  }


  public void agregarCampos(Campo... campos) {
    Collections.addAll(this.campos, campos);
  }

  public void agregarCampos(List<Campo> campos) {
    this.campos.addAll(campos);
  }

}