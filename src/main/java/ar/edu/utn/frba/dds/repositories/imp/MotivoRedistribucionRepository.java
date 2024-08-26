package ar.edu.utn.frba.dds.repositories.imp;

import ar.edu.utn.frba.dds.db.EntityManagerHelper;
import ar.edu.utn.frba.dds.domain.colaboraciones.utils.MotivoRedistribucionVianda;
import ar.edu.utn.frba.dds.repositories.IMotivoRedistribucionRepository;
import ar.edu.utn.frba.dds.serviceLocator.ServiceLocator;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MotivoRedistribucionRepository implements IMotivoRedistribucionRepository {

    private List<MotivoRedistribucionVianda> motivosRedistribucion;

    public MotivoRedistribucionRepository() {
        this.motivosRedistribucion = new ArrayList<>();
    }

    @Override
    public Optional<MotivoRedistribucionVianda> buscar(Long id) {
//        Query query = EntityManagerHelper.getEntityManager().
//            // ACA VA EL NOMBRE DE LA CLASE NO DE LA TABLA
//            createQuery("from MotivoRedistribucionVianda where id=:idParam");
//        query.setParameter("idParam",id);
//        try{
//            MotivoRedistribucionVianda resultado = (MotivoRedistribucionVianda) query.getSingleResult();
//            return Optional.ofNullable(resultado);
//        }
//        catch (NoResultException e){
//            return Optional.empty();
       // }
        return Optional.ofNullable(EntityManagerHelper.getEntityManager().find(MotivoRedistribucionVianda.class, id));
    }

    @Override
    public List<MotivoRedistribucionVianda> buscarTodos() {
        return EntityManagerHelper.getEntityManager().createQuery("from MotivoRedistribucionVianda",MotivoRedistribucionVianda.class).getResultList();
    }

    @Override
    public void guardar(MotivoRedistribucionVianda motivosRedistribucion) {
        EntityManagerHelper.beginTransaction();
        EntityManagerHelper.getEntityManager().persist(motivosRedistribucion);
        EntityManagerHelper.commit();
    }

    @Override
    public void actualizar(MotivoRedistribucionVianda motivo) {
        EntityManagerHelper.beginTransaction();
        EntityManagerHelper.getEntityManager().merge(motivo);
        EntityManagerHelper.commit();
    }

    @Override
    public void eliminar(MotivoRedistribucionVianda motivo) {
        // ponele
        EntityManagerHelper.beginTransaction();
        EntityManager em = EntityManagerHelper.getEntityManager();
        em.remove(
            em.contains(motivo) ? motivo : em.merge(motivo));
        EntityManagerHelper.commit();
    }

    public static void main(String[] args) {
        MotivoRedistribucionVianda m = new MotivoRedistribucionVianda("otro");
        MotivoRedistribucionVianda m1 = new MotivoRedistribucionVianda("uno");
        MotivoRedistribucionVianda m2 = new MotivoRedistribucionVianda("hola");
        IMotivoRedistribucionRepository repositorio = (IMotivoRedistribucionRepository) ServiceLocator.get("motivosRedistribucionRepository");
        repositorio.guardar(m);
        repositorio.guardar(m1);
        repositorio.guardar(m2);

        repositorio.eliminar(m1);
        m2.setMotivo("lo cambio");
        repositorio.actualizar(m2);

        Optional<MotivoRedistribucionVianda> hidratado = repositorio.buscar(1L);
        //System.out.println(hidratado.get().getMotivo());
        Optional<MotivoRedistribucionVianda> hidratado2 = repositorio.buscar(2L);

        List<MotivoRedistribucionVianda> lista = repositorio.buscarTodos();

    }

}
