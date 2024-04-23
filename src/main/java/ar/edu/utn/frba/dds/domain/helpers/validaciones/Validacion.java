package ar.edu.utn.frba.dds.domain.helpers.validaciones;

import ar.edu.utn.frba.dds.domain.helpers.MotivoNoValido;

public interface Validacion {
    public boolean validar(String clave);
    public MotivoNoValido getMotivo();
}
