package ar.edu.utn.frba.dds.domain.colaboraciones.cargaMasiva;

import ar.edu.utn.frba.dds.domain.colaboraciones.Colaboracion;
import ar.edu.utn.frba.dds.domain.colaboraciones.ColocacionHeladeras;
import ar.edu.utn.frba.dds.domain.colaboradores.Colaborador;
import ar.edu.utn.frba.dds.domain.colaboradores.Usuario;
import ar.edu.utn.frba.dds.domain.helpers.ConfigReader;
import ar.edu.utn.frba.dds.domain.helpers.PasswordGenerator;
import ar.edu.utn.frba.dds.domain.utils.MailSenderAdapter;
import ar.edu.utn.frba.dds.domain.utils.TipoDocumento;
import ar.edu.utn.frba.dds.domain.utils.TipoDocumentoMapper;
import ar.edu.utn.frba.dds.domain.colaboradores.factories.ColaboradorFactory;
import ar.edu.utn.frba.dds.domain.utils.factories.MyMailFactory;
import ar.edu.utn.frba.dds.domain.colaboradores.factories.UsuarioFactory;
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
  public CargadorDeColaboraciones(String filePath, CSVReaderAdapter csvReader, MailSenderAdapter mailAdapter, ColaboradorRepository respository) throws IOException {
    this.config = new ConfigReader("config.properties");
    this.csvReader = csvReader;
    this.mailSender = mailAdapter;
    this.filePath = filePath;
    this.separator = config.getProperty("separator");
    this.colaboradoRepository = respository;
  }

  /**
   * Metodo cargarColaboraciones que se encarga de cargar colaboraciones.
   */
  public List<Colaboracion> cargarColaboraciones() throws IOException {
    List<Object> registros = csvReader.readCsv(filePath, separator);
    ArrayList<Colaboracion> colaboraciones = new ArrayList<>();
    Colaborador colaborador = null;

    for (Object reg : registros) {
      CargaColaboracion carga = (CargaColaboracion) reg;
      Colaboracion colaboracion = CargaToColaboracionMapper.colaboracionFromCarga(carga);
      Optional<Colaborador> colaboradorOption = colaboradoRepository.buscar(
          new TipoDocumentoMapper().obtenerTipoDeDocumento(carga.getTipoDocumento()),
          carga.getDocumento());


      colaborador = colaboradorOption.orElseGet(() -> crearUsuarioColaboradorNoRegistrado(carga, config));

      colaboracion.setColaborador(colaborador);

      for (int i = 0; i < carga.getCantidad(); i++) {
        colaboraciones.add(colaboracion);
        colaboracion.efectuar();
      }

    }
    // medio raro, se puede cambiar pero habria que hacer algo asi
    if (colaborador != null) {
      for (ColocacionHeladeras colocacion : colaborador.getHeladerasColocadas()) {
        colaborador.sumarPuntos(colocacion.getCalculadorDePuntos().calcularPuntos(colocacion));
      }
    }
    return colaboraciones;
  }

  private Colaborador crearUsuarioColaboradorNoRegistrado(CargaColaboracion carga,
                                                          ConfigReader config) {
    try {
      TipoDocumento tipoDoc = new TipoDocumentoMapper().obtenerTipoDeDocumento(carga.getTipoDocumento());
      String claveGenerada = PasswordGenerator.generatePassword(Integer.parseInt(config.getProperty("password.length")));
      Usuario nuevoUsuario = UsuarioFactory.createUsuario(carga.getMail(), tipoDoc, carga.getDocumento(), claveGenerada);
      Colaborador nuevoColaborador = ColaboradorFactory.createColaborador(nuevoUsuario);

      MyMailFactory.sendMail(mailSender, config.getProperty("MAIL-DIR"),
          carga.getMail(),
          config.getProperty("ASUNTO"),
          config.getProperty("CUERPO") + claveGenerada);


      colaboradoRepository.guardar(nuevoColaborador);
      return nuevoColaborador;
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

}