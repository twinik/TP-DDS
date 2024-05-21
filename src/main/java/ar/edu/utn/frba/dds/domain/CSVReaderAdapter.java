package ar.edu.utn.frba.dds.domain;

import java.util.*;

/**
 *
 */
public interface CSVReaderAdapter {

  /**
   * @param path
   * @return
   */
  List<Object> readCsv(String path);

}