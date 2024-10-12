package ar.edu.utn.frba.dds.controllers;

import ar.edu.utn.frba.dds.dtos.colaboraciones.ViandaDto;
import ar.edu.utn.frba.dds.services.ViandasService;
import ar.edu.utn.frba.dds.utils.ICrudViewsHandler;
import io.javalin.http.Context;
import lombok.AllArgsConstructor;
import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
public class ViandasController implements ICrudViewsHandler {

    private ViandasService viandasService;

    @Override
    public void index(Context context) {

    }

    @Override
    public void show(Context context) {

    }

    @Override
    public void create(Context context) {
        Map<String, Object> model = new HashMap<>();
        model.put("message", context.queryParam("message"));
        context.render("/app/colaboraciones/donacion-vianda.hbs", model);
    }

    @Override
    public void save(Context context) {
        ViandaDto dto = ViandaDto.of(context);
        this.viandasService.crearVianda(dto);
        Map<String, Object> model = new HashMap<>();
        model.put("message", "Su solcitud de donacioón ha sido registrada con éxito, esperamos su donación con ansias");
        context.render("/app/success.hbs", model);
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
