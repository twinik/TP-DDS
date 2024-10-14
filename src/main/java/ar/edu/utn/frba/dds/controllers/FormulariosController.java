package ar.edu.utn.frba.dds.controllers;

import ar.edu.utn.frba.dds.dtos.formularios.AltaFormularioDto;
import ar.edu.utn.frba.dds.services.FormulariosService;
import ar.edu.utn.frba.dds.utils.ICrudViewsHandler;
import io.javalin.http.Context;
import lombok.AllArgsConstructor;
import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
public class FormulariosController implements ICrudViewsHandler {

    FormulariosService formulariosService;

    @Override
    public void index(Context context) {

    }

    @Override
    public void show(Context context) {

    }

    @Override
    public void create(Context context) {
        context.render("app/admin/alta-formulario.hbs");
    }

    @Override
    public void save(Context context) {
        AltaFormularioDto dto = AltaFormularioDto.fromContext(context);
        formulariosService.crearFormulario(dto);
        Map<String, String> model = new HashMap<>();
        model.put("message", "El formulario ha sido creado con exito!");
        context.status(201);
        context.render("app/success.hbs", model);
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
