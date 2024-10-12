package ar.edu.utn.frba.dds.dtos.formularios;

import io.javalin.http.Context;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class RespuestaFormularioDto {
    private String idColaborador;
    private String idFormulario;
    private List<RespuestaACampoDto> respuestas;

    public static RespuestaFormularioDto fromContext(Context ctx) {
        RespuestaFormularioDto dto = new RespuestaFormularioDto();
        dto.setIdColaborador(ctx.pathParam("idColaborador"));
        dto.setIdFormulario(ctx.pathParam("idFormulario"));
        dto.setRespuestas(RespuestaACampoDto.listFromContext(ctx));
        return dto;
    }
}
