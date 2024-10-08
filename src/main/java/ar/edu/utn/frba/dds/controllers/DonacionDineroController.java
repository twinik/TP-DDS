package ar.edu.utn.frba.dds.controllers;

import ar.edu.utn.frba.dds.dtos.colaboraciones.DonacionDineroInputDto;
import ar.edu.utn.frba.dds.dtos.colaboraciones.FrecuenciaDonacionDineroDto;
import ar.edu.utn.frba.dds.exceptions.FormIncompletoException;
import ar.edu.utn.frba.dds.models.domain.colaboraciones.utils.FrecuenciaDonacion;
import ar.edu.utn.frba.dds.services.DonacionDineroService;
import ar.edu.utn.frba.dds.utils.ICrudViewsHandler;
import io.javalin.http.Context;
import lombok.AllArgsConstructor;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
public class DonacionDineroController implements ICrudViewsHandler {

    private DonacionDineroService donacionDineroService;

    @Override
    public void index(Context context) {

    }

    @Override
    public void show(Context context) {

    }

    @Override
    public void create(Context context) {
        Map<String, Object> model = new HashMap<>();
        model.put("frecuenciasDonacion", Arrays.stream(FrecuenciaDonacion.values()).map(FrecuenciaDonacionDineroDto::of).toList());
        context.render("/app/colaboraciones/donacion-dinero.hbs", model);
    }

    @Override
    public void save(Context context) {
        DonacionDineroInputDto dto = DonacionDineroInputDto.of(context);
        try {
            this.donacionDineroService.crearDonacionDinero(dto);
        } catch (FormIncompletoException e) {
            // TODO: Mostrar pop up error ?
        }
        //TODO: mostrar carteal de creado con exito
        context.redirect("/colaborar");
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
