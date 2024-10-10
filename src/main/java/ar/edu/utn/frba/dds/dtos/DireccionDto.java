package ar.edu.utn.frba.dds.dtos;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class DireccionDto {
    private String calle;
    private Integer numero;
    private Integer piso;
    private String codigoPostal;

    public boolean estanCamposLlenos(){
        return this.calle != null && this.numero != null && this.codigoPostal!= null;
    }
}
