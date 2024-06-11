package ar.edu.utn.frba.dds.domain.incidentes;

import ar.edu.utn.frba.dds.domain.colaboradores.Colaborador;

/**
 * 
 */
public class FallaTecnica extends Incidente {
    private Colaborador colaborador;
    private String descripcion;
    private String urlFoto;

    /**
     * Default constructor
     */
    public FallaTecnica() {
    }

}