package ar.edu.utn.frba.dds.domain.heladeras;

import ar.edu.utn.frba.dds.db.EntidadPersistente;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.persistence.*;

/**
 * Clase: Modelo Heladera, cada heladera viene definida con estos parametros
 */
@Entity
@Table(name = "modelo_heladera")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ModeloHeladera extends EntidadPersistente {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "modelo")
  private String modelo;

  @Column(name = "temperatura_min")
  private float tempMin;

  @Column(name = "temperatura_max")
  private float tempMax;
}
