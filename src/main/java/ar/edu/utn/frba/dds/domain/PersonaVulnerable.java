package ar.edu.utn.frba.dds.domain;

import ar.edu.utn.frba.dds.domain.colaboradores.Colaborador;
import ar.edu.utn.frba.dds.domain.utils.TipoDocumento;

import java.util.*;

/**
 * 
 */
public class PersonaVulnerable {

    /**
     * Default constructor
     */
    public PersonaVulnerable() {
    }

    /**
     * 
     */
    private String nombre;

    /**
     * 
     */
    private Date fechaNacimiento;

    /**
     * 
     */
    private Date fechaRegistro;

    /**
     * 
     */
    private boolean poseeDomicilio;

    /**
     * 
     */
    private String domicilio;

    /**
     * 
     */
    private TipoDocumento tipoDocumento;

    /**
     * 
     */
    private String nroDocumento;

    /**
     * 
     */
    private Colaborador colaborador;

    /**
     * 
     */
    private List <PersonaVulnerable> tutorados;

    /**
     * @return
     */
    public boolean poseeMenores() {
        // TODO implement here
        return false;
    }

    /**
     * @return
     */
    public Integer cantidadMenores() {
        // TODO implement here
        return null;
    }

    /**
     * @return
     */
    public boolean esMenor() {
        // TODO implement here
        return false;
    }

}