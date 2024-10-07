package ar.edu.utn.frba.dds.dtos.ofertas;

import ar.edu.utn.frba.dds.models.domain.colaboraciones.OfertaProducto;
import ar.edu.utn.frba.dds.models.domain.colaboraciones.utils.CategoriaOferta;
import io.javalin.http.Context;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
@Getter
@Builder
@Setter
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

  public static OfertaProductoDto of(Context context) {

    // TODO: conseguir categoria con el form param
    return OfertaProductoDto.builder()
        .nombre(context.formParam("nombre"))
        .puntosNecesarios(Float.valueOf(context.formParam("puntos")))
        .idColaborador(context.sessionAttribute("idColaborador"))
        .categoria(context.formParam("categoria")).build();
  }

  public boolean estanCamposLlenos() {
    return (this.nombre != null) && (this.urlFoto != null) && (this.puntosNecesarios != null)
        && (this.idColaborador != null);
  }

}