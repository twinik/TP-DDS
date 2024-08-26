package ar.edu.utn.frba.dds.repositories.imp;

import ar.edu.utn.frba.dds.domain.colaboraciones.AltaPersonaVulnerable;
import ar.edu.utn.frba.dds.domain.tecnicos.AreaDeCobertura;
import ar.edu.utn.frba.dds.repositories.IAltaPersonaVulnerableRepository;

import java.util.List;
import java.util.Optional;

public class AltaPersonaVulnerableRepository implements IAltaPersonaVulnerableRepository {
    @Override
    public Optional<AltaPersonaVulnerable> buscar(long id) {
        return Optional.empty();
    }

    @Override
    public List<AltaPersonaVulnerable> buscarTodos() {
        return List.of();
    }

    @Override
    public void guardar(AltaPersonaVulnerable altaPersonaVulnerable) {
    }

    @Override
    public void actualizar(AltaPersonaVulnerable altaPersonaVulnerable) {
    }

    @Override
    public void eliminar(AltaPersonaVulnerable altaPersonaVulnerable) {
    }
}
