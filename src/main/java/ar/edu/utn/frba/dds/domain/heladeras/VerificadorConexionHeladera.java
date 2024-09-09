package ar.edu.utn.frba.dds.domain.heladeras;


import ar.edu.utn.frba.dds.helpers.DateHelper;
import lombok.AllArgsConstructor;
import java.time.LocalDateTime;

@AllArgsConstructor
public class VerificadorConexionHeladera {
    public boolean huboFallaConexion(Heladera heladera, int minutosUltimaConexion) {
        RegistroTemperatura ultimoRegistro = heladera.getRegistroTemperaturas().get(heladera.getRegistroTemperaturas().size() - 1);
        return DateHelper.minutosEntre(ultimoRegistro.getFechaHora(), LocalDateTime.now()) > minutosUltimaConexion;
    }

}
