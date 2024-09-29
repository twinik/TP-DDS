package ar.edu.utn.frba.dds.controllers;

import ar.edu.utn.frba.dds.models.domain.colaboraciones.OfertaProducto;
import ar.edu.utn.frba.dds.models.domain.colaboraciones.utils.CategoriaOferta;
import ar.edu.utn.frba.dds.models.domain.colaboraciones.utils.Producto;
import ar.edu.utn.frba.dds.models.domain.colaboradores.Colaborador;
import ar.edu.utn.frba.dds.models.repositories.IOfertaProductoRepository;
import ar.edu.utn.frba.dds.utils.ICrudViewsHandler;
import ar.edu.utn.frba.dds.utils.Initializer;
import io.javalin.http.Context;
import io.javalin.http.HttpStatus;
import lombok.AllArgsConstructor;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@AllArgsConstructor
public class OfertasProductoController implements ICrudViewsHandler {
  private IOfertaProductoRepository ofertaProductoRepository;

  @Override
  public void index(Context context) {
    //PRETENDE DEVOLVER UNA VISTA QUE CONTENGA A TODOS LOS PRODUCTOS ALMACENADOS EN MI SISTEMA
    List<OfertaProducto> ofertas = ofertaProductoRepository.buscarTodos();
    Map<String, Object> model = new HashMap<>();
    model.put("ofertas", ofertas);
    context.render("/app/productos/productos.hbs", model);
  }

  @Override
  public void show(Context context) {
    //RECIBE POR PATH PARAM EL ID DE UN OFERTA Y PRETENDE DEVOLVER UNA VISTA CON EL DETALLE DE ESE PRODUCTO
    // TODO: falta esta vista
    Optional<OfertaProducto> posibleOferta = this.ofertaProductoRepository.buscar(context.pathParam("id"));

    if(posibleOferta.isEmpty()){
      context.status(HttpStatus.NOT_FOUND);
      return;
    }

    Map<String, Object> model = new HashMap<>();
    model.put("oferta", posibleOferta.get());

    context.render("insertar_vista_mis_amores", model);
  }

  @Override
  public void create(Context context) {
    //PRETENDE DEVOLVER UNA VISTA CON UN FORMULARIO PARA DAR DE ALTA UNA NUEVA OFERTA
    context.render("/app/colaboraciones/ofrecer-productos.hbs");
  }

  @Override
  public void save(Context context) {
    OfertaProducto oferta = new OfertaProducto();
    Optional<Colaborador> colaborador = Initializer.dameOng();
    if (colaborador.isEmpty()) {
      context.status(HttpStatus.UNAUTHORIZED);
      return;
    }

    oferta.setColaborador(colaborador.get());
    oferta.setPuntosNecesarios(Float.parseFloat(context.formParam("puntos")));

    // TODO: no anda la foto ni para subirla
    oferta.setProducto(new Producto(context.formParam("nombre"), context.formParam("foto")));
    oferta.setFechaCreacion(LocalDate.now());

    // TODO: falta esto en el form en un desplegable
    oferta.setCategoria(CategoriaOferta.OTROS);


    this.ofertaProductoRepository.guardar(oferta);
    //O BIEN LANZO UNA PANTALLA DE EXITO
    //O BIEN REDIRECCIONO AL USER A LA PANTALLA DE LISTADO DE PRODUCTOS
    context.redirect("/productos");
  }

  @Override
  public void edit(Context context) {
    //PRETENDE DEVOLVER UNA VISTA CON UN FORMULARIO QUE PERMITA EDITAR AL RECURSO QUE LLEGA POR PATH PARAM
    // TODO: falta esta vista
    Optional<OfertaProducto> posibleOferta = this.ofertaProductoRepository.buscar(context.pathParam("id"));

    if(posibleOferta.isEmpty()){
      context.status(HttpStatus.NOT_FOUND);
      return;
    }

    Map<String, Object> model = new HashMap<>();
    model.put("oferta", posibleOferta.get());
    model.put("edicion",true);

    context.render("insertar_vista_mis_amores", model);
  }

  @Override
  public void update(Context context) {

  }

  @Override
  public void delete(Context context) {

  }
}
