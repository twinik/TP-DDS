package ar.edu.utn.frba.dds.services;

import ar.edu.utn.frba.dds.dtos.personas.PersonaHumanaDto;
import ar.edu.utn.frba.dds.dtos.personas.PersonaJuridicaDto;
import ar.edu.utn.frba.dds.exceptions.RecursoInexistenteException;
import ar.edu.utn.frba.dds.helpers.DateHelper;
import ar.edu.utn.frba.dds.models.domain.colaboradores.Colaborador;
import ar.edu.utn.frba.dds.models.domain.colaboradores.autenticacion.Usuario;
import ar.edu.utn.frba.dds.models.domain.utils.Direccion;
import ar.edu.utn.frba.dds.models.repositories.IColaboradoresRepository;
import lombok.AllArgsConstructor;
import java.util.Optional;


@AllArgsConstructor
public class ColaboradoresService {
    private IColaboradoresRepository colaboradoresRepository;

    public Optional<Colaborador> colaboradorFromUsuario(String idUsuario) {
        return this.colaboradoresRepository.buscarPorUsuario(idUsuario);
    }

    public Colaborador obtenerColaborador(String id) {
        // TODO: hacerlo con messageFactory
        if (id == null) throw new RecursoInexistenteException("no existe colaborador asociado a este id");
        Optional<Colaborador> colab = this.colaboradoresRepository.buscar(id);
        if (colab.isEmpty()) throw new RecursoInexistenteException("no existe colaborador asociado a este id");
        return colab.get();
    }

    public void registrar(PersonaHumanaDto dto) {
        Colaborador colaborador = new Colaborador();
        colaborador.setNombre(dto.getNombre());
        colaborador.setApellido(dto.getApellido());
        colaborador.setDireccion(dto.getDireccion() != null ? new Direccion(dto.getDireccion().getCalle(), dto.getDireccion().getNumero(), dto.getDireccion().getPiso(), dto.getDireccion().getCodigoPostal()) : null);
        colaborador.setFechaNacimiento(DateHelper.fechaFromString(dto.getFechaNacimiento(), "dd/MM/yyyy"));
        // TODO: hashear clave
        Usuario user = new Usuario(dto.getUsuarioDto().getEmail(), dto.getUsuarioDto().getClave());
        // TODO: roles service dependiendo de las formas de colab
        user.agregarRoles();

        this.colaboradoresRepository.guardar(colaborador);
    }

    public void regisrar(PersonaJuridicaDto dto) {
        //TODO
    }

}
