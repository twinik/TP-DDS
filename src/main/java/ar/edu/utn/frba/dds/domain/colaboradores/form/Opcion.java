package ar.edu.utn.frba.dds.domain.colaboradores.form;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Opcion class permite representar una opcion de un campo de un formulario.
 */

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Opcion {

  @Id
  @GeneratedValue
  private Long id;

  @Column(name = "opcion", columnDefinition = "TEXT")
  private String opcion;
}