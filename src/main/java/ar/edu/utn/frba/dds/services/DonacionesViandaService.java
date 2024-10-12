package ar.edu.utn.frba.dds.services;

import ar.edu.utn.frba.dds.models.domain.colaboraciones.DonacionVianda;
import ar.edu.utn.frba.dds.models.domain.colaboraciones.calculadores.ICalculadorPuntos;
import ar.edu.utn.frba.dds.models.domain.heladeras.IngresoVianda;
import ar.edu.utn.frba.dds.models.repositories.IDonacionesViandaRepository;
import lombok.AllArgsConstructor;
import java.util.List;

@AllArgsConstructor
public class DonacionesViandaService {
    private IDonacionesViandaRepository donacionesViandaRepository;
    private ICalculadorPuntos calculadorPuntos;

    public void crearDonaciones(IngresoVianda ingresoVianda) {


        List<DonacionVianda> donaciones = ingresoVianda.donar();
        donacionesViandaRepository.guardar(donaciones);

        donaciones.forEach(d -> calculadorPuntos.sumarPuntosPara(d.getColaborador(), d));

    }
}
