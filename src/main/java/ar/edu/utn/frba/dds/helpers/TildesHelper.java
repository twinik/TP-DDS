package ar.edu.utn.frba.dds.helpers;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class TildesHelper {
  public static String codificarParaQueryParam(String msj) {
    return URLEncoder.encode(msj, StandardCharsets.UTF_8);
  }
}
