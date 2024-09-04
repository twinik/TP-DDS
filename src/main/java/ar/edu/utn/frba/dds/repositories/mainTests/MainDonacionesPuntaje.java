package ar.edu.utn.frba.dds.repositories.mainTests;

import ar.edu.utn.frba.dds.domain.colaboraciones.DonacionVianda;
import ar.edu.utn.frba.dds.domain.colaboraciones.calculadores.CalculadorPuntos;
import ar.edu.utn.frba.dds.domain.colaboradores.Colaborador;
import ar.edu.utn.frba.dds.domain.colaboradores.Usuario;
import ar.edu.utn.frba.dds.domain.heladeras.Vianda;
import ar.edu.utn.frba.dds.repositories.IColaboradoresRepository;
import ar.edu.utn.frba.dds.repositories.IDonacionesViandaRepository;
import ar.edu.utn.frba.dds.serviceLocator.ServiceLocator;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MainDonacionesPuntaje {
  public static void main(String[] args) {

    IColaboradoresRepository repoColab = ServiceLocator.get("colaboradoresRepository", IColaboradoresRepository.class);
    IDonacionesViandaRepository repoDonacionesVianda = ServiceLocator.get("donacionesViandaRepository", IDonacionesViandaRepository.class);

    Colaborador c = new Colaborador();
    c.setUsuario(new Usuario("email@email.com", "clave"));
    repoColab.guardar(c);

    for (int i = 0; i < 10; i++) {
      Vianda v = new Vianda();
      v.setColaborador(c);
      DonacionVianda d = new DonacionVianda();
      d.setFecha(LocalDate.now());
      d.setVianda(v);
      d.setColaborador(c);
      repoDonacionesVianda.guardar(d);
    }

    List<DonacionVianda> donaciones = repoDonacionesVianda.buscarPorColaborador(c);

    for (DonacionVianda d : donaciones) {
      ServiceLocator.get("calculadorPuntos", CalculadorPuntos.class).sumarPuntosPara(c, d);
    }

    repoColab.actualizar(c);
  }
}
