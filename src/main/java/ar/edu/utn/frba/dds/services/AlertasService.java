package ar.edu.utn.frba.dds.services;

import ar.edu.utn.frba.dds.dtos.heladeras.HeladeraDto;
import ar.edu.utn.frba.dds.dtos.incidentes.AlertaDto;
import ar.edu.utn.frba.dds.models.domain.incidentes.Alerta;
import ar.edu.utn.frba.dds.models.domain.incidentes.TipoAlerta;
import ar.edu.utn.frba.dds.models.repositories.IAlertasRepository;
import lombok.AllArgsConstructor;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@AllArgsConstructor
public class AlertasService {

  private IAlertasRepository alertasRepository;
  public List<AlertaDto> obtenerTodos(){
    return this.alertasRepository.buscarTodos().stream().map(AlertaDto::fromAlerta).toList();
  }

  public void crear(AlertaDto dto){
    Alerta a = new Alerta();
    a.setHeladera(HeladeraDto.toHeladera(dto.getHeladera()));
    a.setTimestamp(LocalDateTime.parse(dto.getFechaHora(), DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")));
    a.setTipoAlerta(TipoAlerta.valueOf(dto.getTipoAlerta().toUpperCase()));

    alertasRepository.guardar(a);
  }

}
