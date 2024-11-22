package ar.edu.utn.frba.dds.services;

import ar.edu.utn.frba.dds.dtos.incidentes.AlertaDto;
import ar.edu.utn.frba.dds.models.domain.heladeras.Heladera;
import ar.edu.utn.frba.dds.models.domain.incidentes.Alerta;
import ar.edu.utn.frba.dds.models.repositories.IAlertasRepository;
import lombok.AllArgsConstructor;
import java.util.List;

@AllArgsConstructor
public class AlertasService {

  private IAlertasRepository alertasRepository;
  private HeladerasService heladerasService;

  public List<AlertaDto> obtenerTodos() {
    return this.alertasRepository.buscarTodos().stream().map(AlertaDto::fromAlerta).toList();
  }

  public void reportarYGuardarSiNoEstabaElMismoProblema(Alerta alerta, Heladera heladera) {
    List<Alerta> alertasAnteriores = this.alertasRepository.buscarAlertasHeladera(heladera.getId());
    boolean existeOtraNoSolucionada = alertasAnteriores.stream().anyMatch(a -> !a.isSolucionado() && a.getTipoAlerta().equals(alerta.getTipoAlerta()));
    if (!existeOtraNoSolucionada) {
      this.alertasRepository.guardar(alerta);
      alerta.reportar();
      this.heladerasService.detach(heladera);
      this.heladerasService.actualizarHeladera(heladera);
    }
  }
}
