package ar.edu.utn.frba.dds.domain.colaboradores.form;

import lombok.Getter;
import lombok.Setter;

import java.util.*;

@Getter
@Setter
public class RespuestaFormulario {

    public RespuestaFormulario(Formulario formulario) {
        this.formulario = formulario;
        this.respuestas = new ArrayList<RespuestaACampo>();
    }

    private Formulario formulario;

    private List<RespuestaACampo> respuestas;

    public void agregarRespuestasACampo(RespuestaACampo ... respuestas) {
        Collections.addAll(this.respuestas, respuestas);
    }

}