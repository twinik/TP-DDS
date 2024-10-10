package ar.edu.utn.frba.dds.services;

import ar.edu.utn.frba.dds.dtos.ofertas.OfertaProductoDto;
import ar.edu.utn.frba.dds.dtos.personas.ColaboradorPuntosDto;
import ar.edu.utn.frba.dds.exceptions.FormIncompletoException;
import ar.edu.utn.frba.dds.models.domain.colaboraciones.OfertaProducto;
import ar.edu.utn.frba.dds.models.domain.colaboraciones.utils.CategoriaOferta;
import ar.edu.utn.frba.dds.models.domain.colaboraciones.utils.Producto;
import ar.edu.utn.frba.dds.models.domain.colaboradores.Colaborador;
import ar.edu.utn.frba.dds.models.messageFactory.MensajeFormIncompletoFactory;
import ar.edu.utn.frba.dds.models.repositories.IOfertaProductoRepository;
import lombok.AllArgsConstructor;
import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
public class OfertasProductoService {
    private IOfertaProductoRepository ofertaProductoRepository;
    private ColaboradoresService colaboradoresService;

    public List<OfertaProductoDto> obtenerTodos() {
        return this.ofertaProductoRepository.buscarTodos().stream().map(OfertaProductoDto::fromOferta).toList();
    }

    public void crearOferta(OfertaProductoDto oferta) {

        Colaborador colab = this.colaboradoresService.obtenerColaborador(oferta.getIdColaborador());

        // validar aca permisos con alguna capa de middleware ??

        if (!oferta.estanCamposLlenos())
            throw new FormIncompletoException(MensajeFormIncompletoFactory.generarMensaje());

        OfertaProducto ofertaProducto = new OfertaProducto();
        ofertaProducto.setProducto(new Producto(oferta.getNombre(), oferta.getUrlFoto()));
        ofertaProducto.setFechaCreacion(LocalDate.now());
        ofertaProducto.setPuntosNecesarios(oferta.getPuntosNecesarios());
        ofertaProducto.setColaborador(colab);
        ofertaProducto.setCategoria(CategoriaOferta.valueOf(oferta.getCategoria()));


        ofertaProductoRepository.guardar(ofertaProducto);

    }

    public ColaboradorPuntosDto obtenerPuntos(String idColaborador) {
        Colaborador c = this.colaboradoresService.obtenerColaborador(idColaborador);
        return ColaboradorPuntosDto.fromColaborador(c);
    }

}
