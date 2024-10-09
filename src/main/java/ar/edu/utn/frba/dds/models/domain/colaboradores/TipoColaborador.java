package ar.edu.utn.frba.dds.models.domain.colaboradores;

import ar.edu.utn.frba.dds.models.db.EntidadPersistente;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.List;

/**
 * TipoColaborador class permite representar un tipo de colaborador.
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tipo_colaborador")
public class TipoColaborador extends EntidadPersistente {
    @Enumerated(EnumType.STRING)
    @Column(name = "tipoPersona")
    private TipoPersona tipo;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "tipo_colaborador_x_forma_colaboracion", inverseJoinColumns = @JoinColumn(name = "forma_colaboracion_id", referencedColumnName = "id"),
            joinColumns = @JoinColumn(name = "tipo_colaborador_id", referencedColumnName = "id"))
    private List<FormaColaboracion> formasPosiblesColaboracion;

    public boolean tenesFormaColaboracion(String nombreInterno){
        return this.formasPosiblesColaboracion.stream().anyMatch(forma -> forma.getNombreInterno().equals(nombreInterno));
    }

}