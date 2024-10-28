package ar.edu.utn.frba.dds.dtos.colaboraciones;

import ar.edu.utn.frba.dds.models.domain.colaboraciones.utils.MotivoRedistribucionVianda;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class MotivoRedistribucionDto {
    private String id;
    private String desc;

    public static MotivoRedistribucionDto fromMotivo(MotivoRedistribucionVianda motivo) {
        return MotivoRedistribucionDto.builder().id(motivo.getId())
                .desc(motivo.getMotivo())
                .build();
    }

}
