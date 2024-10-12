package ar.edu.utn.frba.dds.controllers;

import ar.edu.utn.frba.dds.services.OfertasProductoService;
import ar.edu.utn.frba.dds.utils.ICrudViewsHandler;
import io.javalin.http.Context;
import java.util.HashMap;
import java.util.Map;
import lombok.AllArgsConstructor;

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
      this.ofertasProductoService.canjearOferta(context.sessionAttribute("idColaborador"), context.pathParam("id"));
      model.put("message", "El canje del producto fue realizado con exito");
      context.render("/app/success.hbs", model);
    } catch (Exception e) {
      model.put("message", e.getMessage());
      context.render("/app/error.hbs", model);
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
