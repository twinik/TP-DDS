package ar.edu.utn.frba.dds.domain.colaboradores.form;

import java.util.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Campo class permite representar un campo de un formulario.
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "campo")
public class Campo {

  @Id
  @GeneratedValue
  private Long id;

  @Enumerated(EnumType.STRING)
  private TipoCampo tipo;

  @Column(name = "pregunta",columnDefinition = "TEXT")
  private String pregunta;

  @Column(name = "obligatorio")
  private boolean obligatorio;

  @OneToMany
  @JoinColumn(name = "campo_id",referencedColumnName = "id")
  private List<Opcion> opciones;

  /**
   * Campo class constructor.
   */
  public Campo(TipoCampo tipo, String pregunta, boolean obligatorio) {
    this.tipo = tipo;
    this.pregunta = pregunta;
    this.obligatorio = obligatorio;
    this.opciones = new ArrayList<Opcion>();
  }


  public void agregarOpciones(Opcion... opciones) {
    Collections.addAll(this.opciones, opciones);
  }

}