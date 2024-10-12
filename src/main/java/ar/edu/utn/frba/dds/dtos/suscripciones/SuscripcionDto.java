package ar.edu.utn.frba.dds.dtos.suscripciones;

import io.javalin.http.Context;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SuscripcionDto {
    private String idColaborador;
    private String tipoSuscripcion;
    private String numero;
    private String canalContacto;

    public static SuscripcionDto fromContext(Context ctx) {
        return new SuscripcionDto(
                ctx.sessionAttribute("idColaborador"),
                ctx.formParam("tipo-suscripcion"),
                ctx.formParam("numero"),
                ctx.formParam("canal-contacto")
        );
    }
}
