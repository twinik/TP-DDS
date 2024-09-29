package ar.edu.utn.frba.dds.dtos.ofertas;

import ar.edu.utn.frba.dds.models.domain.colaboraciones.OfertaProducto;
import lombok.AllArgsConstructor;
import lombok.Getter;

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

  public static  OfertaProductoDto of (String nombre, String puntosNecesarios, String urlFoto,String idColaborador){
    return new OfertaProductoDto(nombre,puntosNecesarios,urlFoto,idColaborador);
  }

}
