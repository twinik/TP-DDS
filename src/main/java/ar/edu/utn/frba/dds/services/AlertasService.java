package ar.edu.utn.frba.dds.services;

import ar.edu.utn.frba.dds.dtos.heladeras.HeladeraDto;
import ar.edu.utn.frba.dds.dtos.incidentes.AlertaDto;
import ar.edu.utn.frba.dds.models.domain.incidentes.Alerta;
import ar.edu.utn.frba.dds.models.repositories.IAlertasRepository;
import lombok.AllArgsConstructor;
import java.util.List;

@AllArgsConstructor
public class AlertasService {

  private IAlertasRepository alertasRepository;
  public List<AlertaDto> obtenerTodos(){
    return this.alertasRepository.buscarTodos().stream().map(AlertaDto::fromAlerta).toList();
  }

  public void crear(AlertaDto dto){
    // TODO completar
    Alerta a = new Alerta();


    alertasRepository.guardar(a);
  }

}
