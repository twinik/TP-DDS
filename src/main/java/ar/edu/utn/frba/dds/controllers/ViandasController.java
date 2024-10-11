package ar.edu.utn.frba.dds.controllers;

import ar.edu.utn.frba.dds.services.ViandasService;
import ar.edu.utn.frba.dds.utils.ICrudViewsHandler;
import io.javalin.http.Context;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ViandasController implements ICrudViewsHandler {
  private ViandasService viandasService;

  @Override
  void index(Context context) {

  }

  @Override
  void show(Context context) {

  }

  @Override
  void create(Context context) {

  }

  @Override
  void save(Context context) {

  }

  @Override
  void edit(Context context) {

  }

  @Override
  void update(Context context) {

  }

  @Override
  void delete(Context context) {

  }
}
