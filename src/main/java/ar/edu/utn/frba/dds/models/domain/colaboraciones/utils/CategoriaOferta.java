package ar.edu.utn.frba.dds.models.domain.colaboraciones.utils;

import java.util.Arrays;
import java.util.List;

/**
 * CategoriaOferta enum representa las categorias de las ofertas.
 */
public enum CategoriaOferta {
    GASTRONOMIA,
    ELECTRONICA,
    ARTICULOS_HOGAR,
    OTROS;

    public static List<String> obtenerNombresCategorias(){
        return Arrays.stream(CategoriaOferta.values()).map(Enum::name).toList();
    }

}