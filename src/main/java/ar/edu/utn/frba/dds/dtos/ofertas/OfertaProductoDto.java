package ar.edu.utn.frba.dds.dtos.ofertas;

import ar.edu.utn.frba.dds.models.domain.colaboraciones.OfertaProducto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
@Getter
public class OfertaProductoDto {
  private String nombre;
  private String puntosNecesarios;
  private String urlFoto;
  private String idColaborador;

  // TODO: agregar el colaborador que hizo la oferta ??

  public static OfertaProductoDto fromOferta(OfertaProducto oferta){
    return new OfertaProductoDto(oferta.getProducto().getNombre(),String.valueOf(oferta.getPuntosNecesarios()),oferta.getProducto().getUrlFoto(),oferta.getColaborador().getId());
  }

  public static  OfertaProductoDto of (Map<String, List<String>> camposFormulario, String idColaborador){
    return new OfertaProductoDto(camposFormulario.get("nombre").get(0),
        camposFormulario.get("puntos").get(0),camposFormulario.get("foto").get(0),idColaborador);
  }

  public boolean estanCamposLlenos(){
    return (this.nombre != null) && (this.urlFoto!= null) && (this.puntosNecesarios != null)
        && (this.idColaborador != null);
  }


}
