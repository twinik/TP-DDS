package ar.edu.utn.frba.dds.domain.colaboradores;

import ar.edu.utn.frba.dds.db.EntidadPersistente;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
public class FormaColaboracion extends EntidadPersistente {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "nombre", unique = true)
  private String nombre;
}
