package ar.edu.utn.frba.dds.dtos.ofertas;

import ar.edu.utn.frba.dds.models.domain.colaboraciones.utils.CategoriaOferta;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CategoriaOfertaDto {
    private String nombre;

    public static CategoriaOfertaDto of(CategoriaOferta c) {
        return new CategoriaOfertaDto(c.name());
    }
}
