package ar.edu.utn.frba.dds.dtos;

import io.javalin.http.Context;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PosibleCodigoTarjetaDto {

    String codigo;

    public static PosibleCodigoTarjetaDto of(Context context) {
        return new PosibleCodigoTarjetaDto(context.formParam("codigoTarjeta"));
    }

    public boolean estanCamposLlenos(){
        return this.codigo!=null;
    }
}