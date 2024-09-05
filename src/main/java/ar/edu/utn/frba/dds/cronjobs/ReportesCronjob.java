package ar.edu.utn.frba.dds.cronjobs;


import static ar.edu.utn.frba.dds.domain.reportes.TipoReporte.FALLAS_HELADERA;
import static ar.edu.utn.frba.dds.domain.reportes.TipoReporte.VIANDA_X_COLAB;
import static ar.edu.utn.frba.dds.domain.reportes.TipoReporte.VIANDA_X_HELADERA;

import ar.edu.utn.frba.dds.domain.colaboradores.Colaborador;
import ar.edu.utn.frba.dds.domain.colaboradores.Usuario;
import ar.edu.utn.frba.dds.domain.heladeras.Heladera;
import ar.edu.utn.frba.dds.domain.heladeras.Vianda;
import ar.edu.utn.frba.dds.domain.reportes.Reporte;
import ar.edu.utn.frba.dds.domain.reportes.ReportesFactory;
import ar.edu.utn.frba.dds.repositories.*;
import ar.edu.utn.frba.dds.serviceLocator.ServiceLocator;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * genera los reportes semanales con estadisticas
 * PARA QUE FUNCIONE BIEN HAY QUE CORRERLO TODOS LOS SABADOS A ULTIMA HORA
 */
public class ReportesCronjob {
    public static void main(String[] args) {

//
//        Colaborador c1 = new Colaborador();
//        c1.setUsuario(new Usuario("fdkfkdf","fdifndk"));
//        c1.setNombre("jorge");
//        c1.setApellido("luca");
//
//        Colaborador c2 = new Colaborador();
//        c2.setUsuario(new Usuario("ss","fdf"));
//        c2.setNombre("juan");
//        c2.setApellido("lopez");
//
//        Colaborador c3 = new Colaborador();
//        c3.setUsuario(new Usuario("s","ahgu"));
//        c3.setNombre("otro");
//        c3.setApellido("tipo");
//
//        IColaboradoresRepository colab = ServiceLocator.get("colaboradoresRepository",IColaboradoresRepository.class);
//        IHeladerasRepository heladeras = ServiceLocator.get("heladerasRepository", IHeladerasRepository.class);
//        IViandasRepository viandas = ServiceLocator.get("viandasRepository",IViandasRepository.class);
//
//        colab.guardar(c1);
//        colab.guardar(c2);
//        colab.guardar(c3);
//
//        Heladera h = new Heladera();
//        h.setNombre("heladeraThompson");
//        heladeras.guardar(h);
//
//        Vianda v1 = new Vianda("milanesa", LocalDate.now(), LocalDate.now(), c1, h, 150, 200f, true);
//        Vianda v2 = new Vianda("papas", LocalDate.now(), LocalDate.now(), c1, h, 150, 200f, true);
//        Vianda v3 = new Vianda("queso", LocalDate.now(), LocalDate.now(), c2, h, 150, 200f, true);
//        Vianda v4 = new Vianda("alberto", LocalDate.now(), LocalDate.now(), c1, h, 150, 200f, true);
//        Vianda v5= new Vianda("salchichas", LocalDate.now(), LocalDate.now(), c3, h, 150, 200f, true);
//
//        viandas.guardar(v1);
//        viandas.guardar(v2);
//        viandas.guardar(v3);
//        viandas.guardar(v4);
//        viandas.guardar(v5);
//
//        h.agregarVianda(v1);
//        h.agregarVianda(v2);
//        h.agregarVianda(v3);
//        h.agregarVianda(v4);
//        h.agregarVianda(v5);


        ReportesFactory reportesFactory = ServiceLocator.get("reportesFactory", ReportesFactory.class);
        IReportesRepository repository = ServiceLocator.get("reportesRepository", IReportesRepository.class);
        List<Reporte> reportes = new ArrayList<>();
        reportes.add(reportesFactory.create(VIANDA_X_COLAB, LocalDate.now()));
        reportes.add(reportesFactory.create(VIANDA_X_HELADERA, LocalDate.now()));
        reportes.add(reportesFactory.create(FALLAS_HELADERA, LocalDate.now()));
        reportes.forEach(Reporte::generarPDF);

        repository.guardar(reportes);

    }
}
