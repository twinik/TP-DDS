package ar.edu.utn.frba.dds.domain.helpers.validaciones;

import ar.edu.utn.frba.dds.domain.helpers.MotivoNoValido;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class Validacion {

    protected MotivoNoValido motivo;
    public abstract boolean validar(String clave);


}
