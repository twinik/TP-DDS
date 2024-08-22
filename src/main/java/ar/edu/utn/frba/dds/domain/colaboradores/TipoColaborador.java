package ar.edu.utn.frba.dds.domain.colaboradores;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 * TipoColaborador class permite representar un tipo de colaborador.
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tipo_colaborador")
public class TipoColaborador {
    @Id
    @GeneratedValue
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipoPersona")
    private TipoPersona tipo;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<FormaColaboracion> formasPosiblesColaboracion;
}