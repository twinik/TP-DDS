package ar.edu.utn.frba.dds.cronjobs;

import ar.edu.utn.frba.dds.domain.heladeras.Heladera;
import ar.edu.utn.frba.dds.domain.heladeras.RegistroTemperatura;
import ar.edu.utn.frba.dds.domain.incidentes.Alerta;
import ar.edu.utn.frba.dds.domain.incidentes.TipoAlerta;
import ar.edu.utn.frba.dds.serviceLocator.ServiceLocator;
import ar.edu.utn.frba.dds.helpers.DateHelper;
import ar.edu.utn.frba.dds.repositories.IHeladerasRepository;
import java.time.LocalDateTime;
import java.util.List;

public class FallaConexionCronJob {
  public static void main(String[] args) {
    IHeladerasRepository heladerasRepository = (IHeladerasRepository) ServiceLocator.get("heladerasRepository");
    List<Heladera> heladeras = heladerasRepository.buscarTodos();
    for (Heladera h : heladeras) {
      List<RegistroTemperatura> registroTemperaturas = h.getRegistroTemperaturas();
      LocalDateTime ultimaFechaYHoraRegistrada = registroTemperaturas.get(registroTemperaturas.size() - 1).getFechaHora();
      if (DateHelper.minutosEntre(ultimaFechaYHoraRegistrada, LocalDateTime.now()) > 5) { // Agregar desde el archivo de config (ya esta)
        Alerta alerta = new Alerta(h, LocalDateTime.now(), TipoAlerta.FALLA_CONEXION);
        alerta.reportar();  //TODO persistir alerta
      }
    }
  }
}

//A partir de la 18, clase verificador falla conexion. verificarfallaconexion(), con esos metodos puedo testearlos TOP.
