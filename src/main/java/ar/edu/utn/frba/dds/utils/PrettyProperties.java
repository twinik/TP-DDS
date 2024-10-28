package ar.edu.utn.frba.dds.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PrettyProperties {
  private static PrettyProperties instance = null;
  private Properties prop;


  private PrettyProperties() {
    this.prop = new Properties();
    this.readProperties();
  }

  public static PrettyProperties getInstance() {
    if (instance == null) {
      instance = new PrettyProperties();
    }
    return instance;
  }

  private void readProperties() {
    try {
      InputStream input = new FileInputStream("./src/main/resources/config.properties");
      this.prop.load(input);
    } catch (IOException ex) {
      ex.printStackTrace();
    }
  }

  public String propertyFromName(String name) {
    return this.prop.getProperty(name, null);
  }
}
