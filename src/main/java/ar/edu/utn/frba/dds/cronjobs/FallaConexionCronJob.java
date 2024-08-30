package ar.edu.utn.frba.dds.cronjobs;

import ar.edu.utn.frba.dds.domain.heladeras.Heladera;
import ar.edu.utn.frba.dds.domain.heladeras.VerificadorConexionHeladera;
import ar.edu.utn.frba.dds.domain.incidentes.Alerta;
import ar.edu.utn.frba.dds.domain.incidentes.TipoAlerta;
import ar.edu.utn.frba.dds.domain.notifications.NotificationStrategyFactory;
import ar.edu.utn.frba.dds.helpers.ConfigReader;
import ar.edu.utn.frba.dds.helpers.TecnicosHelper;
import ar.edu.utn.frba.dds.repositories.IAlertasRepository;
import ar.edu.utn.frba.dds.repositories.ITecnicosRepository;
import ar.edu.utn.frba.dds.serviceLocator.ServiceLocator;
import ar.edu.utn.frba.dds.repositories.IHeladerasRepository;
import java.io.IOException;
import java.util.List;

public class FallaConexionCronJob {
  public static void main(String[] args) {
    int limite_minutos;
    IHeladerasRepository heladerasRepository = ServiceLocator.get("heladerasRepository", IHeladerasRepository.class);
    IAlertasRepository alertasRepository = ServiceLocator.get("alertasRepository", IAlertasRepository.class);
    List<Heladera> heladeras = heladerasRepository.buscarTodos();
    try {
      limite_minutos = Integer.parseInt(new ConfigReader("config.properties").getProperty("MINUTOS_TOLERANCIA_CONEXION"));
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    VerificadorConexionHeladera verifcador = new VerificadorConexionHeladera();
    for (Heladera h : heladeras) {

      if (verifcador.huboFallaConexion(h, limite_minutos)) { // Agregar desde el archivo de config (ya esta)
        Alerta alerta = Alerta.of(h, new TecnicosHelper(ServiceLocator.get("tecnicosRepository",ITecnicosRepository.class))
            ,new NotificationStrategyFactory(), TipoAlerta.FALLA_CONEXION);
        alerta.reportar();
        alertasRepository.guardar(alerta);
      }
    }
  }
}

//A partir de la 18, clase verificador falla conexion. verificarfallaconexion(), con esos metodos puedo testearlos TOP.
