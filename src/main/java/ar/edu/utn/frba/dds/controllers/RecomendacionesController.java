package ar.edu.utn.frba.dds.controllers;

import ar.edu.utn.frba.dds.dtos.heladeras.HeladeraMapaDto;
import ar.edu.utn.frba.dds.externapi.RecomendacionDonaciones;
import ar.edu.utn.frba.dds.externapi.recomendaciones.Recomendacion;
import ar.edu.utn.frba.dds.utils.ICrudViewsHandler;
import io.javalin.http.Context;
import lombok.AllArgsConstructor;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
public class RecomendacionesController implements ICrudViewsHandler {

    RecomendacionDonaciones adapterDonaciones;

    public void getDonacionesParaMapa(Context context) {
    String provincia = context.queryParam("provincia");
    String localidad = context.queryParam("localidad");
    List<Recomendacion> recomendaciones = adapterDonaciones.listarRecomendaciones(provincia, localidad);
    List<HeladeraMapaDto> resultado = recomendaciones.stream().map(HeladeraMapaDto::fromRecomendacion).collect(Collectors.toList());
    context.json(resultado);
}

    @Override
    public void index(Context context) {

    }

    @Override
    public void show(Context context) {

    }

    @Override
    public void create(Context context) {

    }

    @Override
    public void save(Context context) {

    }

    @Override
    public void edit(Context context) {

    }

    @Override
    public void update(Context context) {

    }

    @Override
    public void delete(Context context) {

    }
}
