package ar.edu.utn.frba.dds.dtos.heladeras;


import ar.edu.utn.frba.dds.dtos.DireccionDto;
import ar.edu.utn.frba.dds.models.domain.heladeras.Heladera;
import io.javalin.http.Context;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class HeladeraDto {
    private String id;
    private String nombre;
    private Boolean activa;
    private Integer viandas;
    private Integer capacidadMaxima;
    private ModeloHeladeraOutputDto modelo;
    private DireccionDto direccion;

    public static HeladeraDto fromHeladera(Heladera heladera) {
        return HeladeraDto
                .builder()
                .id(heladera.getId())
                .nombre(heladera.getNombre())
                .activa(heladera.isHeladeraActiva())
                .viandas(heladera.getViandas())
                .capacidadMaxima(heladera.getCapacidadViandas())
                .modelo(ModeloHeladeraOutputDto.fromModelo(heladera.getModelo()))
                .direccion(DireccionDto.fromDireccion(heladera.getDireccion()))
                .build();
    }

    public static HeladeraDto of(Context context) {
        return HeladeraDto
                .builder()
                .id(context.pathParam("id"))
                .nombre(context.formParam("nombre"))
                .build();
    }

}
