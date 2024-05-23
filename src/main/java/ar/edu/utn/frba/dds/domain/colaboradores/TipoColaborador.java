package ar.edu.utn.frba.dds.domain.colaboradores;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * TipoColaborador class permite representar un tipo de colaborador.
 */
@Getter
@AllArgsConstructor
public class TipoColaborador {
    private TipoPersona tipo;
    private List<FormaColaboracion> formasPosiblesColaboracion;
}