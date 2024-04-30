package ar.edu.utn.frba.dds.domain.heladeras;

import ar.edu.utn.frba.dds.domain.utils.Direccion;
import ar.edu.utn.frba.dds.domain.utils.Ubicacion;

import java.util.*;

/**
 * 
 */
public class Heladera {

    /**
     * Default constructor
     */
    public Heladera() {
    }

    /**
     * 
     */
    private Ubicacion ubicacion;

    /**
     * 
     */
    private Direccion direccion;

    /**
     * 
     */
    private String nombre;

    /**
     * 
     */
    private Integer capacidadViandas;

    /**
     * 
     */
    private Date fechaPuestaFuncionamiento;

    /**
     * 
     */
    private List<Vianda> viandas;

    /**
     * 
     */
    private Float tempMin;

    /**
     * 
     */
    private Float tempMax;

    /**
     * 
     */
    private SensorTemperatura sensorTemp;

    /**
     * 
     */
    private SensorMovimiento sensorMov;

    /**
     * @param vianda
     */
    public void agregarVIanda(Vianda vianda) {
        // TODO implement here
    }

    /**
     * 
     */
    public void verificarTemperatura() {
        // TODO implement here
    }

    /**
     * @param mensajeAlerta
     */
    public void alertarEnMapa(String mensajeAlerta) {
        // TODO implement here
    }

}