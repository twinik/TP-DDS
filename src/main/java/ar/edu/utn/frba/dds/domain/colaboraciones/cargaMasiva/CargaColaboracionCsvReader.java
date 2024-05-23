package ar.edu.utn.frba.dds.domain.colaboraciones.cargaMasiva;


import ar.edu.utn.frba.dds.domain.helpers.DateHelper;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * CargaColaboracionCsvReader class lee un archivo csv de colaboraciones.
 */
public class CargaColaboracionCsvReader implements CSVReaderAdapter {


  @Override
  public List<Object> readCsv(String path, String separator) throws IOException {
    List<Object> registros = new ArrayList<>();
    BufferedReader br = new BufferedReader(new FileReader(path));
    String line;
    while ((line = br.readLine()) != null) {
      String[] values = line.split(separator, 9);
      CargaColaboracion cargaColaboracion = new CargaColaboracion();

      cargaColaboracion.setTipoDocumento(values[0]);
      cargaColaboracion.setDocumento(Integer.valueOf(values[1]));
      cargaColaboracion.setNombre(values[2]);
      cargaColaboracion.setApellido(values[3]);
      cargaColaboracion.setMail(values[4]);
      cargaColaboracion.setFechaColaboracion(DateHelper.fechaFromString(values[5], "dd/MM/yyyy"));
      cargaColaboracion.setFormaColaboracion(values[6]);
      cargaColaboracion.setCantidad(Integer.valueOf(values[7]));
      cargaColaboracion.setJsonColaboracion(values[8]);

      registros.add(cargaColaboracion);
    }
    return registros;
  }
}
