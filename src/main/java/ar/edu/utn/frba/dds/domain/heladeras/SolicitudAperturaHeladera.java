package ar.edu.utn.frba.dds.domain.heladeras;

import ar.edu.utn.frba.dds.domain.colaboradores.Colaborador;
import lombok.AllArgsConstructor;
import lombok.Getter;
import java.time.LocalDateTime;

/**
 * Registra una solicud de apertura por parte del colabnardor
 */
@Getter
@AllArgsConstructor
public class SolicitudAperturaHeladera {
    private Colaborador colaborador;
    private String motivo;
    private LocalDateTime timestamp;

}