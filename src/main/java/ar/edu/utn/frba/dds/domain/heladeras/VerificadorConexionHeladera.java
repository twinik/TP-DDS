package ar.edu.utn.frba.dds.domain.heladeras;


import ar.edu.utn.frba.dds.helpers.DateHelper;
import lombok.AllArgsConstructor;
import java.time.LocalDateTime;

@AllArgsConstructor
public class VerificadorConexionHeladera {
  private Heladera heladera;
  private int minutosUltimaConexion;

  public boolean huboFallaConexion(){
    RegistroTemperatura ultimoRegistro = heladera.getRegistroTemperaturas().get(heladera.getRegistroTemperaturas().size()-1);
    return DateHelper.minutosEntre(ultimoRegistro.getFechaHora(), LocalDateTime.now()) > minutosUltimaConexion;
  }

}
