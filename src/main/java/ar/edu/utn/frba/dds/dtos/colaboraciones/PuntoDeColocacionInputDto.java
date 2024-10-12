package ar.edu.utn.frba.dds.dtos.colaboraciones;

import io.javalin.http.Context;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class PuntoDeColocacionInputDto {
    private Float latitud;
    private Float longitud;
    private Float radio;

    public static PuntoDeColocacionInputDto of(Context context) {
        return PuntoDeColocacionInputDto.builder().latitud(Float.parseFloat(context.queryParam("latitud")))
                .longitud(Float.parseFloat(context.queryParam("longitud")))
                .radio(Float.parseFloat(context.queryParam("radio")))
                .build();
    }
}
