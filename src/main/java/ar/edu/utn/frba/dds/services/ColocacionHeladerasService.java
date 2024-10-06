package ar.edu.utn.frba.dds.services;

import ar.edu.utn.frba.dds.dtos.heladeras.HeladeraInputDto;
import ar.edu.utn.frba.dds.exceptions.FormIncompletoException;
import ar.edu.utn.frba.dds.exceptions.NoAutorizadoException;
import ar.edu.utn.frba.dds.models.domain.colaboraciones.ColocacionHeladeras;
import ar.edu.utn.frba.dds.models.domain.colaboradores.Colaborador;
import ar.edu.utn.frba.dds.models.domain.heladeras.Heladera;
import ar.edu.utn.frba.dds.models.domain.utils.Direccion;
import ar.edu.utn.frba.dds.models.domain.utils.Ubicacion;
import ar.edu.utn.frba.dds.models.repositories.IColocacionHeladeraRepository;
import lombok.AllArgsConstructor;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@AllArgsConstructor
public class ColocacionHeladerasService {
  private IColocacionHeladeraRepository colocacionHeladeraRepository;
  private ColaboradoresService colaboradoresService;
  private ModelosService modelosService;

  // TODO: Herencia para las colaboraciones con colaboradoresService ??
  public void crearColocacionHeladera(HeladeraInputDto dto){
    Optional<Colaborador> c = this.colaboradoresService.obtenerColaborador(dto.getIdColaborador());

    if (c.isEmpty()) throw new NoAutorizadoException("no hay colaborador asociado a este id");

    if(!dto.estanCamposLlenos()) throw new FormIncompletoException();
    // TODO: por ahora creo la heladera aca, no se si es mas adecuado ponerlo en heladerasService
    ColocacionHeladeras colocacionHeladeras = new ColocacionHeladeras();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    colocacionHeladeras.setFecha(LocalDate.parse(dto.getFecha(),formatter));
    colocacionHeladeras.setColaborador(c.get());
    Heladera heladera = new Heladera();
    heladera.setNombre(dto.getNombre());
    heladera.setFechaPuestaFuncionamiento(LocalDate.parse(dto.getFecha(),formatter));
    heladera.setModelo(this.modelosService.obtenerModelo(dto.getIdModelo()));
    heladera.setUbicacion(new Ubicacion(dto.getUbicacion().getLatitud(),dto.getUbicacion().getLongitud()));
    heladera.setDireccion(new Direccion(dto.getDireccion().getCalle(),dto.getDireccion().getNumero(),dto.getDireccion().getPiso(),dto.getDireccion().getCodigoPostal()));

    colocacionHeladeras.setHeladera(heladera);
    c.get().agregarColocacionHeladera(colocacionHeladeras);

    this.colocacionHeladeraRepository.guardar(colocacionHeladeras);

    // TODO: falta calcular y recalcular heladeras cercanas

  }
}
