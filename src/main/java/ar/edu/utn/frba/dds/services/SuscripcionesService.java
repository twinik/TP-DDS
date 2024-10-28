package ar.edu.utn.frba.dds.services;

import ar.edu.utn.frba.dds.dtos.suscripciones.SuscripcionDto;
import ar.edu.utn.frba.dds.models.domain.heladeras.RecomendadorHeladeras;
import ar.edu.utn.frba.dds.models.domain.notifications.NotificationStrategyFactory;
import ar.edu.utn.frba.dds.models.domain.suscripciones.Suscripcion;
import ar.edu.utn.frba.dds.models.domain.suscripciones.SuscripcionDesperfectoHeladera;
import ar.edu.utn.frba.dds.models.domain.suscripciones.SuscripcionViandasFaltantes;
import ar.edu.utn.frba.dds.models.domain.suscripciones.SuscripcionViandasRestantes;
import ar.edu.utn.frba.dds.models.domain.utils.CanalContacto;
import ar.edu.utn.frba.dds.models.repositories.ISuscripcionesRepository;
import ar.edu.utn.frba.dds.serviceLocator.ServiceLocator;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class SuscripcionesService {
  private ISuscripcionesRepository repo;
  private ColaboradoresService colaboradoresService;

  public Suscripcion guardarSuscripcion(SuscripcionDto dto) {
    Suscripcion s = new Suscripcion();
    s.setColaborador(this.colaboradoresService.obtenerColaborador(dto.getIdColaborador()));
    switch (dto.getTipoSuscripcion()) {
      case "viandas-restantes":
        s.setTipoSuscripcion(new SuscripcionViandasRestantes());
        s.setNumero(Integer.parseInt(dto.getNumero()));
        break;
      case "viandas-faltantes":
        s.setTipoSuscripcion(new SuscripcionViandasFaltantes());
        s.setNumero(Integer.parseInt(dto.getNumero()));
        break;
      case "desperfecto-heladera":
        s.setTipoSuscripcion(new SuscripcionDesperfectoHeladera(ServiceLocator.get(RecomendadorHeladeras.class)));
        break;
    }
    switch (dto.getCanalContacto()) {
      case "whatsapp":
        s.setNotificacionStrategy(new NotificationStrategyFactory().create(CanalContacto.WHATSAPP));
        break;
      case "email":
        s.setNotificacionStrategy(new NotificationStrategyFactory().create(CanalContacto.EMAIL));
        break;
      case "telegram":
        s.setNotificacionStrategy(new NotificationStrategyFactory().create(CanalContacto.TELEGRAM));
        break;
    }
    this.repo.guardar(s);
    return s;
  }
}
