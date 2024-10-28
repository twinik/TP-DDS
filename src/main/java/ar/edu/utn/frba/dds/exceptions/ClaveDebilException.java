package ar.edu.utn.frba.dds.exceptions;

import lombok.Getter;

@Getter
public class ClaveDebilException extends RuntimeException {
    Object formDto;

    public ClaveDebilException(String message, Object formDto) {
        super(message);
        this.formDto = formDto;
    }

    public ClaveDebilException(String message) {
        super(message);
    }
}
