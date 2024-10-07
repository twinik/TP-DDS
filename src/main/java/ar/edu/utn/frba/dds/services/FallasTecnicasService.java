package ar.edu.utn.frba.dds.services;

import ar.edu.utn.frba.dds.dtos.incidentes.FallaTecnicaDto;
import ar.edu.utn.frba.dds.helpers.TecnicosHelper;
import ar.edu.utn.frba.dds.models.domain.colaboradores.Colaborador;
import ar.edu.utn.frba.dds.models.domain.heladeras.Heladera;
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
  private ColaboradoresService colaboradoresService;
  private HeladerasService heladerasService;

  public List<FallaTecnicaDto> obtenerTodos(){
    return this.fallasTecnicasRepository.buscarTodos().stream().map(FallaTecnicaDto::fromFalla).toList();
  }

  public void crear(FallaTecnicaDto dto){

//    colaboradoresService.validarExistenciaColaborador(dto.getIdColaborador());

    // ya te lo valida aca

    Colaborador colaborador = this.colaboradoresService.obtenerColaborador(dto.getIdColaborador());
    Heladera heladera = this.heladerasService.obtenerHeladera(dto.getHeladeraId());


    FallaTecnica falla = new FallaTecnica(
        heladera,
        LocalDateTime.now(),
        ServiceLocator.get(TecnicosHelper.class),
        new NotificationStrategyFactory(),
        colaborador,
        dto.getDescripcion(),
        dto.getUrlFoto()
    );

    falla.reportar();

    this.fallasTecnicasRepository.guardar(falla);
  }


}
