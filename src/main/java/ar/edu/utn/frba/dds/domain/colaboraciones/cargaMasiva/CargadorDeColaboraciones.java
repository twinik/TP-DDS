package ar.edu.utn.frba.dds.domain.colaboraciones.cargaMasiva;

import ar.edu.utn.frba.dds.domain.colaboraciones.AltaPersonaVulnerable;
import ar.edu.utn.frba.dds.domain.colaboraciones.Colaboracion;
import ar.edu.utn.frba.dds.domain.colaboraciones.DonacionDinero;
import ar.edu.utn.frba.dds.domain.colaboraciones.DonacionVianda;
import ar.edu.utn.frba.dds.domain.colaboraciones.RedistribucionViandas;
import ar.edu.utn.frba.dds.domain.colaboradores.Colaborador;
import ar.edu.utn.frba.dds.domain.colaboradores.Usuario;
import ar.edu.utn.frba.dds.domain.helpers.ConfigReader;
import ar.edu.utn.frba.dds.domain.helpers.LocalDateTypeAdapter;
import ar.edu.utn.frba.dds.domain.helpers.PasswordGenerator;
import ar.edu.utn.frba.dds.domain.utils.FormaColaboracionMapper;
import ar.edu.utn.frba.dds.domain.utils.MailSender;
import ar.edu.utn.frba.dds.domain.utils.MailSenderAdapter;
import ar.edu.utn.frba.dds.domain.utils.MyEmail;
import ar.edu.utn.frba.dds.domain.utils.TipoDocumento;
import ar.edu.utn.frba.dds.domain.utils.TipoDocumentoMapper;
import ar.edu.utn.frba.dds.repositories.imp.ColaboradorRepository;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.time.LocalDate;
import java.util.*;

/**
 *
 */
@Setter
@Getter
public class CargadorDeColaboraciones {
  private CSVReaderAdapter csvReader;
  private MailSenderAdapter mailSender;
  private String filePath;
  private String separator;

  public CargadorDeColaboraciones() {
  }

  public CargadorDeColaboraciones(CSVReaderAdapter csvReader, MailSenderAdapter mailAdapter) throws IOException {
    this.csvReader = csvReader;
    this.mailSender = mailAdapter;
    this.filePath = new ConfigReader("cargacolaboraciones.properties").getProperty("cargadorColaboracionesFilePath");
    this.separator = new ConfigReader("cargacolaboraciones.properties").getProperty("separator");
  }

  /**
   *
   */
  public List<Colaboracion> cargarColaboraciones() throws IOException {
    List<Object> registros = csvReader.readCsv(filePath, separator);

    ArrayList<Colaboracion> colaboraciones = new ArrayList<Colaboracion>();

    for (Object reg : registros) {
      CargaColaboracion carga = (CargaColaboracion) reg;

      Colaboracion colaboracion = CargaToColaboracionMapper.colaboracionFromCarga(carga);

      TipoDocumento tipoDoc = new TipoDocumentoMapper().obtenerTipoDeDocumento(carga.getTipoDocumento());

      ColaboradorRepository colaboradoresRepo = ColaboradorRepository.getInstance();
      Optional<Colaborador> colaboradorOption = colaboradoresRepo.buscar(
          tipoDoc,
          carga.getDocumento());

      Colaborador colaborador;

      if (colaboradorOption.isEmpty()) {
        String claveGenerada = PasswordGenerator.generatePassword();
        Usuario nuevoUsuario = new Usuario(carga.getMail(), tipoDoc, carga.getDocumento(), claveGenerada);
        Colaborador nuevoColaborador = new Colaborador();
        nuevoColaborador.setUsuario(nuevoUsuario);

        // TODO: todo esto deberia salir de algun archivo de configuracion
        MyEmail email = new MyEmail("grupo7ddsutn@gmail.com",
            carga.getMail(),
            "Acceso a cuenta de colaborador",
            "Hola, gracias por colaborar, aca dejamos la clave para acceder a tu cuenta, recomendamos acceder a la pagina y cambiarla inmediatamente por una personal.\nClave: " + claveGenerada);
        MailSender.getInstance().enviarMail(email);
        colaborador = nuevoColaborador;
        colaboradoresRepo.guardar(colaborador);
      } else {
        colaborador = colaboradorOption.get();
      }

      colaboracion.setColaborador(colaborador);

      for(int i = 0; i < carga.getCantidad(); i++){
        colaboraciones.add(colaboracion);
        colaboracion.efectuar();
      }

    }
    return colaboraciones;
  }

}