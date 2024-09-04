package ar.edu.utn.frba.dds.helpers.validaciones;

import ar.edu.utn.frba.dds.helpers.MotivoNoValido;
import lombok.Getter;
import lombok.Setter;

/**
 * Validacion abstract class permite representar una validacion.
 */
@Getter
@Setter
public abstract class Validacion {
    protected MotivoNoValido motivo;

    public abstract boolean validar(String clave);

}
