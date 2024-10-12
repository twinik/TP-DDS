package ar.edu.utn.frba.dds.dtos.tecnicos;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class AreaCoberturaDto {
    private Float latitud;
    private Float longitud;
    private Float radio;

    public boolean estanCamposLlenos() {
        return this.latitud != null && this.longitud != null && this.radio != null;
    }
}
