package ar.edu.utn.frba.dds.services;

import ar.edu.utn.frba.dds.dtos.heladeras.HeladeraInputDto;
import ar.edu.utn.frba.dds.exceptions.FormIncompletoException;
import ar.edu.utn.frba.dds.helpers.DateHelper;
import ar.edu.utn.frba.dds.models.domain.colaboraciones.ColocacionHeladeras;
import ar.edu.utn.frba.dds.models.domain.colaboradores.Colaborador;
import ar.edu.utn.frba.dds.models.domain.heladeras.CalculadorHeladerasCercanas;
import ar.edu.utn.frba.dds.models.domain.heladeras.Heladera;
import ar.edu.utn.frba.dds.models.domain.utils.Direccion;
import ar.edu.utn.frba.dds.models.domain.utils.Ubicacion;
import ar.edu.utn.frba.dds.models.repositories.IColocacionHeladeraRepository;
import ar.edu.utn.frba.dds.models.repositories.IHeladerasRepository;
import lombok.AllArgsConstructor;
import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@AllArgsConstructor
public class ColocacionHeladerasService {
    private IColocacionHeladeraRepository colocacionHeladeraRepository;
    private ColaboradoresService colaboradoresService;
    private ModelosService modelosService;
    private CalculadorHeladerasCercanas calculadorHeladerasCercanas;
    private IHeladerasRepository heladerasRepository;

    // TODO: Herencia para las colaboraciones con colaboradoresService ??
    public void crearColocacionHeladera(HeladeraInputDto dto) {
        Colaborador c = this.colaboradoresService.obtenerColaborador(dto.getIdColaborador());

        if (!dto.estanCamposLlenos()) throw new FormIncompletoException();
        // TODO: por ahora creo la heladera aca, no se si es mas adecuado ponerlo en heladerasService
        ColocacionHeladeras colocacionHeladeras = new ColocacionHeladeras();
        colocacionHeladeras.setFecha(DateHelper.fechaFromString(dto.getFecha(), "dd/MM/yyyy"));
        if (colocacionHeladeras.getFecha().isAfter(LocalDate.now()))
            throw new FormIncompletoException("fecha invalida ingresada");
        colocacionHeladeras.setColaborador(c);
        Heladera heladera = new Heladera();
        heladera.setNombre(dto.getNombre());
        heladera.setFechaPuestaFuncionamiento(DateHelper.fechaFromString(dto.getFecha(), "dd/MM/yyyy"));
        heladera.setModelo(this.modelosService.obtenerModelo(dto.getIdModelo()));
        heladera.setUbicacion(new Ubicacion(dto.getUbicacion().getLatitud(), dto.getUbicacion().getLongitud()));
        heladera.setDireccion(new Direccion(dto.getDireccion().getCalle(), dto.getDireccion().getNumero(), dto.getDireccion().getPiso(), dto.getDireccion().getCodigoPostal()));

        colocacionHeladeras.setHeladera(heladera);
        c.agregarColocacionHeladera(colocacionHeladeras);

        heladera.setHeladerasCercanas(this.calculadorHeladerasCercanas.getHeladerasCercanasA(heladera));

        this.colocacionHeladeraRepository.guardar(colocacionHeladeras);


        // Le recalculo solo a las que podrian llegar a haber cambiado??? igual mas lento que no se que..

        updateHeladerasAsync(heladera, this.calculadorHeladerasCercanas, this.heladerasRepository);
    }

    private void updateHeladerasAsync(Heladera heladera, CalculadorHeladerasCercanas calculadorHeladerasCercanas, IHeladerasRepository heladerasRepository) {
        // Run the first part asynchronously
        List<CompletableFuture<Void>> futures = heladera.getHeladerasCercanas().stream()
                .map(h -> CompletableFuture.runAsync(() -> {
                    List<Heladera> heladerasCercanasA = calculadorHeladerasCercanas.getHeladerasCercanasA(h);
                    h.setHeladerasCercanas(heladerasCercanasA);
                }))
                .toList();

        // Once all individual tasks are completed, update the repository
        CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]))
                .thenRunAsync(() -> {
                    // Update repository asynchronously after all previous tasks are done
                    heladerasRepository.actualizar(heladera.getHeladerasCercanas());
                })
                .exceptionally(ex -> {
                    // Handle any exceptions here
                    ex.printStackTrace();
                    return null;
                });
    }
}


