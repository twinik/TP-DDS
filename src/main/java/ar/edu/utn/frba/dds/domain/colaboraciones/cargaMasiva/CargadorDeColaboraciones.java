package ar.edu.utn.frba.dds.domain.colaboraciones.cargaMasiva;

import ar.edu.utn.frba.dds.domain.colaboraciones.Colaboracion;
import ar.edu.utn.frba.dds.domain.colaboradores.Colaborador;
import ar.edu.utn.frba.dds.domain.colaboradores.Usuario;
import ar.edu.utn.frba.dds.domain.helpers.ConfigReader;
import ar.edu.utn.frba.dds.domain.helpers.PasswordGenerator;
import ar.edu.utn.frba.dds.domain.utils.MailSenderAdapter;
import ar.edu.utn.frba.dds.domain.utils.MyEmail;
import ar.edu.utn.frba.dds.domain.utils.TipoDocumento;
import ar.edu.utn.frba.dds.domain.utils.TipoDocumentoMapper;
import ar.edu.utn.frba.dds.repositories.imp.ColaboradorRepository;
import java.io.IOException;
import java.util.*;
import lombok.Getter;
import lombok.Setter;

/**
 * CargadorDeColaboraciones class se encarga de cargar colaboraciones.
 */
@Setter
@Getter
public class CargadorDeColaboraciones {
  private CSVReaderAdapter csvReader;
  private MailSenderAdapter mailSender;
  private String filePath;
  private String separator;
  private ColaboradorRepository colaboradoRepository;
  private ConfigReader config;

  /**
   * Constructor con parametros.
   */
  public CargadorDeColaboraciones(CSVReaderAdapter csvReader, MailSenderAdapter mailAdapter, ColaboradorRepository respository) throws IOException {
    this.config = new ConfigReader("config.properties");
    this.csvReader = csvReader;
    this.mailSender = mailAdapter;
    this.filePath = config.getProperty("cargadorColaboracionesFilePath");
    this.separator = config.getProperty("separator");
    this.colaboradoRepository = respository;
  }

  /**
   * Metodo cargarColaboraciones que se encarga de cargar colaboraciones.
   */
  public List<Colaboracion> cargarColaboraciones() throws IOException {
    List<Object> registros = csvReader.readCsv(filePath, separator);
    ArrayList<Colaboracion> colaboraciones = new ArrayList<>();

    for (Object reg : registros) {
      CargaColaboracion carga = (CargaColaboracion) reg;
      Colaboracion colaboracion = CargaToColaboracionMapper.colaboracionFromCarga(carga);
      TipoDocumento tipoDoc = new TipoDocumentoMapper().obtenerTipoDeDocumento(carga.getTipoDocumento());
      Optional<Colaborador> colaboradorOption = colaboradoRepository.buscar(
          tipoDoc,
          carga.getDocumento());
      Colaborador colaborador;

      if (colaboradorOption.isEmpty()) {
        //TODO: mover esta logica a algun metodo privado
        String claveGenerada = PasswordGenerator.generatePassword(Integer.parseInt(config.getProperty("password.length")));
        Usuario nuevoUsuario = new Usuario(carga.getMail(), tipoDoc, carga.getDocumento(), claveGenerada);
        Colaborador nuevoColaborador = new Colaborador();
        nuevoColaborador.setUsuario(nuevoUsuario);

        MyEmail email = new MyEmail(config.getProperty("MAIL-DIR"),
            carga.getMail(),
            config.getProperty("ASUNTO"),
            config.getProperty("CUERPO") + claveGenerada);
        mailSender.enviarMail(email);
        colaborador = nuevoColaborador;
        colaboradoRepository.guardar(colaborador);
      } else {
        colaborador = colaboradorOption.get();
      }

      colaboracion.setColaborador(colaborador);

      for (int i = 0; i < carga.getCantidad(); i++) {
        colaboraciones.add(colaboracion);
        colaborador.sumarPuntos(colaboracion.getCalculadorDePuntos().calcularPuntos(colaboracion));
      }

    }
    return colaboraciones;
  }

}