package ar.edu.utn.frba.dds.dtos.heladeras;

import io.javalin.http.Context;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;


@AllArgsConstructor
@Getter
@Builder
public class ModeloHeladeraInputDto {
    private String modelo;
    private Float tempMin;
    private Float tempMax;

    public static ModeloHeladeraInputDto of(Context context) {
        return ModeloHeladeraInputDto.builder().modelo(context.formParam("modelo"))
                .tempMin(Float.valueOf(context.formParam("tempMin")))
                .tempMax(Float.valueOf(context.formParam("tempMax")))
                .build();
    }

    public boolean estanCamposLllenos() {
        return this.modelo != null && this.tempMax != null && this.tempMin != null;
    }

}
