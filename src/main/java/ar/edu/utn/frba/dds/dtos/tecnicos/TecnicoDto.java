package ar.edu.utn.frba.dds.dtos.tecnicos;

import ar.edu.utn.frba.dds.dtos.personas.MedioContactoDto;
import io.javalin.http.Context;
import lombok.Builder;
import lombok.Getter;
import java.util.List;

@Builder
@Getter
public class TecnicoDto {
    List<MedioContactoDto> medioContactoDtoList;
    private String nombre;
    private String apellido;
    private String tipoDocumento;
    private String nroDocumento;
    private AreaCoberturaDto areaCoberturaDto;

    public static TecnicoDto of(Context context) {
        return TecnicoDto.builder().nombre(context.formParam("nombre"))
                .apellido(context.formParam("apellido"))
                .tipoDocumento(context.formParam("tipoDocumento"))
                .nroDocumento(context.formParam("documento"))
                .areaCoberturaDto(AreaCoberturaDto.builder().latitud((context.formParam("latitud") != null && !context.formParam("latitud").isBlank()) ? Float.valueOf(context.formParam("latitud")) : null)
                        .longitud((context.formParam("longitud") != null && !context.formParam("longitud").isBlank()) ? Float.valueOf(context.formParam("longitud")) : null)
                        .radio(Float.valueOf(context.formParam("radio"))).build())
                .medioContactoDtoList(MedioContactoDto.of(context)).build();
    }

    public boolean estanCamposLlenos() {
        return this.nombre != null && this.apellido != null && this.areaCoberturaDto != null && this.areaCoberturaDto.estanCamposLlenos() && this.nroDocumento != null
                && MedioContactoDto.estanCamposLLenos(this.medioContactoDtoList);
    }

}
