package ar.edu.utn.frba.dds.cronjobs;

import ar.edu.utn.frba.dds.helpers.ConfigReader;
import ar.edu.utn.frba.dds.helpers.TecnicosHelper;
import ar.edu.utn.frba.dds.models.domain.heladeras.Heladera;
import ar.edu.utn.frba.dds.models.domain.heladeras.VerificadorConexionHeladera;
import ar.edu.utn.frba.dds.models.domain.incidentes.Alerta;
import ar.edu.utn.frba.dds.models.domain.incidentes.TipoAlerta;
import ar.edu.utn.frba.dds.models.domain.notifications.NotificationStrategyFactory;
import ar.edu.utn.frba.dds.models.repositories.IHeladerasRepository;
import ar.edu.utn.frba.dds.models.repositories.ITecnicosRepository;
import ar.edu.utn.frba.dds.serviceLocator.ServiceLocator;
import ar.edu.utn.frba.dds.services.AlertasService;
import java.io.IOException;
import java.util.List;
import java.util.TimeZone;

public class FallaConexionCronJob {
  public static void main(String[] args) {
    TimeZone.setDefault(TimeZone.getTimeZone("America/Argentina/Buenos_Aires"));

    int limite_minutos;
    IHeladerasRepository heladerasRepository = ServiceLocator.get(IHeladerasRepository.class);
    AlertasService alertasService = ServiceLocator.get(AlertasService.class);
    List<Heladera> heladeras = heladerasRepository.buscarTodos();
    try {
      limite_minutos = Integer.parseInt(new ConfigReader("config.properties").getProperty("MINUTOS_TOLERANCIA_CONEXION"));
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    VerificadorConexionHeladera verifcador = new VerificadorConexionHeladera();
    for (Heladera h : heladeras) {

      if (verifcador.huboFallaConexion(h, limite_minutos)) { // Agregar desde el archivo de config (ya esta)
        Alerta alerta = Alerta.of(h, new TecnicosHelper(ServiceLocator.get(ITecnicosRepository.class))
            , new NotificationStrategyFactory(), TipoAlerta.FALLA_CONEXION);
        alertasService.reportarYGuardarSiNoEstabaElMismoProblema(alerta, h);
      }
    }
  }
}

//A partir de la 18, clase verificador falla conexion. verificarfallaconexion(), con esos metodos puedo testearlos TOP.
