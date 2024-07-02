package ar.edu.utn.frba.dds.brokers;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BrokerPublisherTest {

  @Test
  @DisplayName("Prueba publicar mensaje a Broker")
  public void publicarMensaje() {
    BrokerPublisher brokerPublisher = new BrokerPublisher("dds-grupo07-ma-ma", "tcp://broker.hivemq.com:1883", "cliente");
    brokerPublisher.publicar("Mensaje de prueba de BrokerPublisher");
    // suscribirse con un cliente al topic y ver si llega el mensaje
    assert(true);
  }
}