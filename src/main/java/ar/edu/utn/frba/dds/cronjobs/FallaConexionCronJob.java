package ar.edu.utn.frba.dds.cronjobs;

import ar.edu.utn.frba.dds.domain.heladeras.Heladera;
import ar.edu.utn.frba.dds.domain.heladeras.RegistroTemperatura;
import ar.edu.utn.frba.dds.domain.heladeras.VerificadorConexionHeladera;
import ar.edu.utn.frba.dds.domain.incidentes.Alerta;
import ar.edu.utn.frba.dds.domain.incidentes.TipoAlerta;
import ar.edu.utn.frba.dds.helpers.ConfigReader;
import ar.edu.utn.frba.dds.repositories.IAlertasRepository;
import ar.edu.utn.frba.dds.serviceLocator.ServiceLocator;
import ar.edu.utn.frba.dds.helpers.DateHelper;
import ar.edu.utn.frba.dds.repositories.IHeladerasRepository;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

public class FallaConexionCronJob {
  public static void main(String[] args) {
    int limite_minutos = 5;
    IHeladerasRepository heladerasRepository = (IHeladerasRepository) ServiceLocator.get("heladerasRepository");
    IAlertasRepository alertasRepository = (IAlertasRepository) ServiceLocator.get("alertasRepository");
    List<Heladera> heladeras = heladerasRepository.buscarTodos();
    try {
      limite_minutos = Integer.parseInt(new ConfigReader("config.properties").getProperty("MINUTOS_TOLERANCIA_CONEXION"));
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    for (Heladera h : heladeras) {
      VerificadorConexionHeladera verifcador = new VerificadorConexionHeladera(h,limite_minutos);
      if (verifcador.huboFallaConexion()) { // Agregar desde el archivo de config (ya esta)
        Alerta alerta =  Alerta.of(h, TipoAlerta.FALLA_CONEXION);
        alerta.reportar();
        alertasRepository.guardar(alerta);
      }
    }
  }
}

//A partir de la 18, clase verificador falla conexion. verificarfallaconexion(), con esos metodos puedo testearlos TOP.
