package ar.edu.utn.frba.dds.models.domain.colaboradores;

import ar.edu.utn.frba.dds.models.db.EntidadPersistente;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * TipoColaborador class permite representar un tipo de colaborador.
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Entity
@Table(name = "tipo_colaborador")
public class TipoColaborador extends EntidadPersistente {
  @Enumerated(EnumType.STRING)
  @Column(name = "tipoPersona")
  private TipoPersona tipo;

  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(name = "tipo_colaborador_x_forma_colaboracion", inverseJoinColumns = @JoinColumn(name = "forma_colaboracion_id", referencedColumnName = "id"),
      joinColumns = @JoinColumn(name = "tipo_colaborador_id", referencedColumnName = "id"))
  private List<FormaColaboracion> formasPosiblesColaboracion = new ArrayList<>();

  public boolean tenesFormaColaboracion(String nombreInterno) {
    return this.formasPosiblesColaboracion.stream().anyMatch(forma -> forma.getNombreInterno().equals(nombreInterno));
  }

  public void agregarFormasColaboracion(List<FormaColaboracion> formas) {
    this.formasPosiblesColaboracion.addAll(formas);
  }

  public void agregarFormasColaboracion(FormaColaboracion forma) {
    this.formasPosiblesColaboracion.add(forma);
  }

}