package ar.edu.utn.frba.dds.domain.heladeras;

import ar.edu.utn.frba.dds.domain.colaboradores.Colaborador;
import ar.edu.utn.frba.dds.domain.excepciones.NoAutorizadoParaAbrirHeladeraException;
import ar.edu.utn.frba.dds.domain.helpers.ConfigReader;
import ar.edu.utn.frba.dds.domain.helpers.DateHelper;
import ar.edu.utn.frba.dds.domain.tarjetas.TarjetaColaborador;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.Optional;

/**
 * Clase que representa la apertura de de una heladera
 */
@Getter
@Setter
@AllArgsConstructor
public class AperturaHeladera {
    private SolicitudAperturaHeladera solicitud;
    private LocalDateTime timestamp;
    private Heladera heladera;

    public static AperturaHeladera from(Heladera heladera, TarjetaColaborador tarjeta, ConfigReader configReader) throws IOException {
        Optional<SolicitudAperturaHeladera> solicitudAperturaHeladera = heladera.getSolicitudesApertura().stream().
            filter(s -> s.getColaborador().equals(tarjeta.getColaborador())).
            max(Comparator.comparing(SolicitudAperturaHeladera::getTimestamp));

        if(solicitudAperturaHeladera.isEmpty())
            throw new NoAutorizadoParaAbrirHeladeraException("colaborador no autorizado para abrir la heladera");

        if(DateHelper.horasEntre(LocalDateTime.now(), solicitudAperturaHeladera.get().getTimestamp()) > Integer.parseInt(configReader.getProperty("LIMITE_HORAS_APERTURA_HELADERA")))
            throw new NoAutorizadoParaAbrirHeladeraException("colaborador no autorizado para abrir la heladera");

        return new AperturaHeladera(solicitudAperturaHeladera.get(),LocalDateTime.now(),heladera);
    }

}