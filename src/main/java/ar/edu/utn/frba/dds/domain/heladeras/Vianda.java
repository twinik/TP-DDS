package ar.edu.utn.frba.dds.domain.heladeras;

import ar.edu.utn.frba.dds.domain.colaboradores.Colaborador;
import ar.edu.utn.frba.dds.domain.heladeras.Heladera;

import java.util.*;

/**
 * 
 */
public class Vianda {

    /**
     * Default constructor
     */
    public Vianda() {
    }

    /**
     * 
     */
    private String comida;

    /**
     * 
     */
    private Date fechaCaducidad;

    /**
     * 
     */
    private Date fechaDonacion;

    /**
     * 
     */
    private Colaborador colaborador;

    /**
     * 
     */
    private Heladera heladera;

    /**
     * 
     */
    private Integer calorias;

    /**
     * 
     */
    private Integer peso;

    /**
     * 
     */
    private boolean entregada;

}