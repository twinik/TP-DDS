package ar.edu.utn.frba.dds.domain.tarjetas;

import ar.edu.utn.frba.dds.domain.PersonaVulnerable;

import java.util.*;

/**
 * 
 */
public class Tarjeta {

    /**
     * Default constructor
     */
    public Tarjeta() {
    }

    /**
     * 
     */
    private String codigo;

    /**
     * 
     */
    private Integer nroUsos;

    /**
     * 
     */
    private FrecuenciaUso frecuenciaPermitida;

    /**
     * 
     */
    private List<UsoTarjeta> usos;

    /**
     * 
     */
    private PersonaVulnerable duenio;

}