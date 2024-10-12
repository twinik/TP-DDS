package ar.edu.utn.frba.dds.controllers;

import ar.edu.utn.frba.dds.dtos.heladeras.ModeloHeladeraInputDto;
import ar.edu.utn.frba.dds.exceptions.FormIncompletoException;
import ar.edu.utn.frba.dds.models.messageFactory.MensajeFormIncompletoFactory;
import ar.edu.utn.frba.dds.services.ModelosService;
import ar.edu.utn.frba.dds.utils.ICrudViewsHandler;
import io.javalin.http.Context;
import lombok.AllArgsConstructor;
import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
public class ModelosHeladeraController implements ICrudViewsHandler {
    private ModelosService modelosService;


    @Override
    public void index(Context context) {

    }

    @Override
    public void show(Context context) {

    }

    @Override
    public void create(Context context) {
        context.render("/app/admin/alta-modelo-heladera.hbs");
    }

    @Override
    public void save(Context context) {
        ModeloHeladeraInputDto dto = ModeloHeladeraInputDto.of(context);
        if (!dto.estanCamposLllenos()) throw new FormIncompletoException(MensajeFormIncompletoFactory.generarMensaje());
        modelosService.crearModeloHeladera(dto);
        Map<String, Object> model = new HashMap<>();
        model.put("message", "El modelo " + dto.getModelo() + " fue regisrado con exito!");
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
