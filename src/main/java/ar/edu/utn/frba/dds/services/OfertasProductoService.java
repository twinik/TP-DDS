package ar.edu.utn.frba.dds.services;

import ar.edu.utn.frba.dds.dtos.CanjeOutputDto;
import ar.edu.utn.frba.dds.dtos.ofertas.OfertaProductoDto;
import ar.edu.utn.frba.dds.dtos.personas.ColaboradorPuntosDto;
import ar.edu.utn.frba.dds.exceptions.FormIncompletoException;
import ar.edu.utn.frba.dds.exceptions.RecursoInexistenteException;
import ar.edu.utn.frba.dds.models.domain.colaboraciones.OfertaProducto;
import ar.edu.utn.frba.dds.models.domain.colaboraciones.utils.CanjeProducto;
import ar.edu.utn.frba.dds.models.domain.colaboraciones.utils.CategoriaOferta;
import ar.edu.utn.frba.dds.models.domain.colaboraciones.utils.Producto;
import ar.edu.utn.frba.dds.models.domain.colaboraciones.utils.PuntosInsuficientesException;
import ar.edu.utn.frba.dds.models.domain.colaboradores.Colaborador;
import ar.edu.utn.frba.dds.models.messageFactory.MensajeFormIncompletoFactory;
import ar.edu.utn.frba.dds.models.messageFactory.MensajeRecursoInexistenteFactory;
import ar.edu.utn.frba.dds.models.repositories.ICanjeProductoRepository;
import ar.edu.utn.frba.dds.models.repositories.IOfertaProductoRepository;
import lombok.AllArgsConstructor;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public class OfertasProductoService {
    private IOfertaProductoRepository ofertaProductoRepository;
    private ICanjeProductoRepository canjeProductoRepository;
    private ColaboradoresService colaboradoresService;

    public List<OfertaProductoDto> obtenerTodos() {
        return this.ofertaProductoRepository.buscarTodos().stream().map(OfertaProductoDto::fromOferta).toList();
    }

    public List<OfertaProductoDto> obtenerTodos(String categoria) {
        try {
            CategoriaOferta categoriaOferta = CategoriaOferta.valueOf(categoria.toUpperCase());
            return this.ofertaProductoRepository.buscarTodos().stream().filter(o -> o.getCategoria().equals(categoriaOferta))
                    .map(OfertaProductoDto::fromOferta).toList();
        } catch (IllegalArgumentException e) {
            return obtenerTodos();
        }
    }

    public List<CanjeOutputDto> obtenerCanjes(String idColaborador) {
        return this.canjeProductoRepository.buscarPorColaborador(idColaborador).stream()
                .map(CanjeOutputDto::fromCanje).toList();
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
        this.colaboradoresService.refresh(c);
        return ColaboradorPuntosDto.fromColaborador(c);
    }

    public void canjearOferta(String idColaborador, String idOferta) throws PuntosInsuficientesException {
        Colaborador c = this.colaboradoresService.obtenerColaborador(idColaborador);
        this.colaboradoresService.refresh(c);
        OfertaProducto o = this.obtenerOferta(idOferta);

        if (!o.puedeSerCanjeadoPor(c))
            throw new PuntosInsuficientesException(o.getProducto().getNombre());

        CanjeProducto canje = new CanjeProducto();
        canje.setComprador(c);
        canje.setOfertaCanjeada(o);
        canje.setFechaCanje(LocalDateTime.now());
        canje.setPuntosGastados(o.getPuntosNecesarios());
        this.canjeProductoRepository.guardar(canje);

        c.restarPuntos(o.getPuntosNecesarios());
        this.colaboradoresService.actualizar(c);
        this.colaboradoresService.refresh(c);
    }

    public OfertaProducto obtenerOferta(String idOferta) {
        if (idOferta == null)
            throw new RecursoInexistenteException(MensajeRecursoInexistenteFactory.generarMensaje("Oferta producto"));
        Optional<OfertaProducto> oferta = this.ofertaProductoRepository.buscar(idOferta);
        if (oferta.isEmpty())
            throw new RecursoInexistenteException(MensajeRecursoInexistenteFactory.generarMensaje("Oferta producto", idOferta));
        return oferta.get();
    }
}
