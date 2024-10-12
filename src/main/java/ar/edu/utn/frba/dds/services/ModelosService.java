package ar.edu.utn.frba.dds.services;

import ar.edu.utn.frba.dds.dtos.heladeras.ModeloHeladeraInputDto;
import ar.edu.utn.frba.dds.dtos.heladeras.ModeloHeladeraOutputDto;
import ar.edu.utn.frba.dds.models.domain.heladeras.ModeloHeladera;
import ar.edu.utn.frba.dds.models.repositories.IModeloHeladeraRepository;
import lombok.AllArgsConstructor;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public class ModelosService {
    private IModeloHeladeraRepository modeloHeladeraRepository;

    public List<ModeloHeladeraOutputDto> obtenerModelosDisponibles() {
        return this.modeloHeladeraRepository.buscarTodos().stream().map(ModeloHeladeraOutputDto::fromModelo).toList();
    }

    public ModeloHeladera obtenerModelo(String id) {
        Optional<ModeloHeladera> modelo = this.modeloHeladeraRepository.buscar(id);
        if (modelo.isEmpty()) throw new RuntimeException("no se encontro un modelo con este id");
        return modelo.get();
    }

    public void crearModeloHeladera(ModeloHeladeraInputDto dto) {
        ModeloHeladera modeloHeladera = new ModeloHeladera(dto.getModelo(), dto.getTempMin(), dto.getTempMax());
        this.modeloHeladeraRepository.guardar(modeloHeladera);
    }
}
