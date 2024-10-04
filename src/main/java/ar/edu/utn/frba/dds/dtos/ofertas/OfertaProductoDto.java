package ar.edu.utn.frba.dds.dtos.ofertas;

import ar.edu.utn.frba.dds.models.domain.colaboraciones.OfertaProducto;
import ar.edu.utn.frba.dds.models.domain.colaboraciones.utils.CategoriaOferta;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
@Getter
@Builder
public class OfertaProductoDto {
  private String nombre;
  private Float puntosNecesarios;
  private String urlFoto;
  private String idColaborador;
  private String categoria;

  // TODO: agregar el colaborador que hizo la oferta ??

  public static OfertaProductoDto fromOferta(OfertaProducto oferta) {
    return OfertaProductoDto.builder()
        .nombre(oferta.getProducto().getNombre())
        .puntosNecesarios(oferta.getPuntosNecesarios())
        .urlFoto(oferta.getProducto().getUrlFoto())
        .idColaborador(oferta.getColaborador().getId())
        .categoria(oferta.getCategoria().name()).build();
  }

  public static OfertaProductoDto of(Map<String, List<String>> camposFormulario, String idColaborador) {

    // TODO: conseguir categoria con el form param
    return OfertaProductoDto.builder()
        .nombre(camposFormulario.get("nombre").get(0))
        .puntosNecesarios(Float.valueOf(camposFormulario.get("puntos").get(0)))
        .urlFoto(camposFormulario.get("foto").get(0)).idColaborador(idColaborador)
        .categoria(CategoriaOferta.OTROS.name()).build();
  }

  public boolean estanCamposLlenos() {
    return (this.nombre != null) && (this.urlFoto != null) && (this.puntosNecesarios != null)
        && (this.idColaborador != null);
  }

}