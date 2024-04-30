package ar.edu.utn.frba.dds.domain.colaboradores.form;

import java.util.*;

/**
 * 
 */
public class Campo {

    /**
     * Default constructor
     */
    public Campo() {
    }

    /**
     * 
     */
    private TipoCampo tipo;

    /**
     * 
     */
    private String pregunta;

    /**
     * 
     */
    private boolean obligatorio;

    /**
     * 
     */
    private List<Opcion> opciones;

}