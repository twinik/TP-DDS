package ar.edu.utn.frba.dds.services;

import ar.edu.utn.frba.dds.dtos.formularios.RespuestaACampoDto;
import ar.edu.utn.frba.dds.exceptions.RecursoInexistenteException;
import ar.edu.utn.frba.dds.models.domain.colaboradores.form.Campo;
import ar.edu.utn.frba.dds.models.domain.colaboradores.form.Opcion;
import ar.edu.utn.frba.dds.models.domain.colaboradores.form.RespuestaACampo;
import ar.edu.utn.frba.dds.models.domain.colaboradores.form.TipoCampo;
import ar.edu.utn.frba.dds.models.messageFactory.MensajeRecursoInexistenteFactory;
import ar.edu.utn.frba.dds.models.repositories.ICampoRepository;
import ar.edu.utn.frba.dds.models.repositories.IOpcionRepository;
import lombok.AllArgsConstructor;
import java.util.Optional;

@AllArgsConstructor
public class RespuestaCampoService {
    private ICampoRepository campoRepository;
    private IOpcionRepository opcionRepository;

    public RespuestaACampo obtenerRespuesta(RespuestaACampoDto dto) {
        RespuestaACampo rta = new RespuestaACampo();
        Optional<Campo> campo = this.campoRepository.buscar(dto.getIdCampo());
        if (campo.isEmpty())
            throw new RecursoInexistenteException(MensajeRecursoInexistenteFactory.generarMensaje("Campo", dto.getIdCampo()));
        rta.setCampo(campo.get());

        if (rta.getCampo().getTipo() == TipoCampo.MULTIPLE_CHOICE || rta.getCampo().getTipo() == TipoCampo.CHOICE) {
            dto.getIdOpciones().forEach(o -> {
                Optional<Opcion> opcion = this.opcionRepository.buscar(o);
                if (opcion.isEmpty())
                    throw new RecursoInexistenteException(MensajeRecursoInexistenteFactory.generarMensaje("Opcion", o));
                rta.agregarOpcionesElegidas(opcion.get());
            });
        } else {
            rta.setRespuesta(dto.getRespuesta());
        }
        return rta;
    }
}
