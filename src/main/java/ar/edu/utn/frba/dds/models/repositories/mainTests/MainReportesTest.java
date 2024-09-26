package ar.edu.utn.frba.dds.models.repositories.mainTests;

import static ar.edu.utn.frba.dds.models.domain.reportes.TipoReporte.*;

import ar.edu.utn.frba.dds.models.domain.colaboraciones.DonacionVianda;
import ar.edu.utn.frba.dds.models.domain.colaboraciones.RedistribucionViandas;
import ar.edu.utn.frba.dds.models.domain.colaboraciones.utils.MotivoRedistribucionVianda;
import ar.edu.utn.frba.dds.models.domain.colaboradores.Colaborador;
import ar.edu.utn.frba.dds.models.domain.colaboradores.autenticacion.Usuario;
import ar.edu.utn.frba.dds.models.domain.heladeras.Heladera;
import ar.edu.utn.frba.dds.models.domain.heladeras.Vianda;
import ar.edu.utn.frba.dds.models.domain.incidentes.Alerta;
import ar.edu.utn.frba.dds.models.domain.incidentes.FallaTecnica;
import ar.edu.utn.frba.dds.models.domain.incidentes.TipoAlerta;
import ar.edu.utn.frba.dds.models.domain.reportes.Reporte;
import ar.edu.utn.frba.dds.models.domain.reportes.ReportesFactory;
import ar.edu.utn.frba.dds.models.repositories.*;
import ar.edu.utn.frba.dds.serviceLocator.ServiceLocator;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class MainReportesTest {
    public static void main(String[] args) {

        Colaborador c1 = new Colaborador();
        c1.setUsuario(new Usuario("fdkfkdf", "fdifndk"));
        c1.setNombre("jorge");
        c1.setApellido("luca");

        Colaborador c2 = new Colaborador();
        c2.setUsuario(new Usuario("ss", "fdf"));
        c2.setNombre("juan");
        c2.setApellido("lopez");

        Colaborador c3 = new Colaborador();
        c3.setUsuario(new Usuario("s", "ahgu"));
        c3.setNombre("otro");
        c3.setApellido("tipo");

        IColaboradoresRepository colab = ServiceLocator.get(IColaboradoresRepository.class);
        IHeladerasRepository heladeras = ServiceLocator.get(IHeladerasRepository.class);
        IViandasRepository viandas = ServiceLocator.get(IViandasRepository.class);
        IDonacionesViandaRepository donacionesRepo = ServiceLocator.get(IDonacionesViandaRepository.class);

        colab.guardar(c1);
        colab.guardar(c2);
        colab.guardar(c3);

        Heladera h = new Heladera();
        h.setNombre("heladeraThompson");
        heladeras.guardar(h);

        Heladera h2 = new Heladera();
        h2.setNombre("heladera secundaria");
        heladeras.guardar(h2);

        Vianda v1 = new Vianda("milanesa", LocalDate.now(), LocalDate.now(), c1, h, 150, 200f, true);
        Vianda v2 = new Vianda("papas", LocalDate.now(), LocalDate.now(), c1, h, 150, 200f, true);
        Vianda v3 = new Vianda("queso", LocalDate.now(), LocalDate.now(), c2, h, 150, 200f, true);
        Vianda v4 = new Vianda("alberto", LocalDate.now(), LocalDate.now(), c1, h, 150, 200f, true);
        Vianda v5 = new Vianda("salchichas", LocalDate.now(), LocalDate.now(), c3, h, 150, 200f, true);

        viandas.guardar(v1);
        viandas.guardar(v2);
        viandas.guardar(v3);
        viandas.guardar(v4);
        viandas.guardar(v5);

        donacionesRepo.guardar(new DonacionVianda(c1, LocalDate.now(), v1, h));
        donacionesRepo.guardar(new DonacionVianda(c1, LocalDate.now(), v2, h));
        donacionesRepo.guardar(new DonacionVianda(c1, LocalDate.now(), v3, h));
        donacionesRepo.guardar(new DonacionVianda(c1, LocalDate.now(), v4, h));
        donacionesRepo.guardar(new DonacionVianda(c1, LocalDate.now(), v5, h));

        RedistribucionViandas redistribucionViandas = new RedistribucionViandas(c1, LocalDate.now(), h, h2, new MotivoRedistribucionVianda("prueba"), 2);

        ServiceLocator.get(IRedistribucionesViandaRepository.class).guardar(redistribucionViandas);

        FallaTecnica falla1 = new FallaTecnica(c1, "falla 1", "foto");
        falla1.setHeladera(h);
        falla1.setTimestamp(LocalDateTime.now());
        FallaTecnica falla2 = new FallaTecnica(c1, "falla 2", "foto");
        falla2.setHeladera(h);
        falla2.setTimestamp(LocalDateTime.now());
        FallaTecnica falla3 = new FallaTecnica(c1, "falla 3", "foto");
        falla3.setHeladera(h2);
        falla3.setTimestamp(LocalDateTime.now());
        Alerta alerta1 = new Alerta(TipoAlerta.TEMPERATURA);
        alerta1.setHeladera(h2);
        alerta1.setTimestamp(LocalDateTime.now());

        ServiceLocator.get(IFallasTecnicasRepository.class).guardar(falla1);
        ServiceLocator.get(IFallasTecnicasRepository.class).guardar(falla2);
        ServiceLocator.get(IFallasTecnicasRepository.class).guardar(falla3);
        ServiceLocator.get(IAlertasRepository.class).guardar(alerta1);


        ReportesFactory reportesFactory = ServiceLocator.get(ReportesFactory.class);
        IReportesRepository repository = ServiceLocator.get(IReportesRepository.class);
        List<Reporte> reportes = new ArrayList<>();
        reportes.add(reportesFactory.create(VIANDA_X_COLAB, LocalDate.now()));
        reportes.add(reportesFactory.create(VIANDA_X_HELADERA, LocalDate.now()));
        reportes.add(reportesFactory.create(FALLAS_HELADERA, LocalDate.now()));
        reportes.forEach(Reporte::generarPDF);

        repository.guardar(reportes);

    }
}
