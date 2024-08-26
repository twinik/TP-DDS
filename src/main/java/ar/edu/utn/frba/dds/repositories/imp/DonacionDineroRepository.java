package ar.edu.utn.frba.dds.repositories.imp;

import ar.edu.utn.frba.dds.domain.colaboraciones.DonacionDinero;
import ar.edu.utn.frba.dds.domain.tecnicos.AreaDeCobertura;
import ar.edu.utn.frba.dds.repositories.IDonacionDineroRepository;

import java.util.List;
import java.util.Optional;

public class DonacionDineroRepository implements IDonacionDineroRepository {
    @Override
    public Optional<DonacionDinero> buscar(long id) {
        return Optional.empty();
    }

    @Override
    public List<DonacionDinero> buscarTodos() {
        return List.of();
    }

    @Override
    public void guardar(DonacionDinero donacionDinero) {
    }

    @Override
    public void actualizar(DonacionDinero donacionDinero) {
    }

    @Override
    public void eliminar(DonacionDinero donacionDinero) {
    }
}
