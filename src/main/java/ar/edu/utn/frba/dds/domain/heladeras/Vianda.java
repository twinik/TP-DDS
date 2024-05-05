package ar.edu.utn.frba.dds.domain.heladeras;

import ar.edu.utn.frba.dds.domain.colaboradores.Colaborador;
import ar.edu.utn.frba.dds.domain.heladeras.Heladera;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.*;

@Getter
@Setter
@AllArgsConstructor
public class Vianda {

    private String comida;

    private Date fechaCaducidad;

    private Date fechaDonacion;

    private Colaborador colaborador;

    private Heladera heladera;

    private Integer calorias;

    private Integer peso;

    private boolean entregada;

}