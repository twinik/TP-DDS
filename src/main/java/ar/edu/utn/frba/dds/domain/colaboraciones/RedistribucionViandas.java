package ar.edu.utn.frba.dds.domain.colaboraciones;

import ar.edu.utn.frba.dds.domain.colaboradores.Colaborador;
import ar.edu.utn.frba.dds.domain.heladeras.Heladera;

import java.util.*;

/**
 * 
 */
public class RedistribucionViandas {

    /**
     * Default constructor
     */
    public RedistribucionViandas() {
    }

    /**
     * 
     */
    private Colaborador colaborador;

    /**
     * 
     */
    private Heladera heladeraOrigen;

    /**
     * 
     */
    private Heladera heladeraDestino;

    /**
     * 
     */
    private Date fecha;

    /**
     * 
     */
    private Date motivo;

    /**
     * 
     */
    private Integer cantidad;

}