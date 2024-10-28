package ar.edu.utn.frba.dds.models.repositories.imp;

import ar.edu.utn.frba.dds.models.domain.colaboradores.form.Formulario;
import ar.edu.utn.frba.dds.models.repositories.IFormularioRepository;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;
import java.util.List;
import java.util.Optional;

public class FormularioRepository implements IFormularioRepository, WithSimplePersistenceUnit {
  @Override
  public Optional<Formulario> buscar(String id) {
    return Optional.ofNullable(entityManager().find(Formulario.class, id));
  }

  @Override
  public List<Formulario> buscarTodos() {
    return entityManager().createQuery("from Formulario where activo=:activo order by created_at", Formulario.class).
        setParameter("activo", true)
        .getResultList();
  }

  @Override
  public void guardar(Formulario formulario) {
    withTransaction(() -> entityManager().persist(formulario));
  }

  @Override
  public void actualizar(Formulario formulario) {
    withTransaction(() -> entityManager().merge(formulario));
  }

  @Override
  public void eliminar(Formulario formulario) {

    withTransaction(() -> {
      formulario.borrarLogico();
      entityManager().merge(formulario);
    });
  }

}
