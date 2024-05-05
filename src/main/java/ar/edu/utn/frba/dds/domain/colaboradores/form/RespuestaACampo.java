package ar.edu.utn.frba.dds.domain.colaboradores.form;

import lombok.Getter;
import lombok.Setter;

import java.util.*;

@Getter
@Setter
public class RespuestaACampo {
    public RespuestaACampo(Campo campo, String respuesta) {
        this.campo = campo;
        this.respuesta = respuesta;
        this.opcionesElegidas = new ArrayList<Opcion>();
    }

    private Campo campo;

    private String respuesta;

    private List<Opcion> opcionesElegidas;

    public void agregarOpcionesElegidas(Opcion ... opciones) {
        Collections.addAll(this.opcionesElegidas, opciones);
    }

}