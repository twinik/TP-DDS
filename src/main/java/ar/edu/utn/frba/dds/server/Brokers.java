package ar.edu.utn.frba.dds.server;

import ar.edu.utn.frba.dds.brokers.AperturaHeladeraBroker;
import ar.edu.utn.frba.dds.brokers.SensorMovimientoBroker;
import ar.edu.utn.frba.dds.brokers.TemperaturaHeladeraBroker;
import java.io.IOException;

public class Brokers {
  public static void init(){
    Thread aperturaBrokerThread = new Thread(() -> {
      try {
        AperturaHeladeraBroker.suscribirseAAperturasHeladeras();
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
    });

    aperturaBrokerThread.start();

    Thread movimientoBrokerThread = new Thread(() -> {
      try {
        SensorMovimientoBroker.suscribirseASensorMovimiento();
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
    });

    movimientoBrokerThread.start();


    Thread temperaturaBrokerThread = new Thread(() -> {
      try {
        TemperaturaHeladeraBroker.suscribirseASensorTemperatura();
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
    });

    temperaturaBrokerThread.start();
  }

}
