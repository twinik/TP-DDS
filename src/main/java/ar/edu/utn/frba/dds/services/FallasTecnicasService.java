package ar.edu.utn.frba.dds.services;

import ar.edu.utn.frba.dds.dtos.incidentes.FallaTecnicaAltaDto;
import ar.edu.utn.frba.dds.dtos.incidentes.FallaTecnicaDto;
import ar.edu.utn.frba.dds.dtos.incidentes.FallaTecnicaListadoDto;
import ar.edu.utn.frba.dds.exceptions.RecursoNoDisponibleException;
import ar.edu.utn.frba.dds.helpers.TecnicosHelper;
import ar.edu.utn.frba.dds.models.domain.colaboradores.Colaborador;
import ar.edu.utn.frba.dds.models.domain.heladeras.Heladera;
import ar.edu.utn.frba.dds.models.domain.incidentes.FallaTecnica;
import ar.edu.utn.frba.dds.models.domain.notifications.NotificationStrategyFactory;
import ar.edu.utn.frba.dds.models.messageFactory.MensajeFallaTecnicaExistenteException;
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

  public List<FallaTecnicaListadoDto> obtenerTodos(String heladeraId, String solucionado) {
    List<FallaTecnica> fallas;
    if (heladeraId != null && !heladeraId.isBlank()) {
      fallas = this.fallasTecnicasRepository.buscarPorHeladera(heladeraId);
    } else {
      fallas = this.fallasTecnicasRepository.buscarTodos();
    }
    this.fallasTecnicasRepository.refresh(fallas);
    if (this.esParametroValido(solucionado)) {
      fallas = fallas.stream().filter(f -> {
        if (solucionado.equals("Solucionadas")) {
          return f.isSolucionado();
        } else {
          return !f.isSolucionado();
        }
      }).toList();
    }
    return fallas.stream().map(FallaTecnicaListadoDto::fromFalla).toList();
  }

  private boolean esParametroValido(String param) {
    return param != null && !param.isBlank() && !param.equals("Mostrar todas") && (param.equals("Solucionadas") || param.equals("No Solucionadas"));
  }

  public FallaTecnicaDto obtenerFallaTecnica(String id) {
    return FallaTecnicaDto.fromFalla(
        this.fallasTecnicasRepository.buscar(id)
            .orElseThrow(() -> new RecursoNoDisponibleException("Esta falla tecnica no existe")));
  }

  public void crear(FallaTecnicaAltaDto dto) {

    Colaborador colaborador = this.colaboradoresService.obtenerColaborador(dto.getIdColaborador());
    Heladera heladera = this.heladerasService.obtenerHeladera(dto.getHeladeraId());

    if (existenFallasNoSoluciondas(heladera.getId()))
      throw new RecursoNoDisponibleException(MensajeFallaTecnicaExistenteException.generarMensaje());

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

  private boolean existenFallasNoSoluciondas(String idHeladera) {
    return !this.fallasTecnicasRepository.buscarPorHeladera(idHeladera).stream().
        filter(f -> !f.isSolucionado()).toList().isEmpty();
  }


}
