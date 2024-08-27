package ar.edu.utn.frba.dds.repositories.imp;


import ar.edu.utn.frba.dds.domain.colaboraciones.utils.MotivoRedistribucionVianda;
import ar.edu.utn.frba.dds.domain.colaboradores.Usuario;
import ar.edu.utn.frba.dds.repositories.IUsuariosRepository;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;

import java.util.List;
import java.util.Optional;

/**
 * IAlertasRepository interface permite interactuar con las alertas.
 */
public class UsuariosRepository implements IUsuariosRepository, WithSimplePersistenceUnit {

  @Override
  public Optional<Usuario> buscar(long id) {
    return Optional.ofNullable(entityManager().find(Usuario.class,id));
  }

  @Override
  public List<Usuario> buscarTodos() {
    return entityManager().createQuery("from Usuario where activo=:activo",Usuario.class).
            setParameter("activo",true)
            .getResultList();
  }

  @Override
  public void guardar(Usuario usuario) {
    withTransaction(() -> entityManager().persist(usuario));
  }
  public void guardar(Usuario ...usuario) {

    withTransaction(() -> {
      for (Usuario user : usuario){
        entityManager().persist(user);
      }
    });
  }

  @Override
  public void actualizar(Usuario usuario) {
    withTransaction(() -> entityManager().merge(usuario));
  }

  @Override
  public void eliminar(Usuario usuario) {
    usuario.setActivo(false);
    withTransaction(() -> entityManager().merge(usuario));
  }

  /* public static void main(String[] args) {
        Usuario m = new Usuario("otro");
        Usuario m1 = new Usuario("uno");
        Usuario m2 = new Usuario("hola");
        IUsuariosRepository repositorio = (IUsuariosRepository) ServiceLocator.get("usuariosRepository");
        repositorio.guardar(m);
        repositorio.guardar(m1);
        repositorio.guardar(m2);

        repositorio.eliminar(m1);
        m2.setMotivo("lo cambio");
        m2.setUpdated_at(LocalDateTime.of(2023,1,13,1,3));
      repositorio.actualizar(m2);

        Optional<Usuario> usuario1 = repositorio.buscar(1L);
        //System.out.println(hidratado.get().getMotivo());
        Optional<Usuario> usuario2 = repositorio.buscar(2L);

        List<Usuario> lista = repositorio.buscarTodos();

    }*/
}
