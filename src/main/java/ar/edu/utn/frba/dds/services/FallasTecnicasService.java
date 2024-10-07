package ar.edu.utn.frba.dds.services;

import ar.edu.utn.frba.dds.dtos.incidentes.FallaTecnicaDto;
import ar.edu.utn.frba.dds.helpers.TecnicosHelper;
import ar.edu.utn.frba.dds.models.domain.incidentes.FallaTecnica;
import ar.edu.utn.frba.dds.models.domain.notifications.NotificationStrategyFactory;
import ar.edu.utn.frba.dds.models.repositories.IFallasTecnicasRepository;
import ar.edu.utn.frba.dds.serviceLocator.ServiceLocator;
import lombok.AllArgsConstructor;
import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
public class FallasTecnicasService {
  private IFallasTecnicasRepository fallasTecnicasRepository;

  public List<FallaTecnicaDto> obtenerTodos(){
    return this.fallasTecnicasRepository.buscarTodos().stream().map(FallaTecnicaDto::fromFalla).toList();
  }

  public void crear(FallaTecnicaDto dto){

    ColaboradoresService colaboradoresService = ServiceLocator.get(ColaboradoresService.class);
    colaboradoresService.validarExistenciaColaborador(dto.getIdColaborador());

    HeladerasService heladerasService = ServiceLocator.get(HeladerasService.class);
    heladerasService.validarExistenciaHeladera(dto.getHeladeraId());

    FallaTecnica falla = new FallaTecnica(
        heladerasService.obtenerHeladera(dto.getHeladeraId()).get(),
        LocalDateTime.now(),
        ServiceLocator.get(TecnicosHelper.class),
        new NotificationStrategyFactory(),
        colaboradoresService.obtenerColaborador(dto.getIdColaborador()).get(),
        dto.getDescripcion(),
        dto.getUrlFoto()
    );

    falla.reportar();

    this.fallasTecnicasRepository.guardar(falla);
  }


}
