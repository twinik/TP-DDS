package ar.edu.utn.frba.dds.dtos;

import ar.edu.utn.frba.dds.exceptions.FormIncompletoException;
import ar.edu.utn.frba.dds.models.messageFactory.MensajeDirIncompletaFactory;
import ar.edu.utn.frba.dds.models.messageFactory.MensajeFormIncompletoFactory;
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

        if(this.calle == null || this.numero == null || this.codigoPostal == null){
            throw new FormIncompletoException(MensajeDirIncompletaFactory.generarMensaje());
        }
        return true;
    }
}
