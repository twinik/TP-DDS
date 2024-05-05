package ar.edu.utn.frba.dds.domain.colaboradores;

import ar.edu.utn.frba.dds.domain.*;
import ar.edu.utn.frba.dds.domain.colaboraciones.CategoriaOferta;
import ar.edu.utn.frba.dds.domain.colaboraciones.FrecuenciaDonacion;
import ar.edu.utn.frba.dds.domain.colaboraciones.Producto;
import ar.edu.utn.frba.dds.domain.colaboradores.form.RespuestaFormulario;
import ar.edu.utn.frba.dds.domain.heladeras.Heladera;
import ar.edu.utn.frba.dds.domain.heladeras.Vianda;
import ar.edu.utn.frba.dds.domain.tarjetas.Tarjeta;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.*;

@Getter
@Setter
@AllArgsConstructor
public class Colaborador {

    private Usuario usuario;

    private String clave;

    private TipoColaborador tipoColaborador;

    public Date fechaCaducidad;

    private RespuestaFormulario respuestas;

    private Float puntosGanados;

    public void contribuirVianda(Heladera heladera, Vianda vianda) {
        // TODO implement here
    }

    public void contribuirHeladera(Heladera heladera) {
        // TODO implement here
    }

    public void distribucionVianda(Heladera origen, Heladera destino, String motivo, Integer cantidad) {
        // TODO implement here
    }

    public void donarDinero(Float monto, FrecuenciaDonacion frecuencia) {
        // TODO implement here
    }

    public void darDeAltaPersonaVulnerable(PersonaVulnerable persona, Tarjeta tarjeta) {
        // TODO implement here
    }

    public void crearOfertaProducto(Producto prod, Integer puntosNecesarios, CategoriaOferta rubro) {
        // TODO implement here
    }

}