package ar.edu.utn.frba.dds.dtos.incidentes;

import ar.edu.utn.frba.dds.models.domain.incidentes.FallaTecnica;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class FallaTecnicaListadoDto {
    private String id;
    private String heladeraNombre;
    private String descripcion;
    private Boolean solucionado;


    public static FallaTecnicaListadoDto fromFalla(FallaTecnica f) {
        return FallaTecnicaListadoDto.builder()
                .id(f.getId())
                .heladeraNombre(f.getHeladera().getNombre())
                .descripcion(f.getDescripcion())
                .solucionado(f.isSolucionado())
                .build();
    }
}
