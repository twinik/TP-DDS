package ar.edu.utn.frba.dds.exceptions;

import lombok.Getter;

@Getter
public class DniDuplicadoException extends RuntimeException {
    private Object formDto;

    public DniDuplicadoException(String message) {
        super(message);
    }

    public DniDuplicadoException(String message, Object formDto) {
        super(message);
        this.formDto = formDto;
    }
}
