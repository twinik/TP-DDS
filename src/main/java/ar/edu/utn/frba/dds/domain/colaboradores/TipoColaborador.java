package ar.edu.utn.frba.dds.domain.colaboradores;

import lombok.AllArgsConstructor;
import lombok.Getter;
import java.util.List;

/**
 *
 */
@Getter
@AllArgsConstructor
public class TipoColaborador {
    private TipoPersona tipo;
    private List<FormaColaboracion> formasPosiblesColaboracion;
}