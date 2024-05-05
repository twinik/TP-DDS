package ar.edu.utn.frba.dds.domain.heladeras;

import ar.edu.utn.frba.dds.domain.colaboradores.Colaborador;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.*;

@Getter
@AllArgsConstructor
public class IngresoVianda {


    private Date fechaDonacion;

    private Colaborador colaborador;

    private boolean entregada;

    private List <Vianda> viandas;

    private Heladera heladera;

    public void donar() {
        // TODO implement here
    }

}