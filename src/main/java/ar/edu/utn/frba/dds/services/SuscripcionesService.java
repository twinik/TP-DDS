package ar.edu.utn.frba.dds.services;

import ar.edu.utn.frba.dds.dtos.suscripciones.SuscripcionDto;
import ar.edu.utn.frba.dds.exceptions.ContactoVacioException;
import ar.edu.utn.frba.dds.models.domain.colaboradores.Colaborador;
import ar.edu.utn.frba.dds.models.domain.heladeras.RecomendadorHeladeras;
import ar.edu.utn.frba.dds.models.domain.notifications.NotificationStrategyFactory;
import ar.edu.utn.frba.dds.models.domain.suscripciones.Suscripcion;
import ar.edu.utn.frba.dds.models.domain.suscripciones.SuscripcionDesperfectoHeladera;
import ar.edu.utn.frba.dds.models.domain.suscripciones.SuscripcionViandasFaltantes;
import ar.edu.utn.frba.dds.models.domain.suscripciones.SuscripcionViandasRestantes;
import ar.edu.utn.frba.dds.models.domain.utils.CanalContacto;
import ar.edu.utn.frba.dds.models.messageFactory.MensajeConatctoVacioSuscripcionFactory;
import ar.edu.utn.frba.dds.models.repositories.ISuscripcionesRepository;
import ar.edu.utn.frba.dds.serviceLocator.ServiceLocator;
import lombok.AllArgsConstructor;
import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
public class SuscripcionesService {
  private ISuscripcionesRepository repo;
  private ColaboradoresService colaboradoresService;

  public Map<String, Boolean> contactosDisponibles(String idColaborador) {
    Colaborador c = this.colaboradoresService.obtenerColaborador(idColaborador);
    Map<String, Boolean> result = new HashMap<>();
    result.put("tieneMail", c.email() != null);
    result.put("whastapp", c.telefonoCompleto() != null);
    result.put("telegram", c.telegramId() != null);
    return result;
  }


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
        if (s.getColaborador().telefonoCompleto() == null)
          throw new ContactoVacioException(MensajeConatctoVacioSuscripcionFactory.generarMensaje("whatsapp"));
        s.setNotificacionStrategy(new NotificationStrategyFactory().create(CanalContacto.WHATSAPP));
        break;
      case "email":
        if (s.getColaborador().email() == null)
          throw new ContactoVacioException(MensajeConatctoVacioSuscripcionFactory.generarMensaje("email"));
        s.setNotificacionStrategy(new NotificationStrategyFactory().create(CanalContacto.EMAIL));
        break;
      case "telegram":
        if (s.getColaborador().telegramId() == null)
          throw new ContactoVacioException(MensajeConatctoVacioSuscripcionFactory.generarMensaje("telegram"));
        s.setNotificacionStrategy(new NotificationStrategyFactory().create(CanalContacto.TELEGRAM));
        break;
    }
    this.repo.guardar(s);
    return s;
  }
}
