package ar.edu.utn.frba.dds.services;

import ar.edu.utn.frba.dds.dtos.reportes.ReporteDto;
import ar.edu.utn.frba.dds.exceptions.RecursoInexistenteException;
import ar.edu.utn.frba.dds.models.domain.reportes.Reporte;
import ar.edu.utn.frba.dds.models.domain.reportes.ReporteFallasHeladera;
import ar.edu.utn.frba.dds.models.domain.reportes.ReporteViandasPorColaborador;
import ar.edu.utn.frba.dds.models.domain.reportes.ReporteViandasPorHeladera;
import ar.edu.utn.frba.dds.models.messageFactory.MensajeRecursoInexistenteFactory;
import ar.edu.utn.frba.dds.models.repositories.IReportesRepository;
import lombok.AllArgsConstructor;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@AllArgsConstructor
public class ReportesService {
    private IReportesRepository repo;

    public List<ReporteDto> obtenerReportes(LocalDate desde, LocalDate hasta) {
        List<Reporte> reportes = this.repo.buscarEntreFechas(desde, hasta);
        List<ReporteDto> dtos = reportes.stream().map(r -> {
            ReporteDto dto = new ReporteDto();
            dto.setFecha(r.getCreated_at().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
            dto.setArchivo(r.getRutaArchivo());
            dto.setTipo(r.getTipo());
            return dto;
        }).toList();
        return dtos;
    }

//    private String getTipoReporte(Reporte r) {
//        if(r instanceof ReporteViandasPorHeladera) return "Viandas por heladera";
//        if(r instanceof ReporteViandasPorColaborador) return "Viandas por colaborador";
//        if(r instanceof ReporteFallasHeladera) return "Fallas por heladera";
//        throw new RecursoInexistenteException(MensajeRecursoInexistenteFactory.generarMensaje("Reporte"));
////        Class clase = r.getClass();
////        if (clase.equals(ReporteViandasPorHeladera.class)) return "Viandas por heladera";
////        else if (clase.equals(ReporteViandasPorColaborador.class)) return "Viandas por colaborador";
////        else if (clase.equals(ReporteFallasHeladera.class)) return "Fallas por heladera";
////        else {
////            throw new RecursoInexistenteException(MensajeRecursoInexistenteFactory.generarMensaje("Reporte"));
////        }
//    }
}
