package ar.edu.utn.frba.dds.controllers;

import ar.edu.utn.frba.dds.dtos.ofertas.CategoriaOfertaDto;
import ar.edu.utn.frba.dds.dtos.ofertas.OfertaProductoDto;
import ar.edu.utn.frba.dds.dtos.personas.ColaboradorPuntosDto;
import ar.edu.utn.frba.dds.models.domain.colaboraciones.utils.CategoriaOferta;
import ar.edu.utn.frba.dds.services.FileUploadService;
import ar.edu.utn.frba.dds.services.OfertasProductoService;
import ar.edu.utn.frba.dds.utils.ICrudViewsHandler;
import io.javalin.http.Context;
import io.javalin.http.UploadedFile;
import lombok.AllArgsConstructor;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
public class OfertasProductoController implements ICrudViewsHandler {
  private OfertasProductoService ofertasProductoService;
  private FileUploadService fileUploadService;

  @Override
  public void index(Context context) {
    //PRETENDE DEVOLVER UNA VISTA QUE CONTENGA A TODOS LOS PRODUCTOS ALMACENADOS EN MI SISTEMA
    List<OfertaProductoDto> ofertas = this.ofertasProductoService.obtenerTodos((context.queryParam("categoria") != null) ? context.queryParam("categoria") : "");
    Map<String, Object> model = new HashMap<>();
    if (context.sessionAttribute("idColaborador") != null) {
      ColaboradorPuntosDto puntosdDisponibles = this.ofertasProductoService.obtenerPuntos(context.sessionAttribute("idColaborador"));
      model.put("puntosDisp", puntosdDisponibles);
    }
    model.put("ofertas", ofertas);
    context.render("/app/productos/productos.hbs", model);
  }

  @Override
  public void show(Context context) {
    //RECIBE POR PATH PARAM EL ID DE UN OFERTA Y PRETENDE DEVOLVER UNA VISTA CON EL DETALLE DE ESE PRODUCTO
  }

  @Override
  public void create(Context context) {
    //PRETENDE DEVOLVER UNA VISTA CON UN FORMULARIO PARA DAR DE ALTA UNA NUEVA OFERTA
    Map<String, Object> model = new HashMap<>();
    model.put("categorias", Arrays.stream(CategoriaOferta.values()).map(CategoriaOfertaDto::of).toList());
    context.render("/app/colaboraciones/ofrecer-productos.hbs", model);
  }

  @Override
  public void save(Context context) {
    // obtener de sesion
    OfertaProductoDto dto = OfertaProductoDto.of(context);
    UploadedFile uploadedFile = context.uploadedFile("file");
    try {
      if (uploadedFile != null) {
        String result = this.fileUploadService.uploadFile(uploadedFile);
        dto.setUrlFoto(result);
      }
      ofertasProductoService.crearOferta(dto);
    } catch (IOException e) {
      e.printStackTrace();
    }
    //O BIEN LANZO UNA PANTALLA DE EXITO
    //O BIEN REDIRECCIONO AL USER A LA PANTALLA DE LISTADO DE PRODUCTOS
    context.status(201);
    context.redirect("/productos");
  }

  @Override
  public void edit(Context context) {
    //PRETENDE DEVOLVER UNA VISTA CON UN FORMULARIO QUE PERMITA EDITAR AL RECURSO QUE LLEGA POR PATH PARAM
  }

  @Override
  public void update(Context context) {

  }

  @Override
  public void delete(Context context) {

  }
}
