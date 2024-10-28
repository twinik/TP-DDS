package ar.edu.utn.frba.dds.dtos.colaboraciones;

import ar.edu.utn.frba.dds.dtos.heladeras.HeladeraDto;
import io.javalin.http.Context;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class RedistribucionViandaDto {
    private HeladeraDto origen;
    private HeladeraDto destino;
    private Integer cantViandas;
    private String idMotivo;
    private String fecha;
    private String idColaborador;

    public static RedistribucionViandaDto of(Context context) {
        return RedistribucionViandaDto.builder()
                .origen(HeladeraDto.builder().id(context.formParam("idHeladeraOrigen")).build())
                .destino(HeladeraDto.builder().id(context.formParam("idHeladeraDestino")).build())
                .cantViandas((context.formParam("viandas") != null && !context.formParam("viandas").isBlank()) ? Integer.valueOf(context.formParam("viandas")) : null)
                .idMotivo(context.formParam("motivo"))
                .fecha(context.formParam("fecha"))
                .idColaborador(context.sessionAttribute("idColaborador"))
                .build();
    }

    public boolean estanCamposLlenos() {
        return this.origen != null && this.destino != null && this.cantViandas != null
                && this.idMotivo != null && this.fecha != null;
    }
}
