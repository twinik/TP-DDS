package ar.edu.utn.frba.dds.models.domain.colaboraciones.cargaMasiva;

import java.io.IOException;
import java.util.List;

/**
 * CSVReaderAdapter interface permite leer un archivo CSV.
 */
public interface CSVReaderAdapter {
    List<Object> readCsv(String path, String separator) throws IOException;
}