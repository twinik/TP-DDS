package ar.edu.utn.frba.dds.dtos.incidentes;

import ar.edu.utn.frba.dds.models.domain.incidentes.FallaTecnica;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import java.time.format.DateTimeFormatter;

@Builder
@Getter
@Setter
public class FallaTecnicaDto {
    private String heladeraNombre;
    private String descripcion;
    private String urlFoto;
    private String nombreColaborador;
    private Boolean solucionado;
    private String fecha;


    public static FallaTecnicaDto fromFalla(FallaTecnica f) {
        return FallaTecnicaDto.builder()
                .heladeraNombre(f.getHeladera().getNombre())
                .descripcion(f.getDescripcion())
                .urlFoto(f.getUrlFoto())
                .solucionado(f.isSolucionado())
                .nombreColaborador(f.getColaborador().getNombre() + " " + f.getColaborador().getApellido())
                .fecha(f.getCreated_at().format(DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm")))
                .build();
    }
}
