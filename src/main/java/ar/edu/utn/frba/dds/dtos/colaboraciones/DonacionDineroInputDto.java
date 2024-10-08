package ar.edu.utn.frba.dds.dtos.colaboraciones;

import io.javalin.http.Context;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class DonacionDineroInputDto {
    private String fecha;
    private Float monto;
    private Integer FrecuenciaDonacion;
    private String idColaborador;

    public static DonacionDineroInputDto of(Context context) {
        if (context.formParam("frec") != null) {
            return new DonacionDineroInputDto(context.formParam("fecha"), Float.valueOf(context.formParam("monto")),
                    Integer.valueOf(context.formParam("frec")), context.sessionAttribute("idColaborador"));

        }
        return new DonacionDineroInputDto(context.formParam("fecha"), Float.valueOf(context.formParam("monto")),
                null, context.sessionAttribute("idColaborador"));
    }

    public boolean estanCamposLlenos() {
        return this.fecha != null && this.monto != null;
    }

}
