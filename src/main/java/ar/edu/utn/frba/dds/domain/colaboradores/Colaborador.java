package ar.edu.utn.frba.dds.domain.colaboradores;

import ar.edu.utn.frba.dds.domain.*;
import ar.edu.utn.frba.dds.domain.colaboraciones.CategoriaOferta;
import ar.edu.utn.frba.dds.domain.colaboraciones.FrecuenciaDonacion;
import ar.edu.utn.frba.dds.domain.colaboraciones.Producto;
import ar.edu.utn.frba.dds.domain.colaboradores.form.RespuestaFormulario;
import ar.edu.utn.frba.dds.domain.heladeras.Heladera;
import ar.edu.utn.frba.dds.domain.heladeras.Vianda;
import ar.edu.utn.frba.dds.domain.tarjetas.Tarjeta;

import java.util.*;

/**
 * 
 */
public class Colaborador {

    /**
     * Default constructor
     */
    public Colaborador() {
    }

    /**
     * 
     */
    private Usuario usuario;

    /**
     * 
     */
    private String clave;

    /**
     * 
     */
    private TipoColaborador tipoColaborador;

    /**
     * 
     */
    public Date fechaCaducidad;

    /**
     * 
     */
    private RespuestaFormulario respuestas;

    /**
     * 
     */
    private Float puntosGanados;

    /**
     * @param heladera 
     * @param vianda
     */
    public void contribuirVianda(Heladera heladera, Vianda vianda) {
        // TODO implement here
    }

    /**
     * @param heladera
     */
    public void contribuirHeladera(Heladera heladera) {
        // TODO implement here
    }

    /**
     * @param origen 
     * @param destino 
     * @param motivo 
     * @param cantidad
     */
    public void distribucionVianda(Heladera origen, Heladera destino, String motivo, Integer cantidad) {
        // TODO implement here
    }

    /**
     * @param monto 
     * @param frecuencia
     */
    public void donarDinero(Float monto, FrecuenciaDonacion frecuencia) {
        // TODO implement here
    }

    /**
     * @param persona 
     * @param tarjeta
     */
    public void darDeAltaPersonaVulnerable(PersonaVulnerable persona, Tarjeta tarjeta) {
        // TODO implement here
    }

    /**
     * @param prod 
     * @param puntosNecesarios 
     * @param rubro
     */
    public void crearOfertaProducto(Producto prod, Integer puntosNecesarios, CategoriaOferta rubro) {
        // TODO implement here
    }

}