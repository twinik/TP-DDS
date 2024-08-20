package ar.edu.utn.frba.dds.domain.tecnicos;
import ar.edu.utn.frba.dds.domain.heladeras.Heladera;
import ar.edu.utn.frba.dds.domain.incidentes.Incidente;
import lombok.Data;
import java.time.LocalDateTime;

/**
 * VisitaTecnico class representa una visita de un tecnico a una heladera
 */
@Data
public class VisitaTecnico {
    private Long id;
    private Tecnico tecnico;
    private LocalDateTime timestamp;
    private String descripcion;
    private String urlFoto;
    private boolean solucionado;
    private Incidente incidente;
}