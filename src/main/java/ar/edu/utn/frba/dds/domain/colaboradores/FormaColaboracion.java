package ar.edu.utn.frba.dds.domain.colaboradores;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * FormaColaboracion enum permite representar las formas de colaboracion que puede tener un colaborador.
 */

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "forma_colaboracion")
public class FormaColaboracion {
  @Id
  @GeneratedValue
  private Long id;

  @Column(name = "nombre")
  private String nombre;
}
