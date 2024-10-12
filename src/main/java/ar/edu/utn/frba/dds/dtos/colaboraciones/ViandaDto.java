package ar.edu.utn.frba.dds.dtos.colaboraciones;

import ar.edu.utn.frba.dds.dtos.heladeras.HeladeraDto;
import io.javalin.http.Context;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ViandaDto {
    private String desc;
    private String fechaCaducidad;

    private String fechaDonacion;
    private Integer calorias;
    private Float peso;
    private HeladeraDto heladeraDto;
    private String idColaborador;

    public static ViandaDto of(Context context) {
        return ViandaDto.builder().desc(context.formParam("desc"))
                .fechaCaducidad(context.formParam("fechaCaducidad"))
                .fechaDonacion(context.formParam("fechaDonacion"))
                .calorias((context.formParam("calorias") != null && !context.formParam("calorias").isBlank()) ? Integer.valueOf(context.formParam("calorias")) : null)
                .peso((context.formParam("peso") != null && !context.formParam("peso").isBlank()) ? Float.valueOf(context.formParam("peso")) : null)
                .heladeraDto(HeladeraDto.builder().id(context.formParam("idHeladera")).build())
                .idColaborador(context.sessionAttribute("idColaborador"))
                .build();
    }

    public boolean estanCamposLlenos() {
        return this.desc != null && this.fechaCaducidad != null && this.fechaDonacion != null && this.heladeraDto != null && this.idColaborador != null;
    }

}
