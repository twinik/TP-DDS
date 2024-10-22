package ar.edu.utn.frba.dds.dtos;

import ar.edu.utn.frba.dds.dtos.personas.PersonaHumanaDto;
import ar.edu.utn.frba.dds.exceptions.FormIncompletoException;
import ar.edu.utn.frba.dds.models.messageFactory.MensajeDirIncompletaFactory;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class DireccionDto {
    private String calle;
    private Integer numero;
    private Integer piso;
    private String codigoPostal;

    public boolean estanCamposLlenos(Object formDto) {

        if (this.calle == null || this.numero == null || this.codigoPostal == null) {
            throw new FormIncompletoException(MensajeDirIncompletaFactory.generarMensaje(), formDto);
        }
        return true;
    }
}
