package ar.edu.utn.frba.dds.domain.colaboradores.form;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.*;

@Getter
@Setter
public class Campo {

    public Campo(TipoCampo tipo, String pregunta, boolean obligatorio) {
        this.tipo = tipo;
        this.pregunta = pregunta;
        this.obligatorio = obligatorio;
        this.opciones = new ArrayList<Opcion>();
    }

    private TipoCampo tipo;

    private String pregunta;

    private boolean obligatorio;

    private List<Opcion> opciones;

    public void agregarOpciones(Opcion ... opciones) {
        Collections.addAll(this.opciones, opciones);
    }

}