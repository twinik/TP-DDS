package ar.edu.utn.frba.dds.dtos.colaboraciones;

import ar.edu.utn.frba.dds.models.domain.utils.Ubicacion;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class RecomendacionColocacionDto {
    private List<Float> coords;
    Float latitud;
    Float longitud;
    String nombre;
    String calle;
    Integer altura;

    public RecomendacionColocacionDto(Ubicacion ubicacion, String nombre, String calle, Integer altura) {
        this.coords = List.of(ubicacion.getLatitud(), ubicacion.getLongitud());
        this.latitud = ubicacion.getLatitud();
        this.longitud = ubicacion.getLongitud();
        this.nombre = nombre;
        this.calle = calle;
        this.altura = altura;
    }
}
