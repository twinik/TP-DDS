package ar.edu.utn.frba.dds.dtos.tecnicos;

import ar.edu.utn.frba.dds.dtos.personas.MedioContactoDto;
import io.javalin.http.Context;
import lombok.Builder;
import lombok.Getter;
import java.util.List;

@Builder
@Getter
public class TecnicoDto {
  private String nombre;
  private String apellido;
  private String tipoDocumento;
  private String nroDocumento;
  List<MedioContactoDto> medioContactoDtoList;

  private AreaCoberturaDto areaCoberturaDto;

  public static TecnicoDto of(Context context){
    return TecnicoDto.builder().nombre(context.formParam("nombre"))
        .apellido(context.formParam("apellido"))
        .tipoDocumento(context.formParam("tipoDocumento"))
        .nroDocumento(context.formParam("documento"))
        .areaCoberturaDto(AreaCoberturaDto.builder().latitud(Float.valueOf(context.formParam("latitud")))
            .longitud(Float.valueOf(context.formParam("longitud"))).radio(Float.valueOf(context.formParam("radio"))).build())
        .medioContactoDtoList(MedioContactoDto.of(context)).build();
  }

  public boolean estanCamposLlenos(){
    return this.nombre!=null && this.apellido!= null && this.areaCoberturaDto!= null && this.nroDocumento!= null
        && !this.medioContactoDtoList.isEmpty();
  }

}
