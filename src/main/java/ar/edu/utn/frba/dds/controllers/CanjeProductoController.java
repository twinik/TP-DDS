package ar.edu.utn.frba.dds.controllers;

import ar.edu.utn.frba.dds.models.domain.colaboraciones.OfertaProducto;
import ar.edu.utn.frba.dds.serviceLocator.ServiceLocator;
import ar.edu.utn.frba.dds.services.OfertasProductoService;
import ar.edu.utn.frba.dds.utils.ICrudViewsHandler;
import io.javalin.http.Context;
import io.micrometer.core.instrument.step.StepMeterRegistry;
import lombok.AllArgsConstructor;
import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
public class CanjeProductoController implements ICrudViewsHandler {
  private OfertasProductoService ofertasProductoService;

  @Override
  public void index(Context context) {

  }

  @Override
  public void show(Context context) {

  }

  @Override
  public void create(Context context) {

  }

  @Override
  public void save(Context context) {
    Map<String, Object> model = new HashMap<>();

    try {
      OfertaProducto oferta = this.ofertasProductoService.obtenerOferta(context.pathParam("id"));
      this.ofertasProductoService.canjearOferta(context.sessionAttribute("idColaborador"), context.pathParam("id"));
      model.put("message", "El canje del producto fue realizado con exito");
      model.put("nombreProducto", oferta.getProducto().getNombre());
      model.put("puntosProducto", oferta.getPuntosNecesarios());
      ServiceLocator.get(StepMeterRegistry.class).counter("Canjes","status","ok").increment();
      context.status(201);
      context.render("app/success.hbs", model);
    } catch (Exception e) {
      model.put("message", e.getMessage());
      context.status(400);
      ServiceLocator.get(StepMeterRegistry.class).counter("Canjes","status","failed").increment();
      context.render("app/error.hbs", model);
    }
  }

  @Override
  public void edit(Context context) {

  }

  @Override
  public void update(Context context) {

  }

  @Override
  public void delete(Context context) {

  }
}
