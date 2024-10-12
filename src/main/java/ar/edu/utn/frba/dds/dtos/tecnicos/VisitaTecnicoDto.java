package ar.edu.utn.frba.dds.dtos.tecnicos;
/*
        VisitaTecnico visita = new VisitaTecnico(dto.getTecnico(), dto.getFechaVisita(), dto.getDescripcion(), dto.getUrlFoto(), dto.isSolucionado(), dto.getIncidente());

 */

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class VisitaTecnicoDto {

    private String idTecnico;
    private String fechaVisita;
    private String descripcion;
    private String urlFoto;
    private boolean solucionado;
    private String incidente;
}
