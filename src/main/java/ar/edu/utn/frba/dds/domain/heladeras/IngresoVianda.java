package ar.edu.utn.frba.dds.domain.heladeras;

import ar.edu.utn.frba.dds.domain.colaboraciones.DonacionVianda;
import ar.edu.utn.frba.dds.domain.colaboradores.Colaborador;
import lombok.AllArgsConstructor;
import lombok.Getter;
import java.util.Date;
import java.util.List;

/**
 * IngresoVianda class permite representar un ingreso de vianda.
 */
@Getter
@AllArgsConstructor
public class IngresoVianda {
    private Long id;
    private Date fechaDonacion;
    private Colaborador colaborador;
    private boolean entregada;
    private List<Vianda> viandas;
    private Heladera heladera;

    public List<DonacionVianda> donar() {
        return this.viandas.stream().map(vianda -> new DonacionVianda(this.colaborador, vianda.getFechaDonacion(), vianda)).toList();
    }

}