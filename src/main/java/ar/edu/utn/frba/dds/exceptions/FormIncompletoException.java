package ar.edu.utn.frba.dds.exceptions;

import ar.edu.utn.frba.dds.dtos.personas.PersonaHumanaDto;
import ar.edu.utn.frba.dds.dtos.personas.PersonaJuridicaDto;
import lombok.Getter;

@Getter
public class FormIncompletoException extends RuntimeException {
    Object formDto;

    public FormIncompletoException(String message) {
        super(message);
    }

    public FormIncompletoException(String message, Object formDto) {
        super(message);
        this.formDto = formDto;
    }
}
