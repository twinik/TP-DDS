package ar.edu.utn.frba.dds.repositories.imp;

import ar.edu.utn.frba.dds.domain.colaboradores.form.Formulario;
import ar.edu.utn.frba.dds.domain.tecnicos.AreaDeCobertura;

import java.util.List;
import java.util.Optional;

public class FormularioRepository implements  IFormularioRepository{
    @Override
    public Optional<Formulario> buscar(long id) {
        return Optional.empty();
    }

    @Override
    public List<Formulario> buscarTodos() {
        return List.of();
    }

    @Override
    public void guardar(Formulario formulario) {
    }

    @Override
    public void actualizar(Formulario formulario) {
    }

    @Override
    public void eliminar(Formulario formulario) {
    }
}
