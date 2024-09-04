package ar.edu.utn.frba.dds.repositories.imp;

import ar.edu.utn.frba.dds.domain.colaboraciones.DonacionVianda;
import ar.edu.utn.frba.dds.domain.colaboradores.Colaborador;
import ar.edu.utn.frba.dds.domain.colaboradores.Usuario;
import ar.edu.utn.frba.dds.domain.heladeras.Heladera;
import ar.edu.utn.frba.dds.domain.incidentes.FallaTecnica;
import ar.edu.utn.frba.dds.domain.notifications.NotificationStrategyFactory;
import ar.edu.utn.frba.dds.helpers.DateHelper;
import ar.edu.utn.frba.dds.helpers.TecnicosHelper;
import ar.edu.utn.frba.dds.repositories.IFallasTecnicasRepository;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@NoArgsConstructor

public class FallasTecnicasRepository implements IFallasTecnicasRepository, WithSimplePersistenceUnit {


  @Override
  public Optional<FallaTecnica> buscar(Long id) {
    return Optional.ofNullable(entityManager().find(FallaTecnica.class, id));
  }

  @Override
  public List<FallaTecnica> buscarTodos() {
    return entityManager().createQuery("from FallaTecnica where activo=:activo", FallaTecnica.class)
        .setParameter("activo", true)
        .getResultList();
  }

  @Override
  public List<FallaTecnica> buscarPorHeladera(Heladera heladera) {
    return entityManager().createQuery("from FallaTecnica where activo=:activo and heladera=:heladera and solucionado=:solucionado", FallaTecnica.class)
        .setParameter("activo", true)
        .setParameter("heladera", heladera)
        .setParameter("solucionado", false)
        .getResultList();
  }

  @Override
  public List<FallaTecnica> buscarTodosMismaSemana(LocalDate fecha) {
    LocalDateTime principioDeSemana = DateHelper.principioDeSemana(fecha.atStartOfDay());
    LocalDateTime finDeSemana = DateHelper.finDeSemana(fecha.atStartOfDay());
    return entityManager().createQuery("from FallaTecnica where activo=:activo and timestamp between " +
            ":principioSemana and :finSemana and solucionado=:solucionado", FallaTecnica.class).
        setParameter("activo",true)
        .setParameter("solucionado",false)
        .setParameter("principioSemana",principioDeSemana)
        .setParameter("finSemana",finDeSemana)
        .getResultList();
  }

  @Override
  public void guardar(FallaTecnica fallaTecnica) {
    withTransaction(() -> entityManager().persist(fallaTecnica));
  }

  @Override
  public void actualizar(FallaTecnica fallaTecnica) {
    withTransaction(() -> entityManager().merge(fallaTecnica));
  }

  @Override
  public void eliminar(FallaTecnica fallaTecnica) {
    fallaTecnica.borrarLogico();
    withTransaction(() -> entityManager().merge(fallaTecnica));
  }

  public static void main(String[] args) {
    Colaborador c = new Colaborador();
    c.setUsuario(new Usuario("123","123"));
    FallaTecnica f1 = new FallaTecnica(new Heladera(), LocalDateTime.now(),new TecnicosHelper(new TecnicosRepository()),
        new NotificationStrategyFactory(),c,"hola","rerer");

    FallasTecnicasRepository repo = new FallasTecnicasRepository();
    repo.guardar(f1);

    Optional<FallaTecnica> f3 = repo.buscar(1L);

  }
}
