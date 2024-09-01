package ar.edu.utn.frba.dds.repositories.imp;

import ar.edu.utn.frba.dds.domain.incidentes.Alerta;
import ar.edu.utn.frba.dds.domain.incidentes.FallaTecnica;
import ar.edu.utn.frba.dds.domain.incidentes.TipoAlerta;
import ar.edu.utn.frba.dds.helpers.DateHelper;
import ar.edu.utn.frba.dds.repositories.IAlertasRepository;
import ar.edu.utn.frba.dds.serviceLocator.ServiceLocator;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@NoArgsConstructor
public class AlertasRepository implements IAlertasRepository, WithSimplePersistenceUnit {

  @Override
  public Optional<Alerta> buscar(Long id) {
    return Optional.ofNullable(entityManager().find(Alerta.class, id));
  }

  @Override
  public List<Alerta> buscarTodos() {
    return entityManager().createQuery("from Alerta where activo=:activo", Alerta.class)
        .setParameter("activo", true)
        .getResultList();
  }

  @Override
  public List<Alerta> buscarTodosMismaSemana(LocalDate fecha) {
    LocalDateTime principioDeSemana = DateHelper.principioDeSemana(fecha.atStartOfDay());
    LocalDateTime finDeSemana = DateHelper.finDeSemana(fecha.atStartOfDay());
    return entityManager().createQuery("from Alerta where activo=:activo and timestamp between " +
            ":principioSemana and :finSemana and solucionado=:solucionado", Alerta.class).
        setParameter("activo", true)
        .setParameter("solucionado",false)
        .setParameter("principioSemana", principioDeSemana)
        .setParameter("finSemana", finDeSemana)
        .getResultList();
  }

  @Override
  public void guardar(Alerta alerta) {
    withTransaction(() -> entityManager().persist(alerta));
  }

  @Override
  public void actualizar(Alerta alerta) {
    withTransaction(() -> entityManager().merge(alerta));
  }

  @Override
  public void eliminar(Alerta alerta) {
    alerta.borrarLogico();
    withTransaction(() -> entityManager().merge(alerta));
  }

  public static void main(String[] args) {

    Alerta m = new Alerta(TipoAlerta.TEMPERATURA);
    Alerta m1 = new Alerta(TipoAlerta.FRAUDE);
    Alerta m2 = new Alerta(TipoAlerta.FALLA_CONEXION);
    IAlertasRepository repositorio = ServiceLocator.get("alertasRepository", IAlertasRepository.class);
    repositorio.guardar(m);
    repositorio.guardar(m1);
    repositorio.guardar(m2);

    repositorio.eliminar(m1);


    Optional<Alerta> alerta1 = repositorio.buscar(1L); //Alerta no tiene ID, ver que se hace
    //System.out.println(hidratado.get().getMotivo());
    Optional<Alerta> alerta2 = repositorio.buscar(2L); //Alerta no tiene ID, ver que se hace

    List<Alerta> lista = repositorio.buscarTodos();

  }

}