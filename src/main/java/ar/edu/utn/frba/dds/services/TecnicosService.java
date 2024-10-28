package ar.edu.utn.frba.dds.services;

import ar.edu.utn.frba.dds.dtos.tecnicos.TecnicoDto;
import ar.edu.utn.frba.dds.dtos.tecnicos.TecnicoOutputDto;
import ar.edu.utn.frba.dds.dtos.tecnicos.VisitaTecnicoDto;
import ar.edu.utn.frba.dds.exceptions.DniDuplicadoException;
import ar.edu.utn.frba.dds.exceptions.FormIncompletoException;
import ar.edu.utn.frba.dds.exceptions.RecursoInexistenteException;
import ar.edu.utn.frba.dds.helpers.DniHelper;
import ar.edu.utn.frba.dds.models.domain.tecnicos.AreaDeCobertura;
import ar.edu.utn.frba.dds.models.domain.tecnicos.Tecnico;
import ar.edu.utn.frba.dds.models.domain.tecnicos.VisitaTecnico;
import ar.edu.utn.frba.dds.models.domain.utils.TipoDocumento;
import ar.edu.utn.frba.dds.models.domain.utils.TipoDocumentoMapper;
import ar.edu.utn.frba.dds.models.domain.utils.Ubicacion;
import ar.edu.utn.frba.dds.models.messageFactory.MensajeDniDuplicadoFactory;
import ar.edu.utn.frba.dds.models.messageFactory.MensajeDniInvalidoFactory;
import ar.edu.utn.frba.dds.models.messageFactory.MensajeFechaInvalidaFactory;
import ar.edu.utn.frba.dds.models.messageFactory.MensajeRecursoInexistenteFactory;
import ar.edu.utn.frba.dds.models.repositories.ITecnicosRepository;
import ar.edu.utn.frba.dds.models.repositories.IVisitasTecnicoRepository;
import ar.edu.utn.frba.dds.serviceLocator.ServiceLocator;
import lombok.AllArgsConstructor;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public class TecnicosService {
    private ITecnicosRepository tecnicosRepository;
    private MedioContactoService medioContactoService;
    private IVisitasTecnicoRepository visitasTecnicoRepository;
    private IncidentesService incidentesService;

    public void crearTecnico(TecnicoDto dto) {
        Tecnico tecnico = new Tecnico();
        tecnico.setNombre(dto.getNombre());
        tecnico.setApellido(dto.getApellido());
        tecnico.setTipoDocumento(ServiceLocator.get(TipoDocumentoMapper.class).obtenerTipoDeDocumento(dto.getTipoDocumento()));
        tecnico.setNroDocumento(dto.getNroDocumento());
        this.validarDocumento(tecnico.getTipoDocumento(), tecnico.getNroDocumento(), dto);
        tecnico.setAreaDeCobertura(new AreaDeCobertura(new Ubicacion(dto.getAreaCobertura().getLatitud(), dto.getAreaCobertura().getLongitud()), dto.getAreaCobertura().getRadio()));
        tecnico.agregarMedioContacto(this.medioContactoService.fromDtos(dto.getMedioContactoDtoList()));

        this.tecnicosRepository.guardar(tecnico);
    }

    public List<TecnicoOutputDto> obtenerTodos() {
        return this.tecnicosRepository.buscarTodos().stream().map(TecnicoOutputDto::fromTecnico).toList();
    }

    public void crearVisita(VisitaTecnicoDto dto) {
        VisitaTecnico visitaTecnico = new VisitaTecnico();
        Optional<Tecnico> tecnico = this.tecnicosRepository.buscar(dto.getIdTecnico());
        if (tecnico.isEmpty())
            throw new RecursoInexistenteException(MensajeRecursoInexistenteFactory.generarMensaje("Tecnico", dto.getIdTecnico()));
        visitaTecnico.setTecnico(tecnico.get());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
        visitaTecnico.setTimestamp(LocalDateTime.parse(dto.getFechaVisita(), formatter));
        visitaTecnico.setDescripcion(dto.getDescripcion());
        visitaTecnico.setUrlFoto(dto.getUrlFoto());
        visitaTecnico.setSolucionado(dto.isSolucionado());
        visitaTecnico.setIncidente(this.incidentesService.obtenerIncidente(dto.getIncidente()));
        if (visitaTecnico.getTimestamp().isAfter(LocalDateTime.now()) || visitaTecnico.getTimestamp().isBefore(visitaTecnico.getIncidente().getTimestamp()))
            throw new FormIncompletoException(MensajeFechaInvalidaFactory.generarMensaje());
        if (visitaTecnico.estaSolucionado())
            this.incidentesService.solucionar(visitaTecnico.getIncidente());
        this.visitasTecnicoRepository.guardar(visitaTecnico);
    }


    private void validarDocumento(TipoDocumento tipoDocumento, String nroDocumento, TecnicoDto dto) {
        if (!DniHelper.esValido(nroDocumento))
            throw new DniDuplicadoException(MensajeDniInvalidoFactory.generarMensaje(), dto);
        Optional<Tecnico> t = tecnicosRepository.buscar(tipoDocumento, nroDocumento);
        if (t.isPresent()) throw new DniDuplicadoException(MensajeDniDuplicadoFactory.generarMensaje(), dto);
    }
}
