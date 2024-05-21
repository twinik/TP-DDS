package ar.edu.utn.frba.dds.domain.colaboraciones.cargaMasiva;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

/**
 *
 */
public interface CSVReaderAdapter {
  List<Object> readCsv(String path, String separator) throws IOException;
}