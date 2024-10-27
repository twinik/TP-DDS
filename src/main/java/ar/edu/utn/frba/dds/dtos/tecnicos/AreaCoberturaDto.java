package ar.edu.utn.frba.dds.dtos.tecnicos;

import ar.edu.utn.frba.dds.exceptions.DniDuplicadoException;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class AreaCoberturaDto {
    private Float latitud;
    private Float longitud;
    private Float radio;

    public boolean estanCamposLlenos(TecnicoDto dto) {
        if (this.latitud == null || this.longitud == null || this.radio == null) {
            throw new DniDuplicadoException("Falta el punto de referencia en el área de cobertura", dto);
            //todo nose porque no me deja hacer el throw de AreaDeCoberturaVaciaException
        }
        return true;
    }
}
