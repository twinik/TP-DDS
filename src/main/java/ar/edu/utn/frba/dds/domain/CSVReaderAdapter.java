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
  public List<List<String>> readCSV(String path);

}