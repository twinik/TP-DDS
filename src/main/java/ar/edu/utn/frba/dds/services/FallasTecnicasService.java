package ar.edu.utn.frba.dds.services;

import static java.util.stream.Collectors.toList;

import ar.edu.utn.frba.dds.dtos.incidentes.FallaTecnicaAltaDto;
import ar.edu.utn.frba.dds.dtos.incidentes.FallaTecnicaDto;
import ar.edu.utn.frba.dds.dtos.incidentes.FallaTecnicaListadoDto;
import ar.edu.utn.frba.dds.exceptions.RecursoNoDisponibleException;
import ar.edu.utn.frba.dds.helpers.TecnicosHelper;
import ar.edu.utn.frba.dds.models.domain.colaboradores.Colaborador;
import ar.edu.utn.frba.dds.models.domain.heladeras.Heladera;
import ar.edu.utn.frba.dds.models.domain.incidentes.FallaTecnica;
import ar.edu.utn.frba.dds.models.domain.incidentes.Incidente;
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

    public List<FallaTecnicaListadoDto> obtenerTodos(String heladeraId, String solucionado) {
        List<FallaTecnica> fallas;
        if (heladeraId != null && !heladeraId.isEmpty()) {
            fallas = this.fallasTecnicasRepository.buscarPorHeladera(heladeraId);
        } else {
            fallas = this.fallasTecnicasRepository.buscarTodos();
        }
        if (solucionado != null && !solucionado.equals("Mostrar todas")) {
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

    public FallaTecnicaDto obtenerFallaTecnica(String id) {
        return FallaTecnicaDto.fromFalla(
            this.fallasTecnicasRepository.buscar(id)
                .orElseThrow(() -> new RecursoNoDisponibleException("Esta falla tecnica no existe")));
    }

    public void crear(FallaTecnicaAltaDto dto) {

        Colaborador colaborador = this.colaboradoresService.obtenerColaborador(dto.getIdColaborador());
        Heladera heladera = this.heladerasService.obtenerHeladera(dto.getHeladeraId());

//        if (!heladera.isHeladeraActiva()) {
//         throw new RecursoNoDisponibleException("La heladera ya se encuentra inhabilitada debido a otro incidente y estamos trabajando para solucionarlo cuanto antes. De todos modos, gracias por informarnos!");
//        }

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
