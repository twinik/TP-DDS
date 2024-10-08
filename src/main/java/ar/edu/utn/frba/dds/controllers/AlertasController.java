package ar.edu.utn.frba.dds.controllers;

import ar.edu.utn.frba.dds.dtos.incidentes.AlertaDto;
import ar.edu.utn.frba.dds.services.AlertasService;
import ar.edu.utn.frba.dds.utils.ICrudViewsHandler;
import io.javalin.http.Context;
import lombok.AllArgsConstructor;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
public class AlertasController implements ICrudViewsHandler {

    private AlertasService alertasService;

    @Override
    public void index(Context context) {
        // TODO: agregar paginacion y filtros
        List<AlertaDto> alertas = alertasService.obtenerTodos();

        Map<String, Object> model = new HashMap<>();

        model.put("alertas", alertas);

        context.render("/app/heladeras/listado-alertas.hbs", model);
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
