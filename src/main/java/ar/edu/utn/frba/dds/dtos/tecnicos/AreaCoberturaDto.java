package ar.edu.utn.frba.dds.dtos.tecnicos;

import ar.edu.utn.frba.dds.exceptions.AreaDeCoberturaVaciaException;
import ar.edu.utn.frba.dds.models.messageFactory.MensajeAreaCoberturaFactory;
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
            throw new AreaDeCoberturaVaciaException(MensajeAreaCoberturaFactory.generarMensaje(), dto);
        }
        return true;
    }
}
