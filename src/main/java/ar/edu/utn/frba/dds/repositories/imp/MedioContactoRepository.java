package ar.edu.utn.frba.dds.repositories.imp;

import ar.edu.utn.frba.dds.domain.tecnicos.VisitaTecnico;
import ar.edu.utn.frba.dds.domain.utils.MedioDeContacto;
import ar.edu.utn.frba.dds.repositories.IMedioContactoRepository;
import ar.edu.utn.frba.dds.repositories.IVisitasTecnicoRepository;
import java.util.List;
import java.util.Optional;

public class MedioContactoRepository implements IMedioContactoRepository {
  @Override
  public Optional<MedioDeContacto> buscar(Long id) {
    return Optional.empty();
  }

  @Override
  public List<MedioDeContacto> buscarTodos() {
    return List.of();
  }

  @Override
  public void guardar(MedioDeContacto visitaTecnico) {
  }

  @Override
  public void actualizar(MedioDeContacto visitaTecnico) {
  }

  @Override
  public void eliminar(MedioDeContacto visitaTecnico) {
  }
}
