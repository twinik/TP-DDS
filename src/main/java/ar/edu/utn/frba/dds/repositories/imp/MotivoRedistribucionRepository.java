package ar.edu.utn.frba.dds.repositories.imp;

import ar.edu.utn.frba.dds.domain.colaboraciones.utils.MotivoRedistribucionVianda;
import ar.edu.utn.frba.dds.repositories.IMotivoRedistribucionRepository;
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
        return motivosRedistribucion.stream().filter(r -> r.getId() == id).findFirst();
    }

    @Override
    public List<MotivoRedistribucionVianda> buscarTodos() {
        return this.motivosRedistribucion;
    }

    @Override
    public void guardar(MotivoRedistribucionVianda motivosRedistribucion) {
        this.motivosRedistribucion.add(motivosRedistribucion);
    }

    @Override
    public void actualizar(MotivoRedistribucionVianda motivo) {

    }

    @Override
    public void eliminar(MotivoRedistribucionVianda motivo) {

    }

}
