package ar.edu.utn.frba.dds.services;

import ar.edu.utn.frba.dds.dtos.ofertas.OfertaProductoDto;
import ar.edu.utn.frba.dds.models.domain.colaboraciones.OfertaProducto;
import ar.edu.utn.frba.dds.models.domain.colaboraciones.utils.CategoriaOferta;
import ar.edu.utn.frba.dds.models.domain.colaboraciones.utils.Producto;
import ar.edu.utn.frba.dds.models.domain.colaboradores.Colaborador;
import ar.edu.utn.frba.dds.models.repositories.IColaboradoresRepository;
import ar.edu.utn.frba.dds.models.repositories.IOfertaProductoRepository;
import lombok.AllArgsConstructor;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public class OfertasProductoService {
  private IOfertaProductoRepository ofertaProductoRepository;
  private IColaboradoresRepository colaboradoresRepository;

  public List<OfertaProductoDto> obtenerTodos() {
    return this.ofertaProductoRepository.buscarTodos().stream().map(OfertaProductoDto::fromOferta).toList();
  }

  public void crearOferta(OfertaProductoDto oferta) {

   Optional<Colaborador> colab =  colaboradoresRepository.buscar(oferta.getIdColaborador());

   // validar aca permisos con alguna capa de middleware ??

    if(colab.isEmpty())
        throw new NoAutorizadoException("no esta autorizado para realizar esta operacion");


    if (!oferta.estanCamposLlenos())
      throw new FormIncompletoException("no se proveen todos los campos necesarios");

    OfertaProducto ofertaProducto = new OfertaProducto();
    ofertaProducto.setProducto(new Producto(oferta.getNombre(),oferta.getUrlFoto()));
    ofertaProducto.setFechaCreacion(LocalDate.now());
    ofertaProducto.setPuntosNecesarios(oferta.getPuntosNecesarios());
    ofertaProducto.setColaborador(colab.get());
    // ignorar esto
    ofertaProducto.setCategoria(CategoriaOferta.valueOf(oferta.getCategoria()));


    ofertaProductoRepository.guardar(ofertaProducto);

  }

}
