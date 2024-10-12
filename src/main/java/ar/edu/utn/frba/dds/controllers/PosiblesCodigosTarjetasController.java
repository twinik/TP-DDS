package ar.edu.utn.frba.dds.controllers;

import ar.edu.utn.frba.dds.dtos.PosibleCodigoTarjetaDto;
import ar.edu.utn.frba.dds.exceptions.FormIncompletoException;
import ar.edu.utn.frba.dds.models.messageFactory.MensajeFormIncompletoFactory;
import ar.edu.utn.frba.dds.services.PosiblesCodigosService;
import ar.edu.utn.frba.dds.utils.ICrudViewsHandler;
import io.javalin.http.Context;
import lombok.AllArgsConstructor;
import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
public class PosiblesCodigosTarjetasController implements ICrudViewsHandler {
    private PosiblesCodigosService posiblesCodigosService;

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
        context.render("/app/admin/alta-tarjetas.hbs", model);
    }

    @Override
    public void save(Context context) {
        PosibleCodigoTarjetaDto dto = PosibleCodigoTarjetaDto.of(context);
        if (!dto.estanCamposLlenos()) throw new FormIncompletoException(MensajeFormIncompletoFactory.generarMensaje());
        //if(this.tarjetasService.existeTarjeta(dto)) throw new TarjetaExistenteException();

        //this.tarjetasService.crearTarjeta(dto);
        this.posiblesCodigosService.crearPosibleCodigo(dto);
        Map<String, Object> model = new HashMap<>();
        model.put("message", "La tarjeta fue registrada con exito");
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
