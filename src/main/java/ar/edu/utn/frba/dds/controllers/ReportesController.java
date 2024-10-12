package ar.edu.utn.frba.dds.controllers;

import ar.edu.utn.frba.dds.services.ReportesService;
import ar.edu.utn.frba.dds.utils.ICrudViewsHandler;
import io.javalin.http.Context;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ReportesController implements ICrudViewsHandler {

    private ReportesService service;

    @Override
    public void index(Context context) {
        context.render("/app/reportes/reportes.hbs");
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
