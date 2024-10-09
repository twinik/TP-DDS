package ar.edu.utn.frba.dds.dtos.heladeras;

import ar.edu.utn.frba.dds.externapi.recomendaciones.Recomendacion;
import ar.edu.utn.frba.dds.models.domain.heladeras.Heladera;
import lombok.AllArgsConstructor;
import lombok.Getter;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Getter
public class HeladeraMapaDto {
    private String id;
    private List<Float> coords;
    private String title;
    private boolean disabled;

    public static HeladeraMapaDto fromHeladera(Heladera h) {
        List<Float> coords = new ArrayList<>();
        coords.add(h.getUbicacion().getLatitud());
        coords.add(h.getUbicacion().getLongitud());
        return new HeladeraMapaDto(h.getId(), coords, h.getNombre(), !h.isHeladeraActiva());
    }

    public static HeladeraMapaDto fromRecomendacion(Recomendacion d) {
        List<Float> coords = new ArrayList<>();
        coords.add(Float.parseFloat(d.getLatitud()));
        coords.add(Float.parseFloat(d.getLongitud()));
        return new HeladeraMapaDto(d.getId(), coords, d.getNombre(), true);
    }
}