package ar.edu.utn.frba.dds.controllers;

import ar.edu.utn.frba.dds.dtos.heladeras.HeladeraDto;
import ar.edu.utn.frba.dds.dtos.incidentes.FallaTecnicaAltaDto;
import ar.edu.utn.frba.dds.services.FallasTecnicasService;
import ar.edu.utn.frba.dds.services.FileUploadService;
import ar.edu.utn.frba.dds.services.HeladerasService;
import ar.edu.utn.frba.dds.utils.ICrudViewsHandler;
import io.javalin.http.Context;
import io.javalin.http.UploadedFile;
import lombok.AllArgsConstructor;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
public class FallasTecnicasController implements ICrudViewsHandler {

    private FallasTecnicasService fallasTecnicasService;
    private FileUploadService fileUploadService;
    private HeladerasService heladerasService;

    private boolean esQueryParamFiltroSolucionadoValido(String filtro) {
        return filtro != null &&
                (filtro.equals("Solucionadas") || filtro.equals("No Solucionadas") || filtro.equals("Mostrar todas"));
    }

    @Override
    public void index(Context context) {
        Map<String, Object> model = new HashMap<>();
        model.put("fallas", this.fallasTecnicasService.obtenerTodos(context.queryParam("heladeraId"), context.queryParam("filtroSolucionadas")));
        List<HeladeraDto> heladeraDtoList = this.heladerasService.obtenerHeladeras();
        model.put("heladeras", heladeraDtoList);
        model.put("heladeraSeleccionada", heladeraDtoList.stream().filter(h -> h.getId().equals(context.queryParam("heladeraId"))).findFirst().orElse(null));
        model.put("filtroSolucionadas", this.esQueryParamFiltroSolucionadoValido(context.queryParam("filtroSolucionadas")) ? context.queryParam("filtroSolucionadas") : null);
        context.render("app/heladeras/listado-fallas-tecnicas.hbs", model);
    }

    @Override
    public void show(Context context) {
        Map<String, Object> model = new HashMap<>();
        model.put("falla", this.fallasTecnicasService.obtenerFallaTecnica(context.pathParam("id")));
        context.render("app/heladeras/falla-tecnica.hbs", model);
    }

    @Override
    public void create(Context context) {
        String heladeraId = context.pathParam("id");

        HeladeraDto h = this.heladerasService.getHeladeraDto(heladeraId);

        Map<String, Object> model = new HashMap<>();

        model.put("heladera", h);
        context.render("/app/heladeras/reportar-falla.hbs", model);
    }

    @Override
    public void save(Context context) {
        FallaTecnicaAltaDto falla = FallaTecnicaAltaDto.of(context);
        UploadedFile uploadedFile = context.uploadedFile("file");
        try {
            if (uploadedFile != null) {
                String result = this.fileUploadService.uploadFile(uploadedFile);
                falla.setUrlFoto(result);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.fallasTecnicasService.crear(falla);
        Map<String, String> model = new HashMap<>();
        model.put("message", "La falla tecnica fue registrada con exito!");
        context.status(201);
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
