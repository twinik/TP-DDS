package ar.edu.utn.frba.dds.controllers;

import ar.edu.utn.frba.dds.dtos.reportes.ReporteDto;
import ar.edu.utn.frba.dds.services.ReportesService;
import ar.edu.utn.frba.dds.utils.ICrudViewsHandler;
import io.javalin.http.Context;
import lombok.AllArgsConstructor;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
public class ReportesController implements ICrudViewsHandler {

  private ReportesService service;

  @Override
  public void index(Context context) {
    LocalDate fechaDesde;
    LocalDate fechaHasta;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    if (context.queryParam("fechaDesde") != null && context.queryParam("fechaHasta") != null) {
      fechaDesde = LocalDate.parse(context.queryParam("fechaDesde"), formatter);
      fechaHasta = LocalDate.parse(context.queryParam("fechaHasta"), formatter);
    } else {
      fechaDesde = LocalDate.now().minusMonths(1);
      fechaHasta = LocalDate.now();
    }

    List<ReporteDto> reportes = this.service.obtenerReportes(fechaDesde, fechaHasta);
    Map<String, Object> model = new HashMap<>();
    model.put("fechaDesde", fechaDesde.format(formatter));
    model.put("fechaHasta", fechaHasta.format(formatter));
    model.put("reportes", reportes);
    context.render("app/reportes/reportes.hbs", model);
  }

  @Override
  public void show(Context context) {

  }

  @Override
  public void create(Context context) {

  }

  @Override
  public void save(Context context) {

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
