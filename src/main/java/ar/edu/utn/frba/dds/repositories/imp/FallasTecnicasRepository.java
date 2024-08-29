package ar.edu.utn.frba.dds.repositories.imp;

import ar.edu.utn.frba.dds.domain.colaboradores.Colaborador;
import ar.edu.utn.frba.dds.domain.colaboradores.Usuario;
import ar.edu.utn.frba.dds.domain.heladeras.Heladera;
import ar.edu.utn.frba.dds.domain.incidentes.FallaTecnica;
import ar.edu.utn.frba.dds.domain.notifications.NotificationStrategyFactory;
import ar.edu.utn.frba.dds.helpers.TecnicosHelper;
import ar.edu.utn.frba.dds.repositories.IFallasTecnicasRepository;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;
import lombok.NoArgsConstructor;
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
  public void guardar(FallaTecnica fallaTecnica) {
    withTransaction(() -> entityManager().persist(fallaTecnica));
  }

  @Override
  public void actualizar(FallaTecnica fallaTecnica) {
    withTransaction(() -> entityManager().merge(fallaTecnica));
  }

  @Override
  public void eliminar(FallaTecnica fallaTecnica) {
    fallaTecnica.setActivo(false);
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
